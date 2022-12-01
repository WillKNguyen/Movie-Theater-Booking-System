package ensf614.project.team6.cinema.application.service;

import ensf614.project.team6.cinema.application.service.exceptions.RoleNotFoundException;
import ensf614.project.team6.cinema.application.service.request.CredentialsRequest;
import ensf614.project.team6.cinema.application.service.request.UserInfoRequest;
import ensf614.project.team6.cinema.application.service.exceptions.UserAlreadyExistsException;
import ensf614.project.team6.cinema.application.service.exceptions.UserNotFoundException;

import ensf614.project.team6.cinema.domain.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;

@Component
public class UserService {

    private final static String USER_ROLE="ROLE_USER";

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserFactory userFactory;

    public void registerUser(UserInfoRequest userInfoRequest) {
        if (userRepository.findUserByEmail(userInfoRequest.getEmail()).isPresent())
            throw new UserAlreadyExistsException();

        Role role=roleRepository.findRoleByTitle(USER_ROLE).orElseThrow(RoleNotFoundException::new);

        User user = userFactory.createUser(userInfoRequest.getName(), userInfoRequest.getEmail(), userInfoRequest.getPassword(), Collections.singleton(role));

        userRepository.saveUser(user);
    }

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }
}
