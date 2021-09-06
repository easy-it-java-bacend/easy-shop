package kg.marketplace.easyshop.dto;

import kg.marketplace.easyshop.entity.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDTO {
    private Long id;
    private Integer quantity;
    private Double totalSum;
    private Product product;
}
