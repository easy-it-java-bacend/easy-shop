package kg.marketplace.easyshop.service.impl;

import kg.marketplace.easyshop.dao.OrderRepository;
import kg.marketplace.easyshop.dto.OrderDTO;
import kg.marketplace.easyshop.entity.Order;
import kg.marketplace.easyshop.mapper.OrderMapper;
import kg.marketplace.easyshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {


    private final OrderRepository orderRepository;

    @Override
    public ResponseEntity<?> makeOrder(OrderDTO orderDTO) {
        Order order = OrderMapper.INSTANCE.toEntity(orderDTO);
        orderRepository.save(order);
        return ResponseEntity.ok().build();
    }



    public ResponseEntity<?> deleteOneById(Long id) {
        return orderRepository.findById(id)
                .map(order -> {
                    if (order.isDeleted()) {
                        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Order already deleted for id=" + id);
                    }
                    order.setDeleted(true);
                    orderRepository.save(order);
                    return ResponseEntity.ok().build();
                }).orElseGet(() -> {
                    log.error("User not found for id=" + id);
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("For id=" + id);
                });
    }

    @Override



    public ResponseEntity<?> getOrderById(Long id) {
        return orderRepository.findById(id).map(order ->
                ResponseEntity.ok().body(OrderMapper.INSTANCE.toDTO(order)))
                .orElseGet(()-> {
                    log.error("Order not found by id" + id);
                    return ResponseEntity.notFound().build();
                });
    }


    @Override
    public ResponseEntity<?>getOrdersLessThan(Double limit) {
        return orderRepository.findAllByTotalSumLessThan(limit)
                .map(orders -> ResponseEntity.ok()
                        .body(OrderMapper.INSTANCE.toDTOList(orders)))
                .orElseGet(()->{
                    log.error("Orders not found by id");
                    return ResponseEntity.unprocessableEntity().build();
                });

    }

}
