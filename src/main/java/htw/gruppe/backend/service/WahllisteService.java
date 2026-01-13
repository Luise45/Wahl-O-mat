package htw.gruppe.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import htw.gruppe.backend.entity.Wahlliste;
import htw.gruppe.backend.repository.KandidatAufWahllisteRepository;
import htw.gruppe.backend.repository.WahllisteRepository;

@Service
@Transactional
public class WahllisteService {
private final WahllisteRepository wahllisteRepository;
    private final KandidatAufWahllisteRepository kandidatAufWahllisteRepository;

    public WahllisteService(
            WahllisteRepository wahllisteRepository,
            KandidatAufWahllisteRepository kandidatAufWahllisteRepository
    ) {
        this.wahllisteRepository = wahllisteRepository;
        this.kandidatAufWahllisteRepository = kandidatAufWahllisteRepository;
    }

   
    public Wahlliste getWahlliste(Long wahllisteId) {
        return wahllisteRepository.findById(wahllisteId)
                .orElseThrow(() -> new RuntimeException("Wahlliste nicht gefunden"));
    }

    
    public void validateWahlliste(Long wahllisteId) {

        long kandidatenCount =
                kandidatAufWahllisteRepository.countByWahllisteId(wahllisteId);

        if (kandidatenCount < 3) {
            throw new RuntimeException("Eine Wahlliste muss mindestens 3 Kandidaten enthalten");
        }
    }
}
