package kg.marketplace.easyshop.mapper;

import kg.marketplace.easyshop.dto.RoleDTO;
import kg.marketplace.easyshop.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleMapper extends BaseMapper<Role, RoleDTO> {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    @Override
    @Mapping(target = "id", ignore = true)
    Role toEntity(RoleDTO roleDTO);
}
