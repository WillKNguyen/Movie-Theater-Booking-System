package ensf614.project.team6.cinema.application.service;

import ensf614.project.team6.cinema.application.service.exceptions.TicketNotFoundException;
import ensf614.project.team6.cinema.application.service.exceptions.TicketUnavailableException;
import ensf614.project.team6.cinema.application.service.exceptions.UserNotFoundException;
import ensf614.project.team6.cinema.application.service.response.MovieResponse;
import ensf614.project.team6.cinema.application.service.response.ScheduleResponse;
import ensf614.project.team6.cinema.application.service.response.SeatResponse;
import ensf614.project.team6.cinema.application.service.response.ShowRoomResponse;
import ensf614.project.team6.cinema.domain.bank.Payment;
import ensf614.project.team6.cinema.domain.bank.exceptions.PaymentAlreadyRefunded;
import ensf614.project.team6.cinema.domain.bank.exceptions.RefundNotAvailable;
import ensf614.project.team6.cinema.domain.cinema.Ticket;
import ensf614.project.team6.cinema.domain.cinema.TicketRepository;
import ensf614.project.team6.cinema.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CinemaService extends GlobalService {

    private final static int ANONYMOUS_NBR_DAYS_IN_FUTURE=14;
    private final static int AUTHENTICATED_EXTRA_DAYS=7;
    @Autowired
    private TicketRepository ticketRepository;

    public List<MovieResponse> getAvailableMovies() {
        LocalDateTime start=LocalDateTime.now();
        return ticketRepository.getAllMovies(start,start.plusDays(ANONYMOUS_NBR_DAYS_IN_FUTURE)).stream().map(MovieResponse::new).collect(Collectors.toList());
    }

    public List<ShowRoomResponse> getShowRoomsPlayingMovie(String movieId) {
        LocalDateTime start=LocalDateTime.now();
        return ticketRepository.getShowRoomsPlayingMovie(movieId,start,start.plusDays(ANONYMOUS_NBR_DAYS_IN_FUTURE)).stream().map(ShowRoomResponse::new).distinct().collect(Collectors.toList());
    }

    public List<ScheduleResponse> getScheduleForMovieInShowRoom(String movieId, String showRoomId) {
        LocalDateTime start=LocalDateTime.now();
        return ticketRepository.getMovieStartTimes(movieId, showRoomId, start,start.plusDays(ANONYMOUS_NBR_DAYS_IN_FUTURE)).stream().map(ScheduleResponse::new).distinct().collect(Collectors.toList());
    }

    public List<SeatResponse> getAvailableSeats(String movieId, String showRoomId, LocalDateTime startTime) {
        return ticketRepository.getTickets(movieId, showRoomId, startTime).stream().filter(Ticket::isAvailable).map(SeatResponse::new).distinct().collect(Collectors.toList());
    }

    public void purchaseTicketAsAuthenticatedUser(String ticketId, String email) {
        User user=getUserRepository().findUserByEmail(email).orElseThrow(UserNotFoundException::new);
        processTicketPurchase(ticketId, user.getCreditCardNumber(), email);
    }

    public void purchaseTicketAnonymously(String ticketId, String creditCardNumber, String email) {
        processTicketPurchase(ticketId, creditCardNumber, email);
    }

    private void processTicketPurchase(String ticketId, String creditCardNumber, String email){
        Ticket ticket=ticketRepository.findTicketById(ticketId).orElseThrow(TicketNotFoundException::new);

        if(!ticket.isAvailable()) throw new TicketUnavailableException();

        Payment payment=getBank().processPayment(ticket.getPrice(), creditCardNumber, email, ticket.getDescription());

        ticket.addPayment(payment);

        ticketRepository.saveTicket(ticket);
    }

    public void cancelTicketAnonymously(String ticketId) {
        Ticket ticket=ticketRepository.findTicketById(ticketId).orElseThrow(TicketNotFoundException::new);

        if(ticket.isAvailable() || !ticket.canBeCanceledAtSpecifiedMoment(LocalDateTime.now())) throw new RefundNotAvailable();

        Payment payment=ticket.getLastedPayment();

        Optional<User> user=getUserRepository().findUserByEmail(payment.getContactEmail());
        double refundedPrc=85;
        if(user.isPresent()){
            refundedPrc=100;
        }
        getBank().cancelPayment(payment,refundedPrc);

        ticketRepository.saveTicket(ticket);
    }

}
