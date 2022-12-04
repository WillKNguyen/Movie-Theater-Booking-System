package ensf614.project.team6.cinema.domain.cinema;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public interface TicketRepository {
    List<Movie> getAllMovies(LocalDateTime from, LocalDateTime to);
    List<ShowRoom> getShowRoomsPlayingMovie(String movieId, LocalDateTime from, LocalDateTime to);
    List<LocalDateTime> getMovieStartTimes(String movieId, String showRoomId, LocalDateTime from, LocalDateTime to);
    List<Ticket> getTickets(String movieId, String showRoomId, LocalDateTime startTime);

    Optional<Ticket> findTicketById(String ticketId);

    void saveTicket(Ticket ticket);
}
