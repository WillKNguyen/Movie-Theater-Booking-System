package ensf614.project.team6.cinema.domain.cinema;

import javax.persistence.*;

@Entity
@Table(name = "tickets")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
