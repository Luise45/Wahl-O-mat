package htw.gruppe.backend.repository;

import htw.gruppe.backend.entity.ForgotPassword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository fuer der ResetPasswort Service, zuer erneuerung des Passwortes, mit dem
 * token.
 * @author Tabatt
 * @version 1.0
 */
public interface ResetPasswortRepository extends JpaRepository<ForgotPassword, Long> {
    Optional<ForgotPassword> findByToken(String token);
}
