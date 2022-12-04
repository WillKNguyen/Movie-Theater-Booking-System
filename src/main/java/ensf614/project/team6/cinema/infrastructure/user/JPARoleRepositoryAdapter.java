package ensf614.project.team6.cinema.infrastructure.user;

import ensf614.project.team6.cinema.domain.user.Role;
import ensf614.project.team6.cinema.domain.user.RoleRepository;
import ensf614.project.team6.cinema.infrastructure.user.jpa.JPARoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class JPARoleRepositoryAdapter implements RoleRepository {

    private final static List<String> DEFAULT_ROLES = Arrays.asList("ROLE_USER", "ROLE_ADMIN");

    @Autowired
    private JPARoleRepository jpaRoleRepository;

    public JPARoleRepositoryAdapter() {
//        for (String roleTitle : DEFAULT_ROLES) {
//            if (jpaRoleRepository.findByTitle(roleTitle).isEmpty())
//                jpaRoleRepository.save(new Role(roleTitle));
//        }
    }

    @Override
    public Optional<Role> findRoleByTitle(String title) {
        return jpaRoleRepository.findByTitle(title);
    }
}
