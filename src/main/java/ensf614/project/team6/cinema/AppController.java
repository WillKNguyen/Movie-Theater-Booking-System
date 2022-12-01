package ensf614.project.team6.cinema;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {
    
    @GetMapping("")
    public String index() {
        return "index";
    }

    @GetMapping("movie_list")
    public String movieList() {
        return "movie_list";
    }
}
