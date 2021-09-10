package kg.marketplace.easyshop.service;

import kg.marketplace.easyshop.dto.ChangeUserRoleDTO;
import kg.marketplace.easyshop.dto.UserDTO;
import kg.marketplace.easyshop.entity.User;
import kg.marketplace.easyshop.enums.Status;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    User getOneCustomerById(Long id);
    List<User> getAllCustomers();
    void save(UserDTO userDTO);
    Status deleteOneById(Long id);
    Status changeUserRoleById(ChangeUserRoleDTO changeUserRoleDTO);
}
