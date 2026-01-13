package htw.gruppe.backend.service;

import htw.gruppe.backend.record.GremiumDto;
import htw.gruppe.backend.repository.GremiumRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service zur Verwaltung von Gremien
 * Enthält die fachliche Logik für Gremium
 *
 * @author Nguemezi
 */
@Service
@Transactional
public class GremiumService {
    private final GremiumRepository gremiumRepository;

    public GremiumService(GremiumRepository gremiumRepository) {
        this.gremiumRepository = gremiumRepository;
    }

    /**
     * Liefert alle vorhandenen Gremien als DTOs
     *
     * @return Liste aller Gremien
     */
    public List<GremiumDto> getAlleGremien() {

        return gremiumRepository.findAll()
                .stream()
                .map(gremium -> new GremiumDto(
                        gremium.getName(),
                        List.of()
                ))
                .collect(Collectors.toList());
    }
}



