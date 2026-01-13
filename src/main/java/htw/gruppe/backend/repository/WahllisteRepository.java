package htw.gruppe.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import htw.gruppe.backend.entity.Wahlliste;

public interface WahllisteRepository extends JpaRepository<Wahlliste, Long> {
    
}
