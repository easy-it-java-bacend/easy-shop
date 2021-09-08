package kg.marketplace.easyshop.service.impl;

import kg.marketplace.easyshop.dao.OrderRepository;
import kg.marketplace.easyshop.dto.OrderDTO;
import kg.marketplace.easyshop.entity.Order;
import kg.marketplace.easyshop.enums.Status;
import kg.marketplace.easyshop.exceptions.CustomerNotFoundException;
import kg.marketplace.easyshop.exceptions.OrderNotFoundException;
import kg.marketplace.easyshop.exceptions.OrderSaveException;
import kg.marketplace.easyshop.mapper.OrderMapper;
import kg.marketplace.easyshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {


    private final OrderRepository orderRepository;


    public Order getOneOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("id: " + id));
    }


    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }


    public void save(OrderDTO orderDTO) {

        if(orderDTO.getQuantity() == 0){
            throw new OrderSaveException("Empty required [Quantity] field");
        }
        if(orderDTO.getTotalSum() == 0){
            throw new OrderSaveException("Total Sum is Empty!");
        }

        if(orderDTO.getProduct() == null){
            throw new OrderSaveException("You not have Product");
        }

        Order order = OrderMapper.INSTANCE.toEntity(orderDTO);
        orderRepository.save(order);
    }


    public Status deleteOneById(Long id) {
        if(orderRepository.deleteOrderById(id).isPresent()){
            orderRepository.deleteOrderById(id);
            return Status.SUCCESS;
        }
        return Status.FAIL;
    }
}
