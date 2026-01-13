package htw.gruppe.backend.record;

import io.swagger.v3.oas.annotations.media.Schema;

public record AuthResponse(
                @Schema(description = "token fuer login", example = "sdljgfengl") String token,
                @Schema(description = "Matrikelnummer eiens Kandidaten", example = "s059804") String matrikelnummer,
                @Schema(description = "response message fuer log in ", example = "login erfolgreich") String message) {
}
