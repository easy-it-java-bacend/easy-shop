package kg.marketplace.easyshop.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import kg.marketplace.easyshop.enums.Sex;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "tb_user")
@NoArgsConstructor
@Data
@SequenceGenerator(name = "SEQ_ID", sequenceName = "SEQ_USER", allocationSize = 1, initialValue = 2)
public class User extends BaseEntityAudit implements UserDetails {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastnName;

    @Column
    private String email;

    @Column(name = "date_of_birth", columnDefinition = "DATE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private Date dob;

    @Column
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "orders")
    private List<Order> orders;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_role")
    private Role role;

    @Column
    private String username;

    @Column
    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       return role.getPermissions()
               .stream()
               .map(permission -> new SimpleGrantedAuthority(permission.name()))
               .collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
