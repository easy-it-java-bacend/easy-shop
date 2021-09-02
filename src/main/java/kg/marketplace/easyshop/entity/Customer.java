package kg.marketplace.easyshop.entity;

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

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer")
    private Basket basket;

    @Override
    public String toString() {
        String json = String.format("" +
                "\"id\" : %d%n" +
                "\"firstName\" : \"%s\"%n" +
                "\"lastName\" : \"%s\"%n" +
                "\"email\" : \"%s\"%n" +
                "\"dob\" : %d%n" +
                "\"sex\" : %d%n",
                id,
                firstName,
                lastnName,
                email,
                dob.getTime(),
                sex.ordinal());
        return json;
    }
}
