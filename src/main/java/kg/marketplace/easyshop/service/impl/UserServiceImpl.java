package kg.marketplace.easyshop.service.impl;

import kg.marketplace.easyshop.dao.RoleRepository;
import kg.marketplace.easyshop.dto.ChangeUserRoleDTO;
import kg.marketplace.easyshop.dto.RequestNewUser;
import kg.marketplace.easyshop.dto.UserDTO;
import kg.marketplace.easyshop.entity.Order;
import kg.marketplace.easyshop.entity.Role;
import kg.marketplace.easyshop.enums.Status;
import kg.marketplace.easyshop.exceptions.CustomerNotFoundException;
import kg.marketplace.easyshop.exceptions.CustomerSaveException;
import kg.marketplace.easyshop.dao.UserRepository;
import kg.marketplace.easyshop.entity.User;
import kg.marketplace.easyshop.mapper.UserMapper;
import kg.marketplace.easyshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


    public Status save(RequestNewUser requestNewUser) {
        if (requestNewUser.getEmail().isEmpty()) {
            throw new CustomerSaveException("Empty required email field");
        }
        Role role = roleRepository.findById(requestNewUser.getRoleId()).get();

        User user = new User();
        user.setFirstName(requestNewUser.getFirstName());
        user.setLastnName(requestNewUser.getLastName());
        user.setDob(requestNewUser.getDob());
        user.setEmail(requestNewUser.getEmail());
        user.setOrders(null);
        user.setSex(requestNewUser.getSex());
        user.setRole(role);
        userRepository.save(user);

        return Status.SUCCESS;
    }

    public User getOneCustomerById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("For id : " + id));
    }

    public List<User> getAllCustomers() {
        return userRepository.findAll();
    }

    public Status deleteOneById(Long id) {
        if (userRepository.deleteCustomerById(id).isPresent()) {
            userRepository.deleteCustomerById(id);
            return Status.SUCCESS;
        }
        return Status.FAIL;
    }

    public Status changeUserRoleById(ChangeUserRoleDTO changeUserRoleDTO) {
        userRepository.findById(changeUserRoleDTO.getId())
                .map(user -> {
                   user.setRole(changeUserRoleDTO.getRole());
                   return userRepository.save(user);
                });
        return Status.SUCCESS;
    }

}
