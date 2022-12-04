package ensf614.project.team6.cinema.application.service.response;

import ensf614.project.team6.cinema.domain.cinema.Ticket;
import ensf614.project.team6.cinema.domain.cinema.Seat;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
public class SeatResponse {
    private final String id;
    private final String number;
    private final String relatedTicketId;

    public SeatResponse(Ticket ticket){
        Seat seat=ticket.getSeat();
        this.id=String.valueOf(seat.getId());
        this.number=seat.getNumber();
        this.relatedTicketId=String.valueOf(ticket.getId());
    }
}
