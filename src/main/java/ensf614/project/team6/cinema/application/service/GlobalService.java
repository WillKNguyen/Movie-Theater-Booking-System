package ensf614.project.team6.cinema.application.service;

import ensf614.project.team6.cinema.domain.bank.Bank;
import ensf614.project.team6.cinema.domain.user.User;
import ensf614.project.team6.cinema.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public abstract class GlobalService {
    @Autowired
    private Bank bank;
    @Autowired
    private UserRepository userRepository;

    protected Bank getBank() {
        return bank;
    }

    protected UserRepository getUserRepository() {
        return userRepository;
    }

    public Optional<User> findUserByEmail(String email) {
        return getUserRepository().findUserByEmail(email);
    }
}
