package htw.gruppe.backend.record;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record GremiumDto(

                @Schema(description = "Name des Gremiums", example = "Fachschaftsrat") String name,
                @Schema(description = "Liste der Wahllisten") List<WahllisteDto> wahllisten
) {}
