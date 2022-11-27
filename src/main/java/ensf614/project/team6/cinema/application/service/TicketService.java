package ensf614.project.team6.cinema.application.service;

import ensf614.project.team6.cinema.application.service.exceptions.TicketNotFoundException;
import ensf614.project.team6.cinema.application.service.exceptions.TicketUnavailableException;
import ensf614.project.team6.cinema.application.service.response.MovieResponse;
import ensf614.project.team6.cinema.application.service.response.ScheduleResponse;
import ensf614.project.team6.cinema.application.service.response.SeatResponse;
import ensf614.project.team6.cinema.application.service.response.TheaterResponse;
import ensf614.project.team6.cinema.domain.bank.Bank;
import ensf614.project.team6.cinema.domain.tickets.Ticket;
import ensf614.project.team6.cinema.domain.tickets.TicketRepository;
import ensf614.project.team6.cinema.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private Bank bank;

    private final static User DEFAULT_USER = new User("To Delete", "todelete@gmail.com", "deleteMe");

    public List<MovieResponse> getAvailableMovies() {
        return ticketRepository.getAllMovies().stream().map(MovieResponse::new).collect(Collectors.toList());
    }

    public List<TheaterResponse> getTheatersPlayingMovie(String movieId) {
        return ticketRepository.getTheatersPlayingMovie(movieId).stream().map(TheaterResponse::new).distinct().collect(Collectors.toList());
    }

    public List<ScheduleResponse> getScheduleForMovieAtTheater(String movieId, String theaterId) {
        return ticketRepository.getMovieStartTimes(movieId, theaterId).stream().map(ScheduleResponse::new).distinct().collect(Collectors.toList());
    }

    public List<SeatResponse> getAvailableSeats(String movieId, String theaterId, LocalDateTime startTime) {
        return ticketRepository.getTickets(movieId, theaterId, startTime).stream().map(SeatResponse::new).distinct().collect(Collectors.toList());
    }

    public void purchaseTicket(String ticketId, String creditCardNumber) {
        Ticket ticket=ticketRepository.getTicketById(ticketId).orElseThrow(TicketNotFoundException::new);

        if(ticket.getOwner()!=null) throw new TicketUnavailableException();

        bank.processPayment(ticket.getPrice(), creditCardNumber);
        //ticket.setOwner(); TODO Depends on the way we login
        ticket.setOwner(DEFAULT_USER);

        ticketRepository.saveTicket(ticket);
    }
}
