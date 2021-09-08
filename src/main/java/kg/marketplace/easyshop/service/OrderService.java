package kg.marketplace.easyshop.service;

import kg.marketplace.easyshop.dto.OrderDTO;
import kg.marketplace.easyshop.entity.Order;
import kg.marketplace.easyshop.enums.Status;

import java.util.List;

public interface OrderService {
    Order getOneOrderById(Long Id);
    List<Order> getAllOrder();
    void save(OrderDTO orderDTO);
    Status deleteOneById(Long id);
}
