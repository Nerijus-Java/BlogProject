package lt.codeacademy.blogproject.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "Users")
public class User implements UserDetails {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "VARCHAR(36)" , updatable = false)
    @Type(type = "uuid-char")
    private UUID userID;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "blogIdUser")
    private Set<Blog> blogs;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "commentIdUser")
    private Set<Comment> comments;

    @ManyToMany
    private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
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
