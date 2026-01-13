/**
 * 
 * @author ??
 * 
 */

package htw.gruppe.backend.service;

import htw.gruppe.backend.entity.Kandidat;
import htw.gruppe.backend.repository.KandidatenRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service zur Verwaltung von Kandidaten
 */

@Service
@Transactional
public class KandidatenService {

    private final KandidatenRepository kandidatenRepository;

    public KandidatenService(KandidatenRepository kandidatenRepository) {
        this.kandidatenRepository = kandidatenRepository;
    }

    /**
     * Ruft einen Kandidaten anhand der Matrikelnummer auf.
     *
     * @param matrikelnummer Matrikelnummer des Kandidaten
     * @return Optional mit Kandidat, falls gefunden
     */

    public Optional<Kandidat> getKandidatByMatrikelnummer(String matrikelnummer) {
        return kandidatenRepository.findByMatrikelnummer(matrikelnummer);
    }

    public Kandidat addKandidat(Kandidat kandidat){
        if(kandidatenRepository.existsByMatrikelnummer(kandidat.getMatrikelnummer())){
            throw new IllegalArgumentException("Kandidat mit dieser Matrikelnummer existiert bereits");
        }
        return kandidatenRepository.save(kandidat);
    }
}
