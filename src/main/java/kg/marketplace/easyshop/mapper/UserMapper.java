package kg.marketplace.easyshop.mapper;

import kg.marketplace.easyshop.dto.UserDTO;
import kg.marketplace.easyshop.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper extends BaseMapper<User, UserDTO>{
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Override
    @Mapping(target = "id", ignore = true)
    User toEntity(UserDTO userDTO);
}
