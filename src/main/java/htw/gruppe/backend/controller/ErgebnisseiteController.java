package htw.gruppe.backend.controller;

import htw.gruppe.backend.record.AussageDto;
import htw.gruppe.backend.record.GremiumDto;
import htw.gruppe.backend.service.ErgebnisseiteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller fuer die Ergebnisseite fuer die Waehler.
 */
@RestController
    @RequestMapping("/api")
    @CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Ergebnisseite", description = "Ergebnisseiten Api")// für swagger
    public class ErgebnisseiteController  {

        private ErgebnisseiteService ergebnisseiteService;

        public ErgebnisseiteController(ErgebnisseiteService ergebnisseiteService) {
            this.ergebnisseiteService = ergebnisseiteService;
        }
    // für Swagger
    @Operation(summary = "Ergebnisseite laden",
            description = "Ergebnisseite im frontend laden. Beinhalted Gremien, Wahllisten inklusive Kandidaten ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AussageDto.class))),
            @ApiResponse(responseCode = "404", description = "Keine Gremien",
                    content = @Content)})

    /**
     * GET fuer Ergebnisseite
     * @return alle Gremien und ihre Wahllisten inklusive deren Kandidaten
     */
    @GetMapping("/gremien")
    public List<GremiumDto> getGremienMitWahllistenUndKandidaten() {
        return ergebnisseiteService.findAllWithRelations();
    }



}
