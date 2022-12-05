package ensf614.project.team6.cinema.application.service;

import ensf614.project.team6.cinema.application.service.exceptions.TicketNotFoundException;
import ensf614.project.team6.cinema.application.service.exceptions.TicketUnavailableException;
import ensf614.project.team6.cinema.application.service.exceptions.UserNotFoundException;
import ensf614.project.team6.cinema.application.service.response.MovieResponse;
import ensf614.project.team6.cinema.application.service.response.ScheduleResponse;
import ensf614.project.team6.cinema.application.service.response.SeatResponse;
import ensf614.project.team6.cinema.application.service.response.ShowRoomResponse;
import ensf614.project.team6.cinema.domain.bank.Payment;
import ensf614.project.team6.cinema.domain.bank.exceptions.RefundNotAvailable;
import ensf614.project.team6.cinema.domain.cinema.ShowRoom;
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

    private final static int NBR_DAYS_FUTURE_AVAILABILITY = 14;
    private final static int AUTHENTICATED_EXTRA_DAYS = 7;
    private final static int MAX_PRC_RESERVED_SEATS = 10;
    @Autowired
    private TicketRepository ticketRepository;

    public List<MovieResponse> getAvailableMovies(boolean isAuthenticated) {
        LocalDateTime[] availabilityPeriod = getStartAndEndDateForSearch(isAuthenticated);
        return ticketRepository.getAllMovies(availabilityPeriod[0], availabilityPeriod[1]).stream().map(MovieResponse::new)
                .collect(Collectors.toList());
    }

    public List<ShowRoomResponse> getShowRoomsPlayingMovie(String movieId, boolean isAuthenticated) {
        LocalDateTime[] availabilityPeriod = getStartAndEndDateForSearch(isAuthenticated);
        return ticketRepository.getShowRoomsPlayingMovie(movieId, availabilityPeriod[0], availabilityPeriod[1]).stream()
                .filter(ShowRoom::isAvailableForScreening).map(ShowRoomResponse::new).distinct().collect(Collectors.toList());
    }

    public List<ScheduleResponse> getScheduleForMovieInShowRoom(String movieId, String showRoomId, boolean isAuthenticated) {
        LocalDateTime[] availabilityPeriod = getStartAndEndDateForSearch(isAuthenticated);
        return ticketRepository.getMovieStartTimes(movieId, showRoomId, availabilityPeriod[0], availabilityPeriod[1]).stream()
                .map(ScheduleResponse::new).distinct().collect(Collectors.toList());
    }

    public List<SeatResponse> getAvailableSeats(String movieId, String showRoomId, LocalDateTime startTime) {
        List<Ticket> consideredTickets = ticketRepository.getTickets(movieId, showRoomId, startTime);

        int totalNbrSeats = consideredTickets.size();
        int reservedSeats = 0;
        LocalDateTime maxFutureAvailability = LocalDateTime.now().plusDays(NBR_DAYS_FUTURE_AVAILABILITY);

        for (Ticket ticket : consideredTickets) {
            if (ticket.getShowTime().isAfter(maxFutureAvailability)) {
                reservedSeats++;
            }
        }

        boolean showReservationSeats = (reservedSeats + 1) / totalNbrSeats >= MAX_PRC_RESERVED_SEATS / 100;

        return consideredTickets.stream().filter(Ticket::isAvailable)
                .filter(ticket -> showReservationSeats || ticket.getShowTime().isBefore(maxFutureAvailability))
                .map(SeatResponse::new).distinct().collect(Collectors.toList());
    }

    public void purchaseTicketAsAuthenticatedUser(String ticketId, String email) {
        User user = findUserByEmail(email).orElseThrow(UserNotFoundException::new);
        processTicketPurchase(ticketId, user.getCreditCardNumber(), email);
    }

    public void purchaseTicketAnonymously(String ticketId, String creditCardNumber, String email) {
        processTicketPurchase(ticketId, creditCardNumber, email);
    }

    private void processTicketPurchase(String ticketId, String creditCardNumber, String email) {
        Ticket ticket = ticketRepository.findTicketById(ticketId).orElseThrow(TicketNotFoundException::new);

        if (!ticket.isAvailable()) throw new TicketUnavailableException();

        Payment payment = getBank().processPayment(ticket.getPrice(), creditCardNumber, email, ticket.getDescription());

        ticket.addPayment(payment);
        ticket.setAvailability(false);

        ticketRepository.saveTicket(ticket);
    }

    public void cancelTicketAnonymously(String ticketId) {
        Ticket ticket = ticketRepository.findTicketById(ticketId).orElseThrow(TicketNotFoundException::new);

        if (ticket.isAvailable() || !ticket.canBeCanceledAtSpecifiedMoment(LocalDateTime.now()))
            throw new RefundNotAvailable();

        Payment payment = ticket.getLastedPayment();

        Optional<User> user = findUserByEmail(payment.getContactEmail());
        double refundedPrc = 85;
        if (user.isPresent()) {
            refundedPrc = 100;
        }
        getBank().cancelPayment(payment, refundedPrc);

        ticket.setAvailability(true);

        ticketRepository.saveTicket(ticket);
    }

    private LocalDateTime[] getStartAndEndDateForSearch(boolean isAuthenticated) {
        int forecastDays = NBR_DAYS_FUTURE_AVAILABILITY;
        if (isAuthenticated)
            forecastDays += AUTHENTICATED_EXTRA_DAYS;

        LocalDateTime start = LocalDateTime.now();
        return new LocalDateTime[]{start, start.plusDays(forecastDays)};
    }
}
