package htw.gruppe.backend.service;

import htw.gruppe.backend.record.GremiumDto;
import htw.gruppe.backend.record.KandidatDto;
import htw.gruppe.backend.record.WahllisteDto;
//import htw.gruppe.backend.repository.ErgebnisseiteRepository;
import htw.gruppe.backend.repository.ErgebnisseiteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * <p>Service fuer die Ergebnisseite fuer den Waehler.
 * Der Wahler erhaelt alle Gremien, deren Wahllisten und alle Kandidaten dieser Wahllisten.
 * </p>
 * @author Tabatt
 * @version 1.0
 */
@Service
@Transactional
public class ErgebnisseiteService {

    private ErgebnisseiteRepository ergebnisseiteRepository;

    public ErgebnisseiteService(ErgebnisseiteRepository ergebnisseiteRepository) {
        this.ergebnisseiteRepository = ergebnisseiteRepository;
    }

    public List<GremiumDto> findAllWithRelations() {
        return ergebnisseiteRepository.findAllWithRelations().stream()
                .map(g -> new GremiumDto(g.getName(), g.getWahllisten().stream()
                .map(w -> new WahllisteDto( w.getId(), w.getName(), w.getKandidaten().stream()
                .map(kaw -> new KandidatDto(kaw.getKandidat().getId(), kaw.getKandidat().getVorname(),
                        kaw.getKandidat().getNachname()
                )).toList()
                )).toList()
                )).toList();
    }

}

