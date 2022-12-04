package ensf614.project.team6.cinema.infrastructure.ticket;

import ensf614.project.team6.cinema.domain.cinema.Ticket;
import ensf614.project.team6.cinema.domain.cinema.TicketRepository;
import ensf614.project.team6.cinema.domain.cinema.Movie;
import ensf614.project.team6.cinema.domain.cinema.ShowRoom;
import ensf614.project.team6.cinema.infrastructure.ticket.jpa.JPATicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

@Component
public class JPATicketRepositoryAdapter implements TicketRepository {

    @Autowired
    private JPATicketRepository jpaTicketRepository;

    @Override
    public List<Movie> getAllMovies() {
        return jpaTicketRepository.getAllAvailableMovies();
    }

    @Override
    public List<ShowRoom> getShowRoomsPlayingMovie(String movieId) {
        return jpaTicketRepository.getShowRoomsPlayingMovie(movieId);
    }

    @Override
    public List<LocalDateTime> getMovieStartTimes(String movieId, String showRoomId) {
        return jpaTicketRepository.getMovieStartTimesByMovieIdAndTheater(movieId,showRoomId);
    }

    @Override
    public List<Ticket> getTickets(String movieId, String showRoomId, LocalDateTime startTime) {
        return jpaTicketRepository.getTicketsByMovieIdShowRoomIdAndStartTime(movieId,showRoomId,startTime);

    }

    @Override
    public Optional<Ticket> findTicketById(String ticketId) {
        return jpaTicketRepository.findById(ticketId);
    }

    @Override
    public void saveTicket(Ticket ticket) {
        jpaTicketRepository.save(ticket);
    }

}
