package ensf614.project.team6.cinema.infrastructure.bank;

import ensf614.project.team6.cinema.domain.tickets.Ticket;
import ensf614.project.team6.cinema.domain.tickets.TicketRepository;
import ensf614.project.team6.cinema.domain.tickets.components.Movie;
import ensf614.project.team6.cinema.domain.tickets.components.Seat;
import ensf614.project.team6.cinema.domain.tickets.components.Theater;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class MysqlTicketRepository implements TicketRepository {

    private final static List<Movie> DUMMY_MOVIES = Arrays.asList(new Movie("1", "Black Panther 2"), new Movie("2", "ENSF Stories"), new Movie("3", "La vie en rose"), new Movie("4", "Marley and Me"), new Movie("5", "Pixel"));

    private final static List<Theater> DUMMY_THEATERS = Arrays.asList(new Theater("1", "Cineplex VIP"), new Theater("2", "Cineplex Brentwood"));

    private final static List<LocalDateTime> DUMMY_MOMENTS = Arrays.asList(LocalDateTime.of(2022, 12, 3, 12, 0), LocalDateTime.of(2022, 12, 3, 15, 30), LocalDateTime.of(2022, 12, 3, 18, 0), LocalDateTime.of(2022, 12, 3, 22, 30), LocalDateTime.of(2022, 12, 3, 23, 30), LocalDateTime.of(2022, 12, 4, 15, 0), LocalDateTime.of(2022, 12, 4, 18, 0), LocalDateTime.of(2022, 12, 4, 21, 30), LocalDateTime.of(2022, 12, 5, 12, 0));

    private final static List<Seat> DUMMY_SEATS = Arrays.asList(new Seat("1", "1"), new Seat("2", "2"), new Seat("3", "3"), new Seat("4", "4"), new Seat("5", "5"), new Seat("6", "6"), new Seat("7", "7"), new Seat("8", "8"), new Seat("9", "9"), new Seat("10", "10"));

    private final List<Ticket> dummyTickets;

    public MysqlTicketRepository() {
        Random random = new Random();
        int idTicket=1;
        dummyTickets = new ArrayList<>();


        for (Movie movie : DUMMY_MOVIES) {

            for (Theater theater : DUMMY_THEATERS) {

                for (LocalDateTime moment : DUMMY_MOMENTS) {

                    for (Seat seat : DUMMY_SEATS) {

                        if (random.nextInt(3) < 2) {
                            dummyTickets.add(new Ticket(String.valueOf(idTicket++), moment, null, movie, theater, seat, 14.50));
                        }
                    }
                }
            }
        }
    }

    @Override
    public List<Movie> getAllMovies() {
        return DUMMY_MOVIES;
    }

    @Override
    public List<Theater> getTheatersPlayingMovie(String movieId) {
        return dummyTickets.stream().filter(ticket -> ticket.getMovie().getId().equals(movieId)).map(Ticket::getTheater).collect(Collectors.toList());
    }

    @Override
    public List<LocalDateTime> getMovieStartTimes(String movieId, String theaterId) {
        return dummyTickets.stream().filter(ticket -> ticket.getMovie().getId().equals(movieId)
                        && ticket.getTheater().getId().equals(theaterId))
                .map(Ticket::getShowTime).collect(Collectors.toList());
    }

    @Override
    public List<Ticket> getTickets(String movieId, String theaterId, LocalDateTime startTime) {
        return dummyTickets.stream().filter(ticket -> ticket.getMovie().getId().equals(movieId)
                && ticket.getTheater().getId().equals(theaterId)
                && ticket.getShowTime().equals(startTime)).collect(Collectors.toList());

    }

    @Override
    public Optional<Ticket> getTicketById(String ticketId) {
        List<Ticket> tickets = dummyTickets.stream().filter(ticket -> ticket.getId().equals(ticketId)).collect(Collectors.toList());

        if (tickets.size() == 0) {
            return Optional.empty();
        } else {
            return Optional.of(tickets.get(0));
        }
    }

    @Override
    public void saveTicket(Ticket ticket) {
        List<Ticket> tickets = dummyTickets.stream().filter(currentTicket -> currentTicket.getId().equals(ticket.getId())).collect(Collectors.toList());

        if (tickets.size() == 0) {
            dummyTickets.add(ticket);
        } else {
            int index = dummyTickets.indexOf(tickets.get(0));
            dummyTickets.remove(index);
            dummyTickets.add(tickets.get(0));
        }
    }

}
