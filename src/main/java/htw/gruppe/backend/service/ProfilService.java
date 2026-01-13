/**
 * 
 * @author ??
 * 
 */

package htw.gruppe.backend.service;

import htw.gruppe.backend.entity.Kandidat;
import htw.gruppe.backend.record.ProfilRequest;
import htw.gruppe.backend.record.ProfilResponse;
import htw.gruppe.backend.repository.KandidatenRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

/**
 * Service für Kandidatenprofile
 * 
 * Bietet Methoden zum Abrufen und Aktualisieren von Kandidatenprofilen
 */
@Service
@Transactional
public class ProfilService {

    private final KandidatenRepository kandidatenRepository;
    private final Logger log = LoggerFactory.getLogger(ProfilService.class);

    public ProfilService(KandidatenRepository kandidatenRepository) {
        this.kandidatenRepository = kandidatenRepository;
    }

    /**
     * Holt das Profil eines Kandidaten anhand der Matrikelnummer
     *
     * @param matrikelnummer Matrikelnummer des Kandidaten
     * @return Optional mit ProfilResponse, falls Kandidat existiert
     */
    public Optional<ProfilResponse> getProfilByMatrikelnummer(String matrikelnummer) {
        log.info("Abruf Profil für Matrikelnummer: {}", matrikelnummer);
        return kandidatenRepository.findByMatrikelnummer(matrikelnummer)
                .map(k -> new ProfilResponse(
                        k.getVorname(),
                        k.getNachname(),
                        k.getFachbereich(),
                        k.getStudiengang(),
                        k.getMatrikelnummer(),
                        k.getBeschreibung()));
    }

    /**
     * Erstellt ein neues Profil für einen bestehenden Kandidaten
     *
     * @param request ProfilRequest mit Profilinformationen
     * @return Optinal mit ProfilResponse, falls erfolgreich, sonst leer
     */
    public Optional<ProfilResponse> createProfil(ProfilRequest request) {
        log.info("Erstelle Profil: {}", request);

        // Prüfe, ob Kandidat schon existiert
        if (kandidatenRepository.findByMatrikelnummer(request.matrikelnummer()).isPresent()) {
            log.warn("Kandidat existiert bereits: {}", request.matrikelnummer());
            return Optional.empty();

        }
        // Neuen Kandidaten erstellen
        try {
            Kandidat kandidat = new Kandidat(
                    request.matrikelnummer(),
                    request.fachbereich(),
                    request.studiengang(),
                    "passwort123",
                    request.vorname(),
                    request.nachname(), 
                    null);

            kandidatenRepository.save(kandidat);

            return Optional.of(new ProfilResponse(
                    kandidat.getVorname(),
                    kandidat.getNachname(),
                    kandidat.getFachbereich(),
                    kandidat.getStudiengang(),
                    kandidat.getMatrikelnummer(),
                    kandidat.getBeschreibung()));
        } catch (Exception e) {
            log.error("Fehler beim erstellen des Profils: {}", e.getMessage(), e);
            return Optional.empty();
        }
    }

    /**
     * Aktualisiert das Profil eines Kandidaten anhand der Matrikelnummer
     *
     * @param matrikelnummer Matrikelnummer des Kandidaten
     * @param request        ProfilRequest mit neuen Daten
     * @return Optional mit aktualisierten ProfilResponse, falls Kandidat existiert
     */

    public Optional<ProfilResponse> updateProfil(String matrikelnummer, ProfilRequest request) {
        log.info("Update Profil für Matrikelnummer: {}", matrikelnummer, request);

        Optional<Kandidat> kandidatOpt = kandidatenRepository.findByMatrikelnummer(matrikelnummer);
        if (kandidatOpt.isEmpty()) {
            log.error("Kandidatmit Matrikelnummer {} existiert nicht!", matrikelnummer);
            return Optional.empty();
        }
        try {
            Kandidat k = kandidatOpt.get();
            k.setVorname(request.vorname());
            k.setNachname(request.nachname());
            k.setFachbereich(request.fachbereich());
            k.setStudiengang(request.studiengang());
            k.setBeschreibung(request.beschreibung());

            kandidatenRepository.save(k);

            return Optional.of(new ProfilResponse(
                    k.getVorname(),
                    k.getNachname(),
                    k.getFachbereich(),
                    k.getStudiengang(),
                    k.getMatrikelnummer(),
                    k.getBeschreibung()));
        } catch (Exception e) {
            log.error("Fehler beim Aktualisieren des Profils: {}", e.getMessage(), e);
            return Optional.empty();
        }
    }

}
