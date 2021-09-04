package kg.marketplace.easyshop.controller;

import kg.marketplace.easyshop.entity.Order;
import kg.marketplace.easyshop.enums.Status;
import kg.marketplace.easyshop.service.OrderService;
import kg.marketplace.easyshop.service.impl.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/save-order")
    @ResponseStatus(HttpStatus.OK)
    public Status save(@RequestBody Order order){
        orderService.saveOrder(order);
        return Status.SUCCESS;
    }

    @GetMapping("/get-order-by-id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Order getOneOrder(@PathVariable Long id){
        return orderService.getOneOrderById(id);
    }


    @GetMapping("/get-all-orders")
    public List<Order> getAll(){
        return orderService.getAllOrders();
    }

    @DeleteMapping("/delete-order/{id}")
    public Status deleteOneOrder(@PathVariable Long id){
        return orderService.deleteOrderById(id);
    }
}
