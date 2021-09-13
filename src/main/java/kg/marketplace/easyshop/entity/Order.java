package kg.marketplace.easyshop.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tb_orders")
@NoArgsConstructor
@Data
@SequenceGenerator(name = "SEQ_ID", sequenceName = "SEQ_ORDER", allocationSize = 1)
public class Order extends BaseEntity {

    @Column
    private Integer quantity;

    @Column(name = "total_sum")
    private Double totalSum;

    @Column(name = "order_date")
    private Date orderDate;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "product")
    private List<Product> product;
}
