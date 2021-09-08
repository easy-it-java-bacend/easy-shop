package kg.marketplace.easyshop.dto;

import kg.marketplace.easyshop.entity.Order;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class BasketDTO{
    private Long id;
    private List<Order> orders;
}
