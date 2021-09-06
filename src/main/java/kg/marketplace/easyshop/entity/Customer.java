package kg.marketplace.easyshop.entity;

import kg.marketplace.easyshop.enums.Sex;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tb_customers")
@NoArgsConstructor
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastnName;

    @Column
    private String email;

    @Column(name = "date_of_birth")
    private Date dob;

    @Column
    private Sex sex;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "basket")
    private Basket basket;
}
