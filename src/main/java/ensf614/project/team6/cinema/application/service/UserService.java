package ensf614.project.team6.cinema.application.service;

import ensf614.project.team6.cinema.application.service.exceptions.RoleNotFoundException;
import ensf614.project.team6.cinema.application.service.exceptions.UserAlreadyExistsException;
import ensf614.project.team6.cinema.application.service.exceptions.UserNotFoundException;
import ensf614.project.team6.cinema.application.service.request.UserInfoRequest;
import ensf614.project.team6.cinema.domain.bank.Payment;
import ensf614.project.team6.cinema.domain.user.Role;
import ensf614.project.team6.cinema.domain.user.RoleRepository;
import ensf614.project.team6.cinema.domain.user.User;
import ensf614.project.team6.cinema.domain.user.UserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Collections;

@Component
public class UserService extends GlobalService {

    private final static String USER_ROLE = "ROLE_USER";
    private final static Double MEMBERSHIP_FEE = 20.0;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserFactory userFactory;

    public void registerUser(UserInfoRequest userInfoRequest) {
        if (findUserByEmail(userInfoRequest.getEmail()).isPresent())
            throw new UserAlreadyExistsException();

        Role role = roleRepository.findRoleByTitle(USER_ROLE).orElseThrow(RoleNotFoundException::new);

        User user = userFactory.createUser(userInfoRequest.getName(), userInfoRequest.getEmail(),
                userInfoRequest.getPassword(), userInfoRequest.getCreditCardNumber(), Collections.singleton(role));

        getUserRepository().saveUser(user);
    }

    public void payMembership(String email) {
        User user = findUserByEmail(email).orElseThrow(UserNotFoundException::new);

        Payment payment = getBank().processPayment(MEMBERSHIP_FEE, user.getCreditCardNumber(), email, "Yearly Membership");

        user.addMembershipPayment(payment);
        user.setEndOfSubscription(LocalDate.now().plusYears(1));

        getUserRepository().saveUser(user);
    }
}
