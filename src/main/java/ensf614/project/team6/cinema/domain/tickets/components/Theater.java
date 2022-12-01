package ensf614.project.team6.cinema.domain.tickets.components;

import javax.persistence.*;

@Entity
@Table(name = "theaters")
public class Theater {
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
