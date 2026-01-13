/**
 * Verantwortlich für die komplette Match-Berechnung zwischen einem Wähler
 * und allen Kandidaten.
 *
 * Ablauf:
 *  1. Empfängt die Antworten des Users (15 Werte zwischen 1–10).
 *  2. Lädt alle Kandidaten aus der Datenbank.
 *  3. Lädt für jeden Kandidaten die Antworten sortiert nach Aussage-ID.
 *  4. Ruft den MatchService auf → berechnet dort die prozentuale Übereinstimmung.
 *  5. Baut eine Liste von MatchResult-Objekten (Name + Matchwert).
 *  6. Sortiert die Kandidaten nach höchstem Match.
 *  7. Sendet die Liste zurück an das Angular-Frontend.
 *
 * Wird von der Angular-Komponente „Match-Logik“ via POST /match aufgerufen.
 *
 * @author Dumke
 * 
 */

package htw.gruppe.backend.controller;

import htw.gruppe.backend.record.MatchResult;
import htw.gruppe.backend.entity.Kandidat;
import htw.gruppe.backend.entity.KandidatenAntwort;
import htw.gruppe.backend.repository.KandidatenAntwortRepository;
import htw.gruppe.backend.repository.KandidatenRepository;
import htw.gruppe.backend.service.MatchService;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController // REST-Endpunkt
@RequestMapping("/match")

public class MatchController {

    private final KandidatenRepository kandidatenRepository;
    private final KandidatenAntwortRepository kandidatenAntwortRepository;
    private final MatchService matchService;

    public MatchController(KandidatenRepository kandidatenRepository,
            KandidatenAntwortRepository kandidatenAntwortRepository,
            MatchService matchService) {
        this.kandidatenRepository = kandidatenRepository;
        this.kandidatenAntwortRepository = kandidatenAntwortRepository;
        this.matchService = matchService;
    }

    // Erwartet eine Liste der 15 Antworten des Users [3, 6, 7, 1, ...]
    @PostMapping
    public List<MatchResult> calculateMatch(@RequestBody List<Integer> voterValues) {

        // Debug 1: Wird der Endpoint überhaupt aufgerufen?
        System.out.println(">>> MATCH ENDPOINT WURDE AUFGERUFEN");

        // Debug 2: Was kommt vom Frontend an?
        System.out.println("Wählerwerte erhalten: " + voterValues);

        // Alle Kandidaten aus der DB holen
        List<Kandidat> kandidaten = kandidatenRepository.findAll();

        // Debug 3: Anzahl der Kandidaten prüfen
        System.out.println("Kandidaten aus DB geladen: " + kandidaten.size());

        List<MatchResult> results = new ArrayList<>();

        // Für jeden Kandidaten Matching berechnen
        for (Kandidat k : kandidaten) {

            // Antworten des Kandidaten sortiert nach Aussage-ID laden
            List<KandidatenAntwort> antworten = kandidatenAntwortRepository
                    .findByKandidat_IdOrderByAussage_IdAsc(k.getId());

            // Debug 4: Prüfen, ob der Kandidat Antworten hat
            System.out.println("Kandidat " + k.getVorname() + " " + k.getNachname()
                    + " → Antworten: " + antworten.size());

            // Liste der numerischen Antwortwerte extrahieren
            List<Integer> candidateValues = antworten
                    .stream()
                    .map(KandidatenAntwort::getAnswerValue)
                    .toList();

            // Match-Berechnung (liegt im MatchService)
            double match = matchService.calculateTotalMatch(candidateValues, voterValues);

            // Debug 5: Ergebnis pro Kandidat ausgeben
            System.out.println("Match für " + k.getVorname() + ": " + match);

            // Ergebnis + Beschreibung hinzufügen
            results.add(new MatchResult(k.getVorname() + " " + k.getNachname(), match, k.getBeschreibung()));
        }

        // Liste nach bestem Match sortieren (höchster Wert zuerst)
        results.sort(Comparator.comparingDouble(MatchResult::match).reversed());

        // Debug 6: Gesamtergebnis zurück an Angular
        System.out.println("Finale Ergebnisse: " + results);

        return results;
    }

}
