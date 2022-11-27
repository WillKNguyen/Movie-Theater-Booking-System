package ensf614.project.team6.cinema.application.service;

import ensf614.project.team6.cinema.application.service.request.CredentialsRequest;
import ensf614.project.team6.cinema.application.service.request.UserInfoRequest;
import ensf614.project.team6.cinema.application.service.exceptions.UserAlreadyExistsException;
import ensf614.project.team6.cinema.application.service.exceptions.UserNotFoundException;
import ensf614.project.team6.cinema.domain.user.User;
import ensf614.project.team6.cinema.domain.user.UserFactory;
import ensf614.project.team6.cinema.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserFactory userFactory;

    public void registerUser(UserInfoRequest userInfoRequest) {
        if (userRepository.getUserByEmail(userInfoRequest.getEmail()).isPresent())
            throw new UserAlreadyExistsException();

        String encryptedPassword = Encryptor.encryptPassword(userInfoRequest.getPassword());
        User user = userFactory.create(userInfoRequest.getName(), userInfoRequest.getEmail(), encryptedPassword);

        userRepository.saveUser(user);
    }

    public void login(CredentialsRequest credentialsRequest) {
        User user = userRepository.getUserByEmail(credentialsRequest.getEmail()).orElseThrow(UserNotFoundException::new);
        //TODO
    }
}
