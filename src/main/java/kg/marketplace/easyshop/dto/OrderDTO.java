package kg.marketplace.easyshop.dto;

import kg.marketplace.easyshop.entity.Product;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
public class OrderDTO {
    private Long id;
    private Integer quantity;

    private Date orderDate;

    private Double totalSum;
    private List<Product> product;
}
