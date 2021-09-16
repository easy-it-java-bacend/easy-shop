package kg.marketplace.easyshop.service.impl;

import kg.marketplace.easyshop.dao.OrderRepository;
import kg.marketplace.easyshop.dto.OrderDTO;
import kg.marketplace.easyshop.dto.ResponseStatusDTO;
import kg.marketplace.easyshop.dto.ResponseStatusWithObjectDTO;
import kg.marketplace.easyshop.entity.Order;
import kg.marketplace.easyshop.enums.Status;
import kg.marketplace.easyshop.exceptions.OrderNotFoundException;
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
    public ResponseStatusDTO makeOrder(OrderDTO orderDTO) {
        Order order = OrderMapper.INSTANCE.toEntity(orderDTO);
        orderRepository.save(order);
        return new ResponseStatusWithObjectDTO<>(Status.SUCCESS,
                "Order added", order);
    }



    public Status deleteOneById(Long id) {
        if (orderRepository.deleteOrderById(id).isPresent()) {
            orderRepository.deleteOrderById(id);
            return Status.SUCCESS;
        }
        return Status.FAIL;
    }

    @Override



    public OrderDTO getOrderById(Long id) {
        return OrderMapper.INSTANCE.toDTO(orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("For id : " + id)));
    }


    @Override
    public List<OrderDTO> getOrdersLessThan(Double limit) {
        List<Order> orders = orderRepository.findAllByTotalSumLessThan(limit);
        return OrderMapper.INSTANCE.toDTOList(orders);
    }

}
