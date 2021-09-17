package kg.marketplace.easyshop.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "tb_categories")
@NoArgsConstructor
@Data
@SequenceGenerator(name = "SEQ_ID", sequenceName = "SEQ_CATEGORY", allocationSize = 1)
public class Category extends BaseEntity{

    @Column(name = "category_name")
    private String name;



}
