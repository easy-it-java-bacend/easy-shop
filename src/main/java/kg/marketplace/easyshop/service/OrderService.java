package kg.marketplace.easyshop.service;

import kg.marketplace.easyshop.dto.OrderDTO;

import java.util.List;

public interface OrderService {

    void makeOrder(OrderDTO orderDTO);
    OrderDTO getOrderById(Long id);
    List<OrderDTO> getOrdersLessThan(Double limit);

}
