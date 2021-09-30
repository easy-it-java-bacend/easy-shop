package kg.marketplace.easyshop.controller;

import kg.marketplace.easyshop.dto.OrderDTO;
import kg.marketplace.easyshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/make-order")
    public ResponseStatusDTO makeOrder(@RequestBody OrderDTO orderDTO) {
        return orderService.makeOrder(orderDTO);
    }

    @GetMapping("/get-all-less-than")
    @PreAuthorize("hasAuthority('READ_ORDER')")
    public List<OrderDTO> getAllOrdesLessThan(@RequestParam(defaultValue = "0") Double limit) {
        return orderService.getOrdersLessThan(limit);
    }

    @GetMapping("/get-all-between")
    public List<OrderDTO> getAllOrdesBetween(@RequestParam Double floor, @RequestParam Double ceil) {
        return orderService.getOrdersLessThan(floor);
    }

}
