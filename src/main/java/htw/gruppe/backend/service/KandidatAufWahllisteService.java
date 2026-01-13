package htw.gruppe.backend.service;

import org.springframework.stereotype.Service;

import htw.gruppe.backend.entity.Kandidat;
import htw.gruppe.backend.entity.KandidatAufWahlliste;
import htw.gruppe.backend.entity.Wahlliste;
import htw.gruppe.backend.repository.KandidatAufWahllisteRepository;
import htw.gruppe.backend.repository.KandidatenRepository;
import org.springframework.transaction.annotation.Transactional;
import htw.gruppe.backend.repository.WahllisteRepository;

@Service
@Transactional
public class KandidatAufWahllisteService {
    private final KandidatAufWahllisteRepository kandidatAufWahllisteRepository;
    private final KandidatenRepository kandidatenRepository;
    private final WahllisteRepository wahllisteRepository;

    public KandidatAufWahllisteService(
            KandidatAufWahllisteRepository kandidatAufWahllisteRepository,
            KandidatenRepository kandidatenRepository,
            WahllisteRepository wahllisteRepository
    ) {
        this.kandidatAufWahllisteRepository = kandidatAufWahllisteRepository;
        this.kandidatenRepository = kandidatenRepository;
        this.wahllisteRepository = wahllisteRepository;
    }

    
    public void addKandidatToWahlliste(Long kandidatId, Long wahllisteId) {

        
        Kandidat kandidat = kandidatenRepository.findById(kandidatId)
                .orElseThrow(() -> new RuntimeException("Kandidat nicht gefunden"));

        
        Wahlliste wahlliste = wahllisteRepository.findById(wahllisteId)
                .orElseThrow(() -> new RuntimeException("Wahlliste nicht gefunden"));

        
        if (kandidatAufWahllisteRepository
                .existsByKandidatAndWahlliste(kandidat, wahlliste)) {
            throw new RuntimeException("Kandidat ist bereits auf dieser Wahlliste");
        }

        
        if (kandidatAufWahllisteRepository
                .existsByKandidatIdAndWahllisteGremiumId(
                        kandidatId,
                        wahlliste.getGremium().getId()
                )) {
            throw new RuntimeException(
                    "Kandidat darf pro Gremium nur auf einer Wahlliste stehen"
            );
        }

       
        KandidatAufWahlliste mapping =
                new KandidatAufWahlliste(kandidat, wahlliste);

        kandidatAufWahllisteRepository.save(mapping);
   }

    
    public void removeKandidatFromWahlliste(Long kandidatId, Long wahllisteId) {

        
        long listenCount =
                kandidatAufWahllisteRepository.countByKandidatId(kandidatId);

        if (listenCount <= 1) {
            throw new RuntimeException(
                    "Ein Kandidat muss mindestens auf einer Wahlliste stehen"
            );
        }

       
        long kandidatenAufListe =
                kandidatAufWahllisteRepository.countByWahllisteId(wahllisteId);

        if (kandidatenAufListe <= 3) {
            throw new RuntimeException(
                    "Wahlliste muss mindestens 3 Kandidaten enthalten"
            );
        }

        
        kandidatAufWahllisteRepository
                .deleteByKandidatIdAndWahllisteId(kandidatId, wahllisteId);
    }
}
