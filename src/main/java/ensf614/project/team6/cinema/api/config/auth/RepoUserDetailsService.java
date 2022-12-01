package ensf614.project.team6.cinema.api.config.auth;

import ensf614.project.team6.cinema.application.service.UserService;
import ensf614.project.team6.cinema.application.service.exceptions.UserNotFoundException;
import ensf614.project.team6.cinema.domain.user.User;
import ensf614.project.team6.cinema.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class RepoUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {

        User domainUser;
        try {
            domainUser = userService.findUserByEmail(usernameOrEmail).orElseThrow(UserNotFoundException::new);
        } catch (UserNotFoundException e) {
            throw new UsernameNotFoundException("Bad credentials");
        }

        return new org.springframework.security.core.userdetails.User(domainUser.getEmail(), domainUser.getPassword(),
                domainUser.getRoles().stream().map((role) -> new SimpleGrantedAuthority(role.getTitle()))
                        .collect(Collectors.toList()));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}