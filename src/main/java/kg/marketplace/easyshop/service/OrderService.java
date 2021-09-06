package kg.marketplace.easyshop.service;

import kg.marketplace.easyshop.dto.OrderDTO;
import kg.marketplace.easyshop.entity.Order;
import kg.marketplace.easyshop.enums.Status;

import java.util.List;

public interface OrderService {
    Order getOneOrderById(Long id);
    List<Order> getAllOrders();
    void saveOrder(OrderDTO order);
    Status deleteOrderById(Long id);

}
