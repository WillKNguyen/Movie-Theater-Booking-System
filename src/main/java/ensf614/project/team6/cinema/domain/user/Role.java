package ensf614.project.team6.cinema.domain.user;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    public Role(String title) {
        this.title = title;
    }

    public Role() {

    }

    public String getTitle() {
        return title;
    }
}
