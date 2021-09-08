package kg.marketplace.easyshop.service.impl;

import kg.marketplace.easyshop.dto.UserDTO;
import kg.marketplace.easyshop.enums.Status;
import kg.marketplace.easyshop.exceptions.CustomerNotFoundException;
import kg.marketplace.easyshop.exceptions.CustomerSaveException;
import kg.marketplace.easyshop.dao.UserRepository;
import kg.marketplace.easyshop.entity.User;
import kg.marketplace.easyshop.mapper.UserMapper;
import kg.marketplace.easyshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        int age = (int) ((userDTO.getDob().getTime()) / 365.25 - 1970);
        if (age < 18) {
            throw new CustomerSaveException("Customer is not adult enought");
        }
        User user = UserMapper.INSTANCE.toEntity(userDTO);
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
}
