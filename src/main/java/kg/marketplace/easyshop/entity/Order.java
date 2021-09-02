package kg.marketplace.easyshop.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tb_orders")
@NoArgsConstructor
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column
    private Integer quantity;

    @Column(name = "total_sum")
    private Double totalSum;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product")
    private Product product;
}
