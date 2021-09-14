package kg.marketplace.easyshop.dto;

import kg.marketplace.easyshop.entity.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChangeUserRoleDTO {

    private Long id;
    private Role role;

}
