package kg.marketplace.easyshop.service.impl;

import kg.marketplace.easyshop.dao.RoleRepository;
import kg.marketplace.easyshop.dto.ResponseStatusDTO;
import kg.marketplace.easyshop.dto.RoleDTO;
import kg.marketplace.easyshop.enums.Status;
import kg.marketplace.easyshop.mapper.RoleMapper;
import kg.marketplace.easyshop.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public ResponseStatusDTO addRole(RoleDTO roleDTO) {
        if (roleRepository.existsByRoleName(roleDTO.getRoleName())) {
            return new ResponseStatusDTO(Status.FAIL,
                    "Role with name '" + roleDTO.getRoleName() + "' is already exists");
        }
        roleRepository.save(RoleMapper.INSTANCE.toEntity(roleDTO));
        return new ResponseStatusDTO(Status.SUCCESS, "Role with name '" + roleDTO.getRoleName() + "' is saved");
    }
}