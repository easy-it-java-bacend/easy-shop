package kg.marketplace.easyshop.service;
import kg.marketplace.easyshop.dto.OrderDTO;
import kg.marketplace.easyshop.enums.Status;
import org.springframework.http.ResponseEntity;


import java.util.List;

public interface OrderService {



    ResponseEntity<?> deleteOneById(Long id);


    ResponseEntity<?> makeOrder(OrderDTO orderDTO);
    ResponseEntity<?> getOrderById(Long id);
    ResponseEntity<?> getOrdersLessThan(Double limit);


}
