/**
 * 
 * @author ??
 * 
 */



package htw.gruppe.backend.controller;

import htw.gruppe.backend.entity.Kandidat;
import htw.gruppe.backend.record.AussageDto;
import htw.gruppe.backend.record.Kandidaten;
import htw.gruppe.backend.service.KandidatenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/kandidaten")
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name="Kandidaten", description ="Kandidaten Api") // fur swagger
public class KandidatenController {

    private final KandidatenService kandidatenService;
    private final Logger log = LoggerFactory.getLogger(getClass());

    public KandidatenController(KandidatenService kandidatenService) {
        this.kandidatenService = kandidatenService;
    }
    // für Swagger
    @Operation(summary = "Kandidaten laden",
            description = "Kandidaten laden und Kandidaten registrieren")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AussageDto.class))),
            @ApiResponse(responseCode = "404", description = "Kandidat nicht gefunden",
                    content = @Content)})

    /**
     *Ruft einen Kandidaten anhand der Matrikelnummer ab
     * 
     * @param matrikelnummer Matrikelnummer des Kandidaten
     * @return ResponseEntity mit Kandidat oder 404, falls nicht gefunden
     */
    @GetMapping("/{matrikelnummer}")
    public ResponseEntity<Kandidat> getKandidatByMatrikelNummer(@PathVariable String matrikelnummer) {
        log.info("Abruf Kandidat {}", matrikelnummer);
        return kandidatenService.getKandidatByMatrikelnummer(matrikelnummer)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Registriert einen neuen Kandidaten
     *
     * @param kandidat Kandidat-Objekt mit allen erforderlichen Daten
     * @return ResponseEntity mit erstelltem Kandidaten und HTTP 201 oder HTTP 400 bei Fehler
     */
    @PostMapping
    public ResponseEntity<Kandidat> createKandidat(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Kandidat create",
                    required = true,
                    content = @Content(schema = @Schema(implementation = Kandidaten.class))
            )

            @RequestBody Kandidat kandidat) {
        try {
            Kandidat created = kandidatenService.addKandidat(kandidat);
            log.info("Kandidat erstellt: {}", created.getMatrikelnummer());
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (IllegalArgumentException e) {
            log.warn("Fehler beim Erstellen des Kandidaten: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
