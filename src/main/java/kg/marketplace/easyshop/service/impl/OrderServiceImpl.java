package kg.marketplace.easyshop.service.impl;

import kg.marketplace.easyshop.dao.OrderRepository;
import kg.marketplace.easyshop.dto.OrderDTO;
import kg.marketplace.easyshop.entity.Order;
import kg.marketplace.easyshop.mapper.OrderMapper;
import kg.marketplace.easyshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;


    @Override
    public void makeOrder(OrderDTO orderDTO) {
        Order order = OrderMapper.INSTANCE.toEntity(orderDTO);
        orderRepository.save(order);
    }

    @Override
    public Order getOrderById(Long id) {
        return null;
    }

    @Override
    public List<OrderDTO> getOrdersLessThan(Double limit) {
        List<Order> orders = orderRepository.findAllByTotalSumLessThan(limit);
        return OrderMapper.INSTANCE.toDTOList(orders);
    }
}
