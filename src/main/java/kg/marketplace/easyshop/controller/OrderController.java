package kg.marketplace.easyshop.controller;

import kg.marketplace.easyshop.entity.Order;
import kg.marketplace.easyshop.enums.Status;
import kg.marketplace.easyshop.service.impl.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderServiceImpl orderServiceImpl;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.OK)
    public Status save(@RequestBody Order order){
        orderServiceImpl.saveOrder(order);
        return Status.SUCCESS;
    }

    @GetMapping("/get-one-order/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Order getOneOrder(@PathVariable Long id){
        return orderServiceImpl.getOneOrderById(id);
    }


    @GetMapping("/get-all-orders")
    public List<Order> getAll(){
        return orderServiceImpl.getAllOrders();
    }

    @DeleteMapping("/delete-order/{id}")
    public Status deleteOneOrder(@PathVariable Long id){
        return orderServiceImpl.deleteOrderById(id);
    }
}
