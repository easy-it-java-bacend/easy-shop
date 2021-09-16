package kg.marketplace.easyshop.dto;

import kg.marketplace.easyshop.enums.Permission;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class RoleDTO {

    private String roleName;
    private Set<Permission> permissions;

}
