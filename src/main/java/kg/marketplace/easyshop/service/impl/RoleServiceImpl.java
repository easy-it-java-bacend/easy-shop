package kg.marketplace.easyshop.service.impl;

import kg.marketplace.easyshop.dao.RoleRepository;
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
    public Status addRole(RoleDTO roleDTO) {
        roleRepository.save(RoleMapper.INSTANCE.toEntity(roleDTO));
        return Status.SUCCESS;
    }
}