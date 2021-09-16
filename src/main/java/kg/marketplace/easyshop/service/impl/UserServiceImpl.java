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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public ResponseStatusDTO save(RequestNewUser requestNewUser) {
        if (requestNewUser.getEmail().isEmpty()) {
            throw new UserSaveException("Empty required email field");
        }
        Role role = roleRepository.findById(requestNewUser.getRoleId()).get();

        if (userRepository.existsByEmail(requestNewUser.getEmail())) {
            return new ResponseStatusDTO(Status.FAIL,
                    "User with email = " + requestNewUser.getEmail() + " is already exists");
        }

        User user = NewUserMapper.INSTANCE.toEntity(requestNewUser);
        String email = user.getEmail();
        user.setRole(role);
        user.setUsername(email.substring(0, email.indexOf('@')));
        user.setPassword(passwordEncoder.encode(requestNewUser.getPassword()));

        userRepository.save(user);
        return new ResponseStatusWithObjectDTO<>(Status.SUCCESS, "User saved", user);
    }

    public User getOneUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("For id : " + id));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public ResponseStatusDTO deleteOneById(Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    if (user.isDeleted()) {
                        return new ResponseStatusDTO(Status.FAIL,
                                "User by id = " + id + " is already deleted");
                    }
                    user.setDeleted(true);
                    userRepository.save(user);
                    return new ResponseStatusWithObjectDTO<>(Status.SUCCESS,
                            "User by id = " + id + " deleted", user);
                }).orElseThrow(() -> new UserNotFoundException("For id = " + id));
    }

    public ResponseStatusDTO changeUserRoleById(ChangeUserRoleDTO changeUserRoleDTO) {
        return userRepository.findById(changeUserRoleDTO.getId())
                .map(user -> {
                    Role role = user.getRole();
                    user.setRole(changeUserRoleDTO.getRole());
                    userRepository.save(user);
                    return new ResponseStatusWithObjectDTO<>(Status.SUCCESS,
                            "User role changed from " + role + " to " + changeUserRoleDTO.getRole() + " by id = " + changeUserRoleDTO.getId(), user);
                }).orElseThrow(() -> new UserNotFoundException("For id = " + changeUserRoleDTO.getId()));
    }

    @Override
    public void login(AuthenticationRequest authenticationRequest) {

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
