package ensf614.project.team6.cinema.domain.tickets;

import ensf614.project.team6.cinema.domain.tickets.components.Movie;
import ensf614.project.team6.cinema.domain.tickets.components.Seat;
import ensf614.project.team6.cinema.domain.tickets.components.Theater;
import ensf614.project.team6.cinema.domain.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    private Theater theater;
    @ManyToOne
    private Seat seat;
    private Double price;

    public Ticket(LocalDateTime showTime, User owner, Movie movie, Theater theater, Seat seat, Double price) {
        this.showTime = showTime;
        this.owner = owner;
        this.movie = movie;
        this.theater = theater;
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

    public Theater getTheater() {
        return theater;
    }

    public Seat getSeat() {
        return seat;
    }

    public Double getPrice() {
        return price;
    }


}
