package kg.marketplace.easyshop.service;

import kg.marketplace.easyshop.dto.RoleDTO;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {
    ResponseStatusDTO addRole(RoleDTO roleDTO);
}
