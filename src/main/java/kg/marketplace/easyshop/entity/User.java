package kg.marketplace.easyshop.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import kg.marketplace.easyshop.enums.Role;
import kg.marketplace.easyshop.enums.Sex;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import springfox.documentation.spring.web.json.Json;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tb_customers")
@NoArgsConstructor
@Data
@SequenceGenerator(name = "SEQ_ID", sequenceName = "SEQ_USER", allocationSize = 1)
public class User extends BaseEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastnName;

    @Column
    private String email;

    @Column(name = "date_of_birth", columnDefinition = "DATE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd")
    private Date dob;

    @Column
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "orders")
    private List<Order> orders;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;
}
