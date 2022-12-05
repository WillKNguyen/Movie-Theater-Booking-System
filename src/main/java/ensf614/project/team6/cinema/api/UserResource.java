package ensf614.project.team6.cinema.api;

import ensf614.project.team6.cinema.application.service.UserService;
import ensf614.project.team6.cinema.application.service.request.UserInfoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api")
public class UserResource {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;

    //login is handled by the spring security framework

    @PostMapping("/public/user/register")
    public void registerUser(UserInfoRequest userInfoRequest, HttpServletResponse response) {
        userInfoRequest.setPassword(passwordEncoder.encode(userInfoRequest.getPassword()));
        userService.registerUser(userInfoRequest);
        login(response);
    }

    @GetMapping("/public/user/login")
    public void login(HttpServletResponse response) {
        try {
            response.sendRedirect("/login");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/public/user/logout")
    public void logout(HttpServletResponse response) {
        try {
            response.sendRedirect("/logout");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/private/user/pay_membership")
    public void payMembership(Principal principal) {
        String email = principal.getName();
        userService.payMembership(email);
    }
}
