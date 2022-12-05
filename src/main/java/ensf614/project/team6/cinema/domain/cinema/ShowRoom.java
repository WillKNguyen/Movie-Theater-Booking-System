package ensf614.project.team6.cinema.domain.cinema;

import javax.persistence.*;

@Entity
@Table(name = "showrooms")
public class ShowRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Boolean isAvailableForScreening;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Boolean isAvailableForScreening() {
        return isAvailableForScreening;
    }

    public void closeShowRoom() {
        isAvailableForScreening = true;
    }

    public void openShowRoom() {
        isAvailableForScreening = false;
    }
}
