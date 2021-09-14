package kg.marketplace.easyshop.dto;

import kg.marketplace.easyshop.entity.Order;
import kg.marketplace.easyshop.entity.Role;
import kg.marketplace.easyshop.enums.Sex;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class RequestNewUser {
    private String firstName;
    private String lastName;
    private String email;
    private Date dob;
    private Sex sex;
    private Long roleId;
}
