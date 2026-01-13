package htw.gruppe.backend.record;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record WahllisteDto(
        @Schema(description = "Id der Wahlliste", example = "100")
        Long id,
        @Schema(description = "Name der Wahlliste", example = "Liste 1")
        String name,
        @Schema(description = "Liste der kandidaten auf der Wahlliste", example = "Tom Tailor, Lisa Breite")
        List<KandidatDto> kandidaten

) {
}
