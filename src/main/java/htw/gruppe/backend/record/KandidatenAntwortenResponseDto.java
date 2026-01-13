package htw.gruppe.backend.record;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 *Dto für Kandidaten Antworten Post Endpunkt - response
 */
public record KandidatenAntwortenResponseDto(

            @Schema(description = "Aussagen id fur die 15 Aussagen. Nicht auto gen", example = "1")
            Long aussage_id,
            @Schema(description = "Antworten Wert", example = "10")
            int answerValue
)
    {}

