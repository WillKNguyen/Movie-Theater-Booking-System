package ensf614.project.team6.cinema.infrastructure.ticket.jpa;

import ensf614.project.team6.cinema.domain.tickets.Ticket;
import ensf614.project.team6.cinema.domain.tickets.components.Movie;
import ensf614.project.team6.cinema.domain.tickets.components.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public interface JPATicketRepository extends JpaRepository<Ticket, Integer> {
    Optional<Ticket> findById(String id);

    @Query(value = "SELECT distinct tic FROM Ticket tic " +
            "inner join Movie m on tic.movie.id = m.id " +
            "inner join Theater th on tic.theater.id = th.id "+
            "WHERE m.id = :movieId " +
            "AND th.id = :theaterId "+
            "AND tic.showTime = :startTime")
    List<Ticket> getTicketsByMovieIdTheaterIdAndStartTime(String movieId, String theaterId, LocalDateTime startTime);

    @Query(value = "SELECT distinct tic.showTime FROM Ticket tic " +
            "inner join Movie m on tic.movie.id = m.id " +
            "inner join Theater th on tic.theater.id = th.id "+
            "WHERE m.id = :movieId " +
            "AND th.id = :theaterId")
    List<LocalDateTime> getMovieStartTimesByMovieIdAndTheater(String movieId, String theaterId);

    @Query(value = "SELECT distinct th FROM Ticket tic " +
            "inner join Movie m on tic.movie.id = m.id " +
            "inner join Theater th on tic.theater.id = th.id "+
            "WHERE m.id = :movieId")
    List<Theater> getTheatersPlayingMovie(String movieId);

    @Query(value = "SELECT distinct m FROM Ticket tic " +
            "inner join Movie m on tic.movie.id = m.id")
    List<Movie> getAllAvailableMovies();
}
