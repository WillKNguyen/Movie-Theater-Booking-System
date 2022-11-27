package ensf614.project.team6.cinema.api;

import ensf614.project.team6.cinema.application.service.request.CredentialsRequest;
import ensf614.project.team6.cinema.application.service.request.UserInfoRequest;
import ensf614.project.team6.cinema.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/user")
public class UserResource {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public void registerUser(@RequestBody UserInfoRequest userInfoRequest) {
        userService.registerUser(userInfoRequest);
    }

    @PostMapping("/login")
    public void loginUser(@RequestBody CredentialsRequest credentialsRequest) {
        userService.login(credentialsRequest);
    }
}
