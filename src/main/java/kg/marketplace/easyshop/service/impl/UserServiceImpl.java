package kg.marketplace.easyshop.service.impl;

import kg.marketplace.easyshop.dto.ChangeUserRoleDTO;
import kg.marketplace.easyshop.dto.UserDTO;
import kg.marketplace.easyshop.enums.Role;
import kg.marketplace.easyshop.enums.Status;
import kg.marketplace.easyshop.exceptions.CustomerNotFoundException;
import kg.marketplace.easyshop.exceptions.CustomerSaveException;
import kg.marketplace.easyshop.dao.UserRepository;
import kg.marketplace.easyshop.entity.User;
import kg.marketplace.easyshop.mapper.UserMapper;
import kg.marketplace.easyshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public void save(UserDTO userDTO) {
        if (userDTO.getEmail().isEmpty()) {
            throw new CustomerSaveException("Empty required email field");
        }
        if (userDTO.getDob() == null) {
            throw new CustomerSaveException("Empty required dob field");
        }
        User user = UserMapper.INSTANCE.toEntity(userDTO);

        LocalDate dob = userDTO.getDob().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Period period = Period.between(dob, LocalDate.now());
        if (period.getYears() < 18) {
            user.setRole(Role.CUSTOMER_NOT_ADULT);
        }
        else {
            user.setRole(Role.CUSTOMER);
        }
        userRepository.save(user);
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
        userRepository.changeRoleById(changeUserRoleDTO.getId(), changeUserRoleDTO.getRole());
        return Status.SUCCESS;
    }

}
