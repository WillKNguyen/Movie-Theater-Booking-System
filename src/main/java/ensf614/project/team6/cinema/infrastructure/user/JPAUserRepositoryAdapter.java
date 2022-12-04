package ensf614.project.team6.cinema.infrastructure.user;

import ensf614.project.team6.cinema.domain.user.User;
import ensf614.project.team6.cinema.domain.user.UserRepository;
import ensf614.project.team6.cinema.infrastructure.user.jpa.JPAUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class JPAUserRepositoryAdapter implements UserRepository {

    private final static List<String> DEFAULT_NAMES = List.of("admin@ensf614.ca");
    private final static List<String> DEFAULT_EMAILS = List.of("admin@ensf614.ca");
    private final static List<String> DEFAULT_PASSWORDS = List.of("admin@ensf614.ca");

    private final static List<Set<String>> DEFAULT_ROLES = new ArrayList<>() {{
        add(new HashSet<>(List.of("ROLE_ADMIN")));
    }};

    @Autowired
    private JPAUserRepository jpaUserRepository;

    @Autowired
    private JPARoleRepositoryAdapter jpaRoleRepositoryAdapter;

    public JPAUserRepositoryAdapter() {
//        if (DEFAULT_EMAILS.size() == DEFAULT_NAMES.size() && DEFAULT_EMAILS.size() == DEFAULT_PASSWORDS.size()
//                && DEFAULT_EMAILS.size() == DEFAULT_ROLES.size())
//
//            for (int i = 0; i < DEFAULT_EMAILS.size(); i++) {
//                if (jpaUserRepository.findByEmail(DEFAULT_EMAILS.get(i)).isEmpty()) {
//                    try {
//                        Set<Role> role = DEFAULT_ROLES.get(i).stream().map(roleTitle -> jpaRoleRepositoryAdapter.findRoleByTitle(roleTitle).get()).collect(Collectors.toSet());
//                        jpaUserRepository.save(new User(DEFAULT_NAMES.get(i), DEFAULT_EMAILS.get(i),
//                                DEFAULT_PASSWORDS.get(i), role));
//                    } catch (Exception ignored) {
//                    }
//                }
//            }
    }

    @Override
    public void saveUser(User user) {
        jpaUserRepository.save(user);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return jpaUserRepository.findByEmail(email);
    }
}
