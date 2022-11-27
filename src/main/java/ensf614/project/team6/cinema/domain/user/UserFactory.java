package ensf614.project.team6.cinema.domain.user;

import ensf614.project.team6.cinema.domain.user.exceptions.InvalidEmailFormatException;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class UserFactory {

    private final static String VALID_EMAIL_REGEX = "^[a-zA-Z][\\w-\\.]+\\w@[a-zA-Z]([\\w-]+\\.)+[\\w-]{2,4}$";

    public User create(String name, String email, String password) {
        validateEmail(email);

        return new User(name, email, password);
    }

    private void validateEmail(String email) {
        if (Pattern.matches(VALID_EMAIL_REGEX, email)) throw new InvalidEmailFormatException();
    }
}
