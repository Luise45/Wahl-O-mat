package htw.gruppe.backend.record;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
// fur post enpoint
/**
 * Request Dto
 * <p>
 * Für den Post Kandidaten Antworten Endpunkt.
 * </p>
 */
public record KandidatenAntwortenRequestDto (

        @Schema(description = "Aussagen id fur die 15 Aussagen. ", example = "1")
        @NotNull Long aussage_id,
        @Schema(description = "Antworten Wert fuer eien Aussage", example = "5/10")
        int answerValue
)
{}
