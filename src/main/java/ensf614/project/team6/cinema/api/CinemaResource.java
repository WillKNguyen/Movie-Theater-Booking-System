package ensf614.project.team6.cinema.api;

import ensf614.project.team6.cinema.application.service.CinemaService;
import ensf614.project.team6.cinema.application.service.response.ScheduleResponse;
import ensf614.project.team6.cinema.application.service.response.MovieResponse;
import ensf614.project.team6.cinema.application.service.response.SeatResponse;
import ensf614.project.team6.cinema.application.service.response.ShowRoomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/")
public class CinemaResource {

    @Autowired
    private CinemaService cinemaService;

    @GetMapping("/public/cinema/movies")
    public List<MovieResponse> getMovies() {
        return cinemaService.getAvailableMovies();
    }

    @GetMapping("/public/cinemashow_rooms")
    public List<ShowRoomResponse> getShowRooms(@RequestParam("movie_id") String movieId) {
        return cinemaService.getShowRoomsPlayingMovie(movieId);
    }

    @GetMapping("/public/cinemaschedule")
    public List<ScheduleResponse> getSchedules(@RequestParam("movie_id") String movieId, @RequestParam("show_room_id") String showRoomId) {
        return cinemaService.getScheduleForMovieInShowRoom(movieId, showRoomId);
    }

    @GetMapping("/public/cinemaseats")
    public List<SeatResponse> getSeats(@RequestParam("movie_id") String movieId, @RequestParam("show_room_id") String showRoomId,
                                       @RequestParam("start_time") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime startTime) {
        return cinemaService.getAvailableSeats(movieId, showRoomId, startTime);
    }

    @GetMapping("/private/cinema/purchase_ticket")
    public void purchaseTicketAsAuthenticatedUser(@RequestParam("ticket_id") String ticketId, Principal principal) {
        cinemaService.purchaseTicketAsAuthenticatedUser(ticketId, principal.getName());
    }

    @GetMapping("/public/cinema/purchase_ticket")
    public void purchaseTicketAnonymously(@RequestParam("ticket_id") String ticketId,
                                          @RequestParam("credit_card") String creditCardNumber, @RequestParam("email") String email) {
        cinemaService.purchaseTicketAnonymously(ticketId, creditCardNumber, email);
    }

    @GetMapping("/public/cinema/cancel_ticket")
    public void cancelTicketAnonymously(@RequestParam("ticket_id") String ticketId) {
        cinemaService.cancelTicketAnonymously(ticketId);
    }
}
