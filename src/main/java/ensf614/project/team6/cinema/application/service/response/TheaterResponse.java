package ensf614.project.team6.cinema.application.service.response;

import ensf614.project.team6.cinema.domain.tickets.components.Theater;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
public class TheaterResponse {
    private final String id;
    private final String name;

    public TheaterResponse(Theater theater){
        this.id=theater.getId();
        this.name=theater.getName();
    }
}
