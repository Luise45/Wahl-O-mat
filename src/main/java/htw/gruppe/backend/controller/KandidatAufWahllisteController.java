package htw.gruppe.backend.controller;

import htw.gruppe.backend.record.WahllisteDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import htw.gruppe.backend.service.KandidatAufWahllisteService;

@RestController
@RequestMapping("/api/wahllisten")
@Tag(name = "KandidataufWahliste", description = "Aussagen Api")// für swagger
public class KandidatAufWahllisteController {

     private final KandidatAufWahllisteService kandidatAufWahllisteService;

    public KandidatAufWahllisteController(KandidatAufWahllisteService kandidatAufWahllisteService) {
        this.kandidatAufWahllisteService = kandidatAufWahllisteService;
    }
    // für Swagger
    @Operation(summary = "KandidatenAufWahlliste laden",
            description = "Kanidaten der Wahllisten")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = WahllisteDto.class))),
            @ApiResponse(responseCode = "404", description = "Kandidat nicht gefunden",
                    content = @Content)})


    @PostMapping("/{wahllisteId}/kandidaten/{kandidatId}")
    public ResponseEntity<Void> addKandidat(
            @PathVariable Long wahllisteId,
            @PathVariable Long kandidatId
    ) {
        kandidatAufWahllisteService.addKandidatToWahlliste(kandidatId, wahllisteId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{wahllisteId}/kandidaten/{kandidatId}")
    public ResponseEntity<Void> removeKandidat(
            @PathVariable Long wahllisteId,
            @PathVariable Long kandidatId
    ) {
        kandidatAufWahllisteService.removeKandidatFromWahlliste(kandidatId, wahllisteId);
        return ResponseEntity.noContent().build();
    }
}
