package ensf614.project.team6.cinema.gui;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GuiController {

    @GetMapping("")
    public String index(Principal princpial) {
        try {
            princpial.getName();
        }
        catch(Exception nullPointerException) {
            return "index";
        }
        return "index_auth";

    }

    @GetMapping("movie_list")
    public String movieList() {
        return "movie_list";
    }

    @GetMapping("register")
    public String register() {
        return "register";
    }

    @GetMapping("checkout")
    public String checkout(Principal princpial) {
        try {
            princpial.getName();
        }
        catch(Exception nullPointerException) {
            return "purchase_ticket";
        }
        return "purchase_ticket_auth";

    }

    @GetMapping("refund")
    public String refund() {
        return "refund";
    }
}
