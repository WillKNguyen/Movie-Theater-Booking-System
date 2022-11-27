package ensf614.project.team6.cinema.application.service.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Setter(AccessLevel.NONE)
public class ScheduleResponse {
    private final String moment;

    public ScheduleResponse(LocalDateTime moment){
        this.moment=moment.toString();
    }
}
