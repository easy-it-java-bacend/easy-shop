package kg.marketplace.easyshop.service.impl;

import kg.marketplace.easyshop.dao.RoleRepository;
import kg.marketplace.easyshop.dto.RoleDTO;
import kg.marketplace.easyshop.entity.Role;
import kg.marketplace.easyshop.enums.Status;
import kg.marketplace.easyshop.mapper.RoleMapper;
import kg.marketplace.easyshop.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public ResponseEntity<?> addRole(RoleDTO roleDTO) {
        Role role = RoleMapper.INSTANCE.toEntity(roleDTO);
        return roleRepository
                .findByRoleName(roleDTO.getRoleName())
                .map(r -> {
                    log.error("Role already exists");
                   return ResponseEntity.unprocessableEntity().build();
                })
                .orElseGet(()->{
                    roleRepository.save(role);
                    return ResponseEntity.ok().build();
        });
    }
}