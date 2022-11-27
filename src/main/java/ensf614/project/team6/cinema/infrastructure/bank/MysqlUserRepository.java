package ensf614.project.team6.cinema.infrastructure.bank;

import ensf614.project.team6.cinema.domain.user.User;
import ensf614.project.team6.cinema.domain.user.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MysqlUserRepository implements UserRepository {
    @Override
    public Optional<User> getUserByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public void saveUser(User user) {

    }
}
