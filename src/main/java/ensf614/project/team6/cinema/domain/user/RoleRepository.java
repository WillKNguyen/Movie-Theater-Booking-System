package ensf614.project.team6.cinema.domain.user;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface RoleRepository {
    Optional<Role> findRoleByTitle(String title);
}
