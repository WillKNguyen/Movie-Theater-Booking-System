package ensf614.project.team6.cinema.domain.user;

import ensf614.project.team6.cinema.domain.user.exceptions.InvalidEmailFormatException;
import ensf614.project.team6.cinema.domain.user.credentials.PasswordEncryptor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.regex.Pattern;

@Component
public class UserFactory {

    private final static String VALID_EMAIL_REGEX = "^[a-zA-Z][\\w+\\.]+\\w@[a-zA-Z]([\\w+]+\\.)+[a-zA-Z][\\w]{1,3}$";

    public User createUser(String name, String email, String password, Set<Role> roles) {
        validateEmail(email);
        validatePassword(password);

        String encryptedPassword = PasswordEncryptor.encryptPassword(password);

        return new User(name, email, encryptedPassword, roles);
    }

    private void validateEmail(String email) {
        if (!Pattern.matches(VALID_EMAIL_REGEX, email)) throw new InvalidEmailFormatException();
    }

    private void validatePassword(String password){
        //TODO
    }
}
