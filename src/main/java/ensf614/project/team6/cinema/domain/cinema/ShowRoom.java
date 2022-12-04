package ensf614.project.team6.cinema.domain.cinema;

import javax.persistence.*;

@Entity
@Table(name = "showrooms")
public class ShowRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }


}
