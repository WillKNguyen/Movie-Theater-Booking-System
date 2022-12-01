package ensf614.project.team6.cinema.api;

import ensf614.project.team6.cinema.application.service.request.CredentialsRequest;
import ensf614.project.team6.cinema.application.service.request.UserInfoRequest;
import ensf614.project.team6.cinema.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/user")
public class UserResource {

    @Autowired
    private UserService userService;

    //login is handled by the spring security framework

    @PostMapping("/register")
    public void registerUser(@RequestBody UserInfoRequest userInfoRequest) {
        userService.registerUser(userInfoRequest);
    }

    @GetMapping("/login")
    public void login(HttpServletResponse response) {
        try {
            response.sendRedirect("/login");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/registered/pay_membership")
    public void payMembership(@RequestBody CredentialsRequest credentialsRequest) {
        userService.login(credentialsRequest);
    }
}
