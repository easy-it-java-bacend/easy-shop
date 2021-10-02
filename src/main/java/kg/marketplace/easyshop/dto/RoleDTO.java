package kg.marketplace.easyshop.dto;

import kg.marketplace.easyshop.entity.Permission;
import kg.marketplace.easyshop.enums.Permissions;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class RoleDTO {

    private String roleName;
    private List<Permission> permissions;

}
