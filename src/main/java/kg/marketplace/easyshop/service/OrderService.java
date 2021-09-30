package kg.marketplace.easyshop.service;
import kg.marketplace.easyshop.dto.OrderDTO;
import kg.marketplace.easyshop.enums.Status;


import java.util.List;

public interface OrderService {



    Status deleteOneById(Long id);


    ResponseStatusDTO makeOrder(OrderDTO orderDTO);
    OrderDTO getOrderById(Long id);
    List<OrderDTO> getOrdersLessThan(Double limit);


}
