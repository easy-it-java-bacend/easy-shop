package kg.marketplace.easyshop.mapper;

import kg.marketplace.easyshop.dto.CustomerDTO;
import kg.marketplace.easyshop.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper extends BaseMapper<Customer, CustomerDTO>{
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);
}
