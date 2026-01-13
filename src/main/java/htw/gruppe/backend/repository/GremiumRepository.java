package htw.gruppe.backend.repository;

import htw.gruppe.backend.entity.Gremium;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GremiumRepository extends JpaRepository<Gremium, Long> {

    Optional<Gremium> findByName(String name);

    boolean existsByName(String name);
}
