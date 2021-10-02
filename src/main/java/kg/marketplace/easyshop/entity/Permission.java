package kg.marketplace.easyshop.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.List;



@Data
@Entity
@NoArgsConstructor
@Table(name = "tb_permissions")
@EqualsAndHashCode(callSuper = true)
@SequenceGenerator(name = "SEQ_ID", sequenceName = "SEQ_PERMISSION", allocationSize = 1, initialValue = 2)
public class Permission extends BaseEntity {

    @Column(name = "permission_name")
private String permissionName;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "permissions")
    private List<Role> roles;
}
