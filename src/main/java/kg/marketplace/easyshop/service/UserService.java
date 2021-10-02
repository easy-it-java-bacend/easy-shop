package kg.marketplace.easyshop.service;

import kg.marketplace.easyshop.dto.AuthenticationRequest;
import kg.marketplace.easyshop.dto.ChangeUserRoleDTO;
import kg.marketplace.easyshop.dto.RequestNewUser;
import kg.marketplace.easyshop.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService extends UserDetailsService {

    ResponseEntity<?> getOneUserById(Long id);
    ResponseEntity<?> getAllUsers();
    ResponseEntity<?> save(RequestNewUser requestNewUser);
    ResponseEntity<?> deleteOneById(Long id);
    ResponseEntity<?> changeUserRoleById(ChangeUserRoleDTO changeUserRoleDTO);

    UserDetails getOneByUsername(String username);
}
