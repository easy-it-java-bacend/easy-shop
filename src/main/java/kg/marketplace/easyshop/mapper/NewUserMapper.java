package kg.marketplace.easyshop.mapper;

import kg.marketplace.easyshop.dto.RequestNewUser;
import kg.marketplace.easyshop.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NewUserMapper extends BaseMapper<User, RequestNewUser> {

    NewUserMapper INSTANCE = Mappers.getMapper(NewUserMapper.class);

    @Override
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "username", ignore = true)
    @Mapping(target = "order", ignore = true)
    User toEntity(RequestNewUser requestNewUser);
}
