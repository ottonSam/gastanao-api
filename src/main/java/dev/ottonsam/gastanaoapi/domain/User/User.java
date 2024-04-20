package dev.ottonsam.gastanaoapi.domain.User;

import dev.ottonsam.gastanaoapi.domain.model.Category;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "tb_users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(length = 50)
    private String name;
    @Column(unique = true, length = 50)
    private String login;
    private String password;
    private UserRole rule;
    @OneToMany(targetEntity = Category.class,fetch = FetchType.EAGER, mappedBy = "user")
    @Column(nullable = true)
    private List<Category> categories = new ArrayList<>();

    public User(UserRequestDTO data) {
        this.name = data.name();
        this.login = data.login();
        this.password = data.password();
        this.categories = new ArrayList<>();
        this.rule = UserRole.USER;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (rule == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return login;
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
