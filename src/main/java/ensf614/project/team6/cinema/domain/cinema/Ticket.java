package ensf614.project.team6.cinema.domain.cinema;

import ensf614.project.team6.cinema.domain.bank.Payment;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime showTime;
    @ManyToOne
    private Movie movie;
    @ManyToOne
    private ShowRoom showRoom;
    @ManyToOne
    private Seat seat;
    private Double price;
    private Boolean isAvailable;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Payment> payments;

    public Ticket(LocalDateTime showTime, Movie movie, ShowRoom showRoom, Seat seat, Double price) {
        this.showTime = showTime;
        this.movie = movie;
        this.showRoom = showRoom;
        this.seat = seat;
        this.price = price;

        this.isAvailable = true;

        payments = new ArrayList<>();
    }

    public Ticket() {

    }

    public Integer getId() {
        return id;
    }

    public LocalDateTime getShowTime() {
        return showTime;
    }

    public Movie getMovie() {
        return movie;
    }

    public ShowRoom getShowRoom() {
        return showRoom;
    }

    public Seat getSeat() {
        return seat;
    }

    public Double getPrice() {
        return price;
    }

    public Boolean isAvailable() {
        return isAvailable;
    }

    public void addPayment(Payment payment) {
        payments.add(payment);
    }

    public Payment getLastedPayment() {
        return payments.get(payments.size() - 1);
    }

    public String getDescription() {
        return "Ticket number " + id + "Movie: " + movie.getTitle() +
                "Showroom: " + showRoom.getName() +
                "Showtime: " + showTime.toString();
    }

    public Boolean canBeCanceledAtSpecifiedMoment(LocalDateTime cancelTime) {
        return cancelTime.isBefore(showTime.minusDays(3));
    }

    public void setAvailability(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
}
