/**
 * Aussagen Controller
 * Im Ausssagen Controller giebt es die Endpunkte get Aussage/get Aussage by id/get kandidaten-fragen
 * 
 * @author Schmidt
 * @author Tabatt
 */

package htw.gruppe.backend.controller;

import htw.gruppe.backend.record.AussageDto;
import htw.gruppe.backend.service.AussagenService;
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

import java.util.List;

@RestController
@RequestMapping("/api") //Alle Endpunkte bekommen api vorangestellt
@CrossOrigin(origins = "http://localhost:4200")

@Tag(name = "Aussage", description = "Aussagen Api")// für swagger

public class AussagenController {

    private final Logger log = LoggerFactory.getLogger(getClass()); //to log  info/errors aud die konsole

    private final AussagenService aussagenService;

    public AussagenController(AussagenService aussagenService) {

        this.aussagenService = aussagenService;
    }

    // für Swagger
    @Operation(summary = "Aussagen laden",
            description = "Aussagen in eine Liste im frontend laden")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AussageDto.class))),
            @ApiResponse(responseCode = "404", description = "Aussagen nicht gefunden",
                    content = @Content)})

    /**
     * Liefert alle Aussagen, die Aktive sind
     */

    @GetMapping("/aussage")
    public ResponseEntity<List<AussageDto>> getAktiveAussagen() {
        log.info("getAlleAussagen()");

        var ausgabe = aussagenService.getAktiveAussagen();

        return ResponseEntity
              .status(HttpStatus.OK)
                .body(ausgabe);
    }
}
