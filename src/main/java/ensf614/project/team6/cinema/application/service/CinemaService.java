package ensf614.project.team6.cinema.application.service;

import ensf614.project.team6.cinema.application.service.exceptions.TicketNotFoundException;
import ensf614.project.team6.cinema.application.service.exceptions.TicketUnavailableException;
import ensf614.project.team6.cinema.application.service.response.MovieResponse;
import ensf614.project.team6.cinema.application.service.response.ScheduleResponse;
import ensf614.project.team6.cinema.application.service.response.SeatResponse;
import ensf614.project.team6.cinema.application.service.response.ShowRoomResponse;
import ensf614.project.team6.cinema.domain.cinema.Ticket;
import ensf614.project.team6.cinema.domain.cinema.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CinemaService extends GlobalService {

    @Autowired
    private TicketRepository ticketRepository;

    //private final static User DEFAULT_USER = new User("To Delete", "todelete@gmail.com", "deleteMe", Collections.singleton(new Role("ROLE_USER")));

    public List<MovieResponse> getAvailableMovies() {
        return ticketRepository.getAllMovies().stream().map(MovieResponse::new).collect(Collectors.toList());
    }

    public List<ShowRoomResponse> getShowRoomsPlayingMovie(String movieId) {
        return ticketRepository.getShowRoomsPlayingMovie(movieId).stream().map(ShowRoomResponse::new).distinct().collect(Collectors.toList());
    }

    public List<ScheduleResponse> getScheduleForMovieInShowRoom(String movieId, String showRoomId) {
        return ticketRepository.getMovieStartTimes(movieId, showRoomId).stream().map(ScheduleResponse::new).distinct().collect(Collectors.toList());
    }

    public List<SeatResponse> getAvailableSeats(String movieId, String showRoomId, LocalDateTime startTime) {
        return ticketRepository.getTickets(movieId, showRoomId, startTime).stream().filter(Ticket::isAvailable).map(SeatResponse::new).distinct().collect(Collectors.toList());
    }

    public void purchaseTicket(String ticketId, String creditCardNumber) {
        Ticket ticket=ticketRepository.findTicketById(ticketId).orElseThrow(TicketNotFoundException::new);

        if(ticket.getOwner()!=null) throw new TicketUnavailableException();

        getBank().processPayment(ticket.getPrice(), creditCardNumber);
        //ticket.setOwner(); TODO Depends on the way we login
        //ticket.setOwner(DEFAULT_USER);

        ticketRepository.saveTicket(ticket);
    }
}
