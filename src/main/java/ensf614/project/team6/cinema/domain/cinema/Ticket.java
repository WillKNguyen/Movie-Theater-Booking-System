package ensf614.project.team6.cinema.domain.cinema;

import ensf614.project.team6.cinema.domain.bank.Payment;
import ensf614.project.team6.cinema.domain.user.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime showTime;
    @ManyToOne
    private User owner;
    @ManyToOne
    private Movie movie;
    @ManyToOne
    private ShowRoom showRoom;
    @ManyToOne
    private Seat seat;
    private Double price;

    @OneToMany
    private List<Payment> payments;

    public Ticket(LocalDateTime showTime, User owner, Movie movie, ShowRoom showRoom, Seat seat, Double price) {
        this.showTime = showTime;
        this.owner = owner;
        this.movie = movie;
        this.showRoom = showRoom;
        this.seat = seat;
        this.price = price;
    }

    public Ticket(){

    }

    public Integer getId() {
        return id;
    }

    public LocalDateTime getShowTime() {
        return showTime;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner=owner;
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

    public Boolean isAvailable(){
        return Optional.of(owner).isEmpty();
    }

    public void addPayment(Payment payment){
        payments.add(payment);
    }
    public Payment getLastedPayment(){
        return payments.get(payments.size()-1);
    }

    public String getDescription(){
        return "Ticket number " + id + "Movie: " + movie.getTitle() +
                "Showroom: " + showRoom.getName() +
                "Showtime: " + showTime.toString();
    }

    public Boolean canBeCanceledAtSpecifiedMoment(LocalDateTime cancelTime){
        return cancelTime.isBefore(showTime.minusDays(3));
    }
}
