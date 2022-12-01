package ensf614.project.team6.cinema.domain.tickets;

import ensf614.project.team6.cinema.domain.tickets.components.Movie;
import ensf614.project.team6.cinema.domain.tickets.components.Theater;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public interface TicketRepository {
    List<Movie> getAllMovies();
    List<Theater> getTheatersPlayingMovie(String movieId);
    List<LocalDateTime> getMovieStartTimes(String movieId, String theaterId);
    List<Ticket> getTickets(String movieId, String theaterId, LocalDateTime startTime);

    Optional<Ticket> findTicketById(String ticketId);

    void saveTicket(Ticket ticket);
}
