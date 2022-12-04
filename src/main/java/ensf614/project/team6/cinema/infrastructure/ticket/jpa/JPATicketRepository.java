package ensf614.project.team6.cinema.infrastructure.ticket.jpa;

import ensf614.project.team6.cinema.domain.cinema.Movie;
import ensf614.project.team6.cinema.domain.cinema.ShowRoom;
import ensf614.project.team6.cinema.domain.cinema.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public interface JPATicketRepository extends JpaRepository<Ticket, Integer> {
    Optional<Ticket> findById(Integer id);

    @Query(value = "SELECT distinct tic FROM Ticket tic " +
            "inner join Movie m on tic.movie.id = m.id " +
            "inner join ShowRoom sr on tic.showRoom.id = sr.id " +
            "WHERE m.id = :movieId " +
            "AND sr.id = :showRoomId " +
            "AND tic.showTime = :startTime")
    List<Ticket> getTicketsByMovieIdShowRoomIdAndStartTime(String movieId, String showRoomId, LocalDateTime startTime);

    @Query(value = "SELECT distinct tic.showTime FROM Ticket tic " +
            "inner join Movie m on tic.movie.id = m.id " +
            "inner join ShowRoom sr on tic.showRoom.id = sr.id " +
            "WHERE m.id = :movieId " +
            "AND sr.id = :showRoomId " +
            "AND tic.showTime between :from and :to")
    List<LocalDateTime> getMovieStartTimesByMovieIdAndShowRoomId(String movieId, String showRoomId, LocalDateTime from, LocalDateTime to);

    @Query(value = "SELECT distinct sr FROM Ticket tic " +
            "inner join Movie m on tic.movie.id = m.id " +
            "inner join ShowRoom sr on tic.showRoom.id = sr.id " +
            "WHERE m.id = :movieId " +
            "AND tic.showTime between :from and :to")
    List<ShowRoom> getShowRoomsPlayingMovie(String movieId, LocalDateTime from, LocalDateTime to);

    @Query(value = "SELECT distinct m FROM Ticket tic " +
            "inner join Movie m on tic.movie.id = m.id " +
            "where tic.showTime between :from and :to")
    List<Movie> getAllAvailableMovies(LocalDateTime from, LocalDateTime to);
}
