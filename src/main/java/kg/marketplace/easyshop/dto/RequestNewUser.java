package kg.marketplace.easyshop.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private Date dob;
    private Sex sex;
    private Long roleId;
    private String password;


}
