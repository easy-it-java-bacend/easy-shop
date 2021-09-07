package kg.marketplace.easyshop.dto;

import kg.marketplace.easyshop.entity.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class OrderDTO {
    private Long id;
    private Integer quantity;
    private Date orderDate;
    private Double totalSum;
    private Product product;
}
