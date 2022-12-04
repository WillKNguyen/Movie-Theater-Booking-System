package ensf614.project.team6.cinema.infrastructure.user.jpa;

import ensf614.project.team6.cinema.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface JPAUserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
