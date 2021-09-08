package kg.marketplace.easyshop.mapper;

import kg.marketplace.easyshop.dto.OrderDTO;
import kg.marketplace.easyshop.entity.Order;
import org.mapstruct.factory.Mappers;

public interface OrderMapper extends BaseMapper<Order, OrderDTO> {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);
}
