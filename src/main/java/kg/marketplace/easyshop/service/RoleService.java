package kg.marketplace.easyshop.service;

import kg.marketplace.easyshop.dto.RoleDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {
    ResponseEntity<?> addRole(RoleDTO roleDTO);
}
