package ensf614.project.team6.cinema.application.service.response;

import ensf614.project.team6.cinema.domain.cinema.ShowRoom;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
public class ShowRoomResponse {
    private final String id;
    private final String name;

    public ShowRoomResponse(ShowRoom showRoom){
        this.id=String.valueOf(showRoom.getId());
        this.name= showRoom.getName();
    }
}
