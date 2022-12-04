package ensf614.project.team6.cinema.infrastructure.user.jpa;

import ensf614.project.team6.cinema.domain.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface JPARoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByTitle(String title);
}
