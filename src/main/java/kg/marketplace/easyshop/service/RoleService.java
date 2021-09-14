package kg.marketplace.easyshop.service;

import kg.marketplace.easyshop.dto.RoleDTO;
import kg.marketplace.easyshop.enums.Status;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {
    Status addRole(RoleDTO roleDTO);
}
