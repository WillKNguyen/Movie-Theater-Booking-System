package ensf614.project.team6.cinema.api.config.auth;

import ensf614.project.team6.cinema.application.service.exceptions.UserNotFoundException;
import ensf614.project.team6.cinema.domain.user.User;
import ensf614.project.team6.cinema.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class RepoUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public RepoUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UserNotFoundException {
        User domainUser = userRepository.findUserByEmail(usernameOrEmail).orElseThrow(UserNotFoundException::new);

        return new org.springframework.security.core.userdetails.User(domainUser.getEmail(), domainUser.getPassword(),
                domainUser.getRoles().stream().map((role) -> new SimpleGrantedAuthority(role.getTitle()))
                        .collect(Collectors.toList()));
    }
}