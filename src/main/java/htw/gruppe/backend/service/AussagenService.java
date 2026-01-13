/**
 * 
 * @author ??
 * 
 */

package htw.gruppe.backend.service;

import htw.gruppe.backend.record.AussageDto;
import htw.gruppe.backend.repository.AussagenRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AussagenService {

    private final AussagenRepository aussagenRepository;

    public AussagenService(AussagenRepository aussagenRepository) {

        this.aussagenRepository = aussagenRepository;
    }

    /**
     * Gibt alle aktiven Aussagen zurück
     */
    public List<AussageDto> getAktiveAussagen() {
        return aussagenRepository.findByAktivTrue().stream()
                .map(e -> new AussageDto(e.getId(), e.getAussage_text(), null))
                .collect(Collectors.toList());
    }

}
