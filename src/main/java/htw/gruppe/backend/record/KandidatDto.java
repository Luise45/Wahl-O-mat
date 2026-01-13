package htw.gruppe.backend.record;

import io.swagger.v3.oas.annotations.media.Schema;

public record KandidatDto(

                @Schema(description = "id des Kandidaten", example = "1") Long id,
                @Schema(description = "Name des Kandidaten") String nachname,
                @Schema(description = "Name des Kandidaten") String vorname
) {}
