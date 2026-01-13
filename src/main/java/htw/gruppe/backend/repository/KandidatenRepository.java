package htw.gruppe.backend.repository;

import htw.gruppe.backend.entity.Kandidat;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

/**
 * Repository für Kandidaten.
 * Bietet CRUD-Funktionen über JpaRepository.
 */
public interface KandidatenRepository extends JpaRepository<Kandidat, Long> {

    // Kandidat anhand Matrikelnummer suchen
    Optional<Kandidat> findByMatrikelnummer(String matrikelnummer);
    

    // Prüfen, ob Matrikelnummer bereits existiert
    boolean existsByMatrikelnummer(String matrikelnummer);


}