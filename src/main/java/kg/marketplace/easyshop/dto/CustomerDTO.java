package kg.marketplace.easyshop.dto;

import kg.marketplace.easyshop.entity.Basket;
import kg.marketplace.easyshop.entity.Sex;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class CustomerDTO {
    private Long id;
    private String firstName;
    private String lastnName;
    private String email;
    private Date dob;
    private Sex sex;
    private Basket basket;
}
