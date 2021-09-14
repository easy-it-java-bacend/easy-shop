package kg.marketplace.easyshop.entity;

import kg.marketplace.easyshop.enums.Permission;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tb_role")
@NoArgsConstructor
@Data
public class Role extends BaseEntity {

    @Column(name = "role_name")
    private String roleName;

    @ElementCollection(targetClass = Permission.class)
    @CollectionTable(name = "tb_permission", joinColumns = @JoinColumn(name = "id"))
    @Column
    @Enumerated(EnumType.STRING)
    private Set<Permission> permissions;
}
