package kg.marketplace.easyshop.service;

import kg.marketplace.easyshop.dto.OrderDTO;
import kg.marketplace.easyshop.dto.ResponseStatusDTO;

import java.util.List;

public interface OrderService {

    ResponseStatusDTO makeOrder(OrderDTO orderDTO);
    OrderDTO getOrderById(Long id);
    List<OrderDTO> getOrdersLessThan(Double limit);

}
