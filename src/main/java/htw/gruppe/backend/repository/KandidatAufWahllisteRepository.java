package htw.gruppe.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import htw.gruppe.backend.entity.KandidatAufWahlliste;
import htw.gruppe.backend.entity.Kandidat;
import htw.gruppe.backend.entity.Wahlliste;

public interface KandidatAufWahllisteRepository 
    extends JpaRepository<KandidatAufWahlliste, Long> {
  
    boolean existsByKandidatAndWahlliste(
        Kandidat kandidatId, 
        Wahlliste wahllisteId
    );


    boolean existsByKandidatIdAndWahllisteGremiumId(
        Long kandidatId,
        Long gremiumId
    );

   
    long countByKandidatId(Long kandidatId);


    long countByWahllisteId(Long wahllisteId);

    
    void deleteByKandidatIdAndWahllisteId(
        Long kandidatId,
        Long wahllisteId
    );
}