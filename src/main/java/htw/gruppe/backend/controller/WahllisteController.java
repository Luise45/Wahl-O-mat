package htw.gruppe.backend.controller;

import htw.gruppe.backend.record.AussageDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import htw.gruppe.backend.entity.Wahlliste;
import htw.gruppe.backend.service.WahllisteService;

@RestController
@RequestMapping("/api/wahllisten")
@Tag(name = "Wahlliste", description = "Wahlliste Api")// für swagger
public class WahllisteController {
    private final WahllisteService wahllisteService;

    public WahllisteController(WahllisteService wahllisteService) {
        this.wahllisteService = wahllisteService;
    }

    // für Swagger
    @Operation(summary = "Wahllsten laden",
            description = "Wahllisten in eine Liste im frontend laden")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AussageDto.class))),
            @ApiResponse(responseCode = "404", description = "Wahlliste nicht gefunden",
                    content = @Content)})

    @GetMapping("/{wahllisteId}")
    public ResponseEntity<Wahlliste> getWahlliste(@PathVariable Long wahllisteId) {
        Wahlliste wahlliste = wahllisteService.getWahlliste(wahllisteId);
        return ResponseEntity.ok(wahlliste);
    }

    @PostMapping("/{wahllisteId}/validate")
    public ResponseEntity<Void> validateWahlliste(@PathVariable Long wahllisteId) {
        wahllisteService.validateWahlliste(wahllisteId);
        return ResponseEntity.noContent().build();
    }
}
