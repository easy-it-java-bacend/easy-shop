package kg.marketplace.easyshop.entity;

import kg.marketplace.easyshop.enums.Permissions;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tb_role")
@NoArgsConstructor
@Data
public class Role extends BaseEntity {

    @Column(name = "role_name")
    private String roleName;



    @Column(name = "id_permission")
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name ="role_has_permissions",
    joinColumns =  {@JoinColumn(name = "id_role")},
    inverseJoinColumns = {@JoinColumn(name = "id_permission")})
    private List<Permission> permissions;
}
