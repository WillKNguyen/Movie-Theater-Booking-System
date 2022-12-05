package ensf614.project.team6.cinema.infrastructure.cinema;

import ensf614.project.team6.cinema.domain.cinema.Movie;
import ensf614.project.team6.cinema.domain.cinema.ShowRoom;
import ensf614.project.team6.cinema.domain.cinema.Ticket;
import ensf614.project.team6.cinema.domain.cinema.TicketRepository;
import ensf614.project.team6.cinema.infrastructure.cinema.jpa.JPATicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JPATicketRepositoryAdapter implements TicketRepository {

    @Autowired
    private JPATicketRepository jpaTicketRepository;

    @Override
    public List<Movie> getAllMovies(LocalDateTime from, LocalDateTime to) {
        return jpaTicketRepository.getAllAvailableMovies(from, to);
    }

    @Override
    public List<ShowRoom> getShowRoomsPlayingMovie(String movieId, LocalDateTime from, LocalDateTime to) {
        return jpaTicketRepository.getShowRoomsPlayingMovie(Integer.valueOf(movieId), from, to);
    }

    @Override
    public List<LocalDateTime> getMovieStartTimes(String movieId, String showRoomId, LocalDateTime from, LocalDateTime to) {
        return jpaTicketRepository.getMovieStartTimesByMovieIdAndShowRoomId(Integer.valueOf(movieId), Integer.valueOf(showRoomId), from, to);
    }

    @Override
    public List<Ticket> getTickets(String movieId, String showRoomId, LocalDateTime startTime) {
        return jpaTicketRepository.getTicketsByMovieIdShowRoomIdAndStartTime(Integer.valueOf(movieId), Integer.valueOf(showRoomId), startTime);

    }

    @Override
    public Optional<Ticket> findTicketById(String ticketId) {
        return jpaTicketRepository.findById(Integer.valueOf(ticketId));
    }

    @Override
    public void saveTicket(Ticket ticket) {
        jpaTicketRepository.save(ticket);
    }

}
