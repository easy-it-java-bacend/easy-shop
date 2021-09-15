package kg.marketplace.easyshop.service;

import kg.marketplace.easyshop.dto.ChangeUserRoleDTO;
import kg.marketplace.easyshop.dto.RequestNewUser;
import kg.marketplace.easyshop.dto.ResponseStatusDTO;
import kg.marketplace.easyshop.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService extends UserDetailsService {

    User getOneCustomerById(Long id);
    List<User> getAllCustomers();
    ResponseStatusDTO save(RequestNewUser requestNewUser);
    ResponseStatusDTO deleteOneById(Long id);
    ResponseStatusDTO changeUserRoleById(ChangeUserRoleDTO changeUserRoleDTO);
}
