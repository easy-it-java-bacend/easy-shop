package kg.marketplace.easyshop.service.impl;

import kg.marketplace.easyshop.dao.RoleRepository;
import kg.marketplace.easyshop.dto.*;
import kg.marketplace.easyshop.entity.Role;
import kg.marketplace.easyshop.enums.Status;
import kg.marketplace.easyshop.exceptions.UserNotFoundException;
import kg.marketplace.easyshop.exceptions.UserSaveException;
import kg.marketplace.easyshop.dao.UserRepository;
import kg.marketplace.easyshop.entity.User;
import kg.marketplace.easyshop.mapper.NewUserMapper;
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
            throw new UserSaveException("Empty required email field");
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
        return userRepository.findById(id).map(user -> {
            return ResponseEntity.ok().body(user);
        }).orElseThrow(() -> new UserNotFoundException("For id=" + id));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public ResponseEntity<?> deleteOneById(Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    if (user.isDeleted()) {
                        return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("User already deleted for id=" + id);
                    }
                    user.setDeleted(true);
                    userRepository.save(user);
                    return ResponseEntity.ok().build();
                }).orElseGet(() -> {
                    log.error("User not found for id=" + id);
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("For id=" + id);
                });
    }

    public ResponseEntity<?> changeUserRoleById(ChangeUserRoleDTO changeUserRoleDTO) {
        return userRepository.findById(changeUserRoleDTO.getId())
                .map(user -> {
                    if (!roleRepository.existsById(changeUserRoleDTO.getId())) {
                        log.error("Role not found for id=" + changeUserRoleDTO.getId());
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Role not found for id=" + changeUserRoleDTO.getRole());
                    }
                    user.setRole(roleRepository.findById(changeUserRoleDTO.getRole()).get());
                    userRepository.save(user);
                    return ResponseEntity.ok().build();
                }).orElseGet(() -> {
                    log.error("User not found for id=" + changeUserRoleDTO.getId());
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found for id=" + changeUserRoleDTO.getId());
                });
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
