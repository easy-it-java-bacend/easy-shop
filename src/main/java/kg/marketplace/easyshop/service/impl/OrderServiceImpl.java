package kg.marketplace.easyshop.service.impl;

import kg.marketplace.easyshop.dao.OrderRepository;
import kg.marketplace.easyshop.entity.Order;
import kg.marketplace.easyshop.enums.Status;
import kg.marketplace.easyshop.exceptions.OrderNotFoundException;
import kg.marketplace.easyshop.exceptions.OrderSaveException;
import kg.marketplace.easyshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public Order getOneOrderById(Long id) {
        return orderRepository.getOrderById(id).orElseThrow(()->new OrderNotFoundException("Order number "+id+" not found!"));
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public void saveOrder(Order order) {
        if(order.getProduct()==null){
            throw new OrderSaveException("Order must contain at least 1 product!");
        }
        if (order.getQuantity()<=0){
            throw new OrderSaveException("Order must contain at least 1 product!");
        }
        if (order.getTotalSum()<=0){
            throw new OrderSaveException("Can not count total sum!");
        }
    }

    @Override
    public Status deleteOrderById(Long id) {
        if (orderRepository.deleteOrderById(id).isPresent()){
            orderRepository.deleteOrderById(id);
            return Status.SUCCESS;
        }
        return Status.FAIL;
    }
}
