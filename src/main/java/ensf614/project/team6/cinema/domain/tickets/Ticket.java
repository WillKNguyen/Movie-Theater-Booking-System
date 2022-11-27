package ensf614.project.team6.cinema.domain.tickets;

import ensf614.project.team6.cinema.domain.tickets.components.Movie;
import ensf614.project.team6.cinema.domain.tickets.components.Seat;
import ensf614.project.team6.cinema.domain.tickets.components.Theater;
import ensf614.project.team6.cinema.domain.user.User;

import java.time.LocalDateTime;

public class Ticket {
    private String id;
    private LocalDateTime showTime;
    private User owner;
    private Movie movie;
    private Theater theater;
    private Seat seat;
    private Double price;

    public Ticket(String id, LocalDateTime showTime, User owner, Movie movie, Theater theater, Seat seat, Double price) {
        this.id = id;
        this.showTime = showTime;
        this.owner = owner;
        this.movie = movie;
        this.theater = theater;
        this.seat = seat;
        this.price = price;
    }

    public String getId() {
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
