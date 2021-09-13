package kg.marketplace.easyshop.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tb_products")
@NoArgsConstructor
@Data
@SequenceGenerator(name = "SEQ_ID", sequenceName = "SEQ_PRODUCT", allocationSize = 1)
public class Product extends BaseEntity {

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Double price;

}
