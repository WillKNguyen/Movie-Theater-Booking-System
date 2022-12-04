package ensf614.project.team6.cinema.api.config;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigResource {

    @GetMapping("/api")
    String heartBeat() {
        return "ENSF614 (Team 6) API is running";
    }
}
