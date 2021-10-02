package kg.marketplace.easyshop.service.impl;

import kg.marketplace.easyshop.dao.RoleRepository;
import kg.marketplace.easyshop.dto.*;
import kg.marketplace.easyshop.entity.Role;
import kg.marketplace.easyshop.dao.UserRepository;
import kg.marketplace.easyshop.entity.User;
import kg.marketplace.easyshop.mapper.NewUserMapper;
import kg.marketplace.easyshop.mapper.UserMapper;
import kg.marketplace.easyshop.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<?> save(RequestNewUser requestNewUser) {
        if (requestNewUser.getEmail().isEmpty()) {
          return ResponseEntity.badRequest().body("this field must completed");
        }
        Role role = roleRepository.findById(requestNewUser.getRoleId())
                .orElseThrow(() -> new RuntimeException("Role not found for id=" + requestNewUser.getRoleId()));

        if (userRepository.existsByEmail(requestNewUser.getEmail())) {
            return ResponseEntity.status(HttpStatus.IM_USED).body("For email=" + requestNewUser.getEmail());
        }

        User user = NewUserMapper.INSTANCE.toEntity(requestNewUser);
        String email = user.getEmail();
        user.setRole(role);
        user.setUsername(email.substring(0, email.indexOf('@')));
        user.setPassword(passwordEncoder.encode(requestNewUser.getPassword()));

        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public ResponseEntity<?> getOneUserById(Long id) {
        return userRepository.findById(id).map(user ->
                ResponseEntity.ok().body(UserMapper.INSTANCE.toDTO(user)))
                .orElseGet(()-> {
                    log.error("User not found by id" + id);
                return ResponseEntity.ok().build();
                });
    }

    public ResponseEntity<?> getAllUsers() {
        return userRepository.findAllByDeletedFalseAndArchivedFalse()
                .map(user -> ResponseEntity.ok()
                .body(UserMapper.INSTANCE.toDTO(user)))
                .orElseGet(()->{
                    log.error("Users not found by id" );
                    return ResponseEntity.ok().build();
                });
    }

    public ResponseEntity<?> deleteOneById(Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    if (user.isDeleted()) {
                        return ResponseEntity.unprocessableEntity().body("User already deleted for id=" + id);
                    }
                    user.setDeleted(true);
                    userRepository.save(user);
                    return ResponseEntity.ok().build();
                }).orElseGet(() -> {
                    log.error("User not found for id=" + id);
                    return ResponseEntity.ok().build();
                });
    }

    public ResponseEntity<?> changeUserRoleById(ChangeUserRoleDTO changeUserRoleDTO) {
        return userRepository.findById(changeUserRoleDTO.getId())
                .map(user -> {
                    user.setRole(roleRepository.findById(changeUserRoleDTO.getRole()).get());
                    userRepository.save(user);
                    return ResponseEntity.ok().build();
                }).orElseGet(() -> {
                    log.error("User not found for id=" + changeUserRoleDTO.getId());
                    return ResponseEntity.ok().build();
                });
    }

    @Override
    public UserDetails getOneByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseGet(()->{
                        log.error("User not found for username="
                                + username);
                        return null;});
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
