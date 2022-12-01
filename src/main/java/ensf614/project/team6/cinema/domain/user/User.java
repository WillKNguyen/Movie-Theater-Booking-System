package ensf614.project.team6.cinema.domain.user;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    public User(String name, String email, String password, Set<Role> roles) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = new ArrayList<>(roles);
    }

    public User() {
        this.name = "0";
        this.email = "default";
        this.password = "default";
        roles = new ArrayList<>();
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return email;
    }

    public List<Role> getRoles() {
        return roles;
    }
}
