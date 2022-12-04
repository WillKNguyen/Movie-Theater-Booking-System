package ensf614.project.team6.cinema.api;

import ensf614.project.team6.cinema.application.service.CinemaService;
import ensf614.project.team6.cinema.application.service.response.ScheduleResponse;
import ensf614.project.team6.cinema.application.service.response.MovieResponse;
import ensf614.project.team6.cinema.application.service.response.SeatResponse;
import ensf614.project.team6.cinema.application.service.response.ShowRoomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/public/cinema")
public class CinemaResource {

    @Autowired
    private CinemaService cinemaService;

    @GetMapping("/movies")
    public List<MovieResponse> getMovies() {
        return cinemaService.getAvailableMovies();
    }

    @GetMapping("/show_rooms")
    public List<ShowRoomResponse> getShowRooms(@RequestParam("movie_id") String movieId) {
        return cinemaService.getShowRoomsPlayingMovie(movieId);
    }

    @GetMapping("/schedule")
    public List<ScheduleResponse> getSchedules(@RequestParam("movie_id") String movieId, @RequestParam("show_room_id") String showRoomId) {
        return cinemaService.getScheduleForMovieInShowRoom(movieId, showRoomId);
    }

    @GetMapping("/seats")
    public List<SeatResponse> getSeats(@RequestParam("movie_id") String movieId, @RequestParam("show_room_id") String showRoomId,
                                       @RequestParam("start_time") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime startTime) {
        return cinemaService.getAvailableSeats(movieId, showRoomId, startTime);
    }

    @GetMapping("/purchase_ticket")
    public void purchaseTicket(@RequestParam("ticket_id") String ticketId, @RequestParam("credit_card") String creditCardNumber) {
        cinemaService.purchaseTicket(ticketId, creditCardNumber);
    }
}
