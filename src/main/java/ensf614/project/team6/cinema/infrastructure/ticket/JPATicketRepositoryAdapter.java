package ensf614.project.team6.cinema.infrastructure.ticket;

import ensf614.project.team6.cinema.domain.tickets.Ticket;
import ensf614.project.team6.cinema.domain.tickets.TicketRepository;
import ensf614.project.team6.cinema.domain.tickets.components.Movie;
import ensf614.project.team6.cinema.domain.tickets.components.Theater;
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
    public List<Theater> getTheatersPlayingMovie(String movieId) {
        return jpaTicketRepository.getTheatersPlayingMovie(movieId);
    }

    @Override
    public List<LocalDateTime> getMovieStartTimes(String movieId, String theaterId) {
        return jpaTicketRepository.getMovieStartTimesByMovieIdAndTheater(movieId,theaterId);
    }

    @Override
    public List<Ticket> getTickets(String movieId, String theaterId, LocalDateTime startTime) {
        return jpaTicketRepository.getTicketsByMovieIdTheaterIdAndStartTime(movieId,theaterId,startTime);

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
