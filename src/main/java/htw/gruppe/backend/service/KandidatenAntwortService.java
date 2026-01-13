/**
 * 
 * @author ??
 * 
 */

package htw.gruppe.backend.service;

import htw.gruppe.backend.entity.Aussage;
import htw.gruppe.backend.entity.Kandidat;
import htw.gruppe.backend.entity.KandidatenAntwort;
import htw.gruppe.backend.record.KandidatenAntwortenDto;
import htw.gruppe.backend.record.KandidatenAntwortenRequestDto;
import htw.gruppe.backend.record.KandidatenAntwortenResponseDto;
import htw.gruppe.backend.repository.AussagenRepository;
import htw.gruppe.backend.repository.KandidatenAntwortRepository;
import htw.gruppe.backend.repository.KandidatenRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * service für Kandidaten Antworten
 */
@Service
public class KandidatenAntwortService {

        private KandidatenAntwortRepository kandidatenAntwortRepository;
        private KandidatenRepository kandidatenRepository;
        private AussagenRepository aussagenRepository;

        /**
         * Konstruktor für Kandidaten Antworten
         *
         * @param kandidatenAntwortRepository
         * @param kandidatRepository
         * @param aussageRepository
         */
        public KandidatenAntwortService(KandidatenAntwortRepository kandidatenAntwortRepository,
                        KandidatenRepository kandidatRepository, AussagenRepository aussageRepository) {
                this.kandidatenAntwortRepository = kandidatenAntwortRepository;
                this.kandidatenRepository = kandidatRepository;
                this.aussagenRepository = aussageRepository;
        }

        /**
         * Gibt alle Kandidaten Antworten
         * Für den Get Request
         */
        public List<KandidatenAntwortenDto> getKandidatenAntwort(Long kandidatId) {
                return kandidatenAntwortRepository.findByKandidat_IdOrderByAussage_IdAsc(kandidatId).stream()
                                .map(e -> new KandidatenAntwortenDto(e.getAussage().getId(), e.getAnswerValue()))
                                .collect(Collectors.toList());
        }

        /**
         * Für die Kandidaten Antworten
         * Für den Post Request
         * 
         * @param request        der Request DTO
         * @param Matrikelnummer vom Kandidaten Repository für findBy
         * @return Response DTO to Controller
         */
        public KandidatenAntwortenResponseDto postKandidatenAntwort(KandidatenAntwortenRequestDto request,
                        String Matrikelnummer) {

                Kandidat kandidat = kandidatenRepository.findByMatrikelnummer(Matrikelnummer)
                                .orElseThrow(() -> new RuntimeException("Kandidat nich hier"));

                Aussage aussage = aussagenRepository.findById(request.aussage_id())
                                .orElseThrow(() -> new RuntimeException("Aussage nicht gefunden"));

                /**
                 * antwort kombinert den kandidaten von Login, aussage und die Antwort der
                 * Aussage
                 */
                KandidatenAntwort antwort = new KandidatenAntwort(kandidat, aussage, request.answerValue());
                KandidatenAntwort saved = kandidatenAntwortRepository.save(antwort);
                /**
                 * kopiert die nötigen Felder in Resposne DTO für den API Response
                 */
                return new KandidatenAntwortenResponseDto(
                                saved.getAussage().getId(),
                                saved.getAnswerValue());

        }

        /**
         * Alle Antworten eines bestimmten Kandidaten abrufen
         *
         * @param kandidatId ID des Kandidaten
         */
        public List<KandidatenAntwort> getAntwortenByKandidat(Long kandidatId) {
                return kandidatenAntwortRepository.findByKandidat_IdOrderByAussage_IdAsc(kandidatId);
        }

}
