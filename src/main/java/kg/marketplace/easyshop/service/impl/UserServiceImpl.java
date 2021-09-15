package kg.marketplace.easyshop.service.impl;

import kg.marketplace.easyshop.dao.RoleRepository;
import kg.marketplace.easyshop.dto.ChangeUserRoleDTO;
import kg.marketplace.easyshop.dto.RequestNewUser;
import kg.marketplace.easyshop.dto.ResponseStatusDTO;
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
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


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
        return new ResponseStatusDTO(Status.SUCCESS, "User saved");
    }

    public User getOneCustomerById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("For id : " + id));
    }

    public List<User> getAllCustomers() {
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
                    return new ResponseStatusDTO(Status.SUCCESS,
                            "User by id = " + id + " deleted");
                }).orElseThrow(() -> new UserNotFoundException("For id = " + id));
    }

    public ResponseStatusDTO changeUserRoleById(ChangeUserRoleDTO changeUserRoleDTO) {
        return userRepository.findById(changeUserRoleDTO.getId())
                .map(user -> {
                    Role role = user.getRole();
                    user.setRole(changeUserRoleDTO.getRole());
                    userRepository.save(user);
                    return new ResponseStatusDTO(Status.SUCCESS,
                            "User role changed from " + role + " to " + changeUserRoleDTO.getRole() + " by id = " + changeUserRoleDTO.getId());
                }).orElseThrow(() -> new UserNotFoundException("For id = " + changeUserRoleDTO.getId()));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
