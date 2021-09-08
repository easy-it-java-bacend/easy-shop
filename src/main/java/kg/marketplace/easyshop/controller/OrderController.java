package kg.marketplace.easyshop.controller;

import kg.marketplace.easyshop.dto.OrderDTO;
import kg.marketplace.easyshop.entity.Order;
import kg.marketplace.easyshop.enums.Status;
import kg.marketplace.easyshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/make-order")
    public Status makeOrder(@RequestBody OrderDTO orderDTO) {
        orderService.makeOrder(orderDTO);
        return Status.SUCCESS;
    }

    @GetMapping("/get-all-less-than")
    public List<OrderDTO> getAllOrdesLessThan(@RequestParam(defaultValue = "0") Double limit) {
        return orderService.getOrdersLessThan(limit);
    }

    @GetMapping("/get-all-between")
    public List<OrderDTO> getAllOrdersBetween(@RequestParam Double floor, @RequestParam Double ceil) {
        return orderService.getOrdersLessThan(floor);
    }

}
