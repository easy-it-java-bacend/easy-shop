package kg.marketplace.easyshop.dto;

import kg.marketplace.easyshop.entity.Order;
import kg.marketplace.easyshop.enums.Role;
import kg.marketplace.easyshop.enums.Sex;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Date dob;
    private Sex sex;
    private List<Order> orders;
}
