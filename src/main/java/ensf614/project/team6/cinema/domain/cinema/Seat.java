package ensf614.project.team6.cinema.domain.cinema;

import javax.persistence.*;

@Entity
@Table(name = "seats")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String number;

    public Integer getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }
}
