package ensf614.project.team6.cinema.api;

import ensf614.project.team6.cinema.application.service.TicketService;
import ensf614.project.team6.cinema.application.service.response.ScheduleResponse;
import ensf614.project.team6.cinema.application.service.response.MovieResponse;
import ensf614.project.team6.cinema.application.service.response.SeatResponse;
import ensf614.project.team6.cinema.application.service.response.TheaterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/cinema")
public class TicketResource {

    @Autowired
    private TicketService ticketService;

    @GetMapping("/movies")
    public List<MovieResponse> getMovies() {
        return ticketService.getAvailableMovies();
    }

    @GetMapping("/theaters")
    public List<TheaterResponse> getTheaters(@RequestParam("movie_id") String movieId) {
        return ticketService.getTheatersPlayingMovie(movieId);
    }

    @GetMapping("/schedule")
    public List<ScheduleResponse> getSchedules(@RequestParam("movie_id") String movieId, @RequestParam("theater_id") String theaterId) {
        return ticketService.getScheduleForMovieAtTheater(movieId, theaterId);
    }

    @GetMapping("/seats")
    public List<SeatResponse> getSeats(@RequestParam("movie_id") String movieId, @RequestParam("theater_id") String theaterId,
                                       @RequestParam("start_time") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime startTime) {
        return ticketService.getAvailableSeats(movieId, theaterId, startTime);
    }

    @GetMapping("/purchase_ticket")
    public void purchaseTicket(@RequestParam("ticket_id") String ticketId, @RequestParam("credit_card") String creditCardNumber) {
        ticketService.purchaseTicket(ticketId, creditCardNumber);
    }
}
