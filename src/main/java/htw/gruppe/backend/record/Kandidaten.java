package htw.gruppe.backend.record;

import io.swagger.v3.oas.annotations.media.Schema;

public record Kandidaten(
        @Schema(description = "Matrikelnummer fuer den Kandidaten", example = "s0485684")
        String matrikelnummer,

        @Schema(description = "Fachbereich des Kandidaten", example = "FB4")
        String fachbereich,

        @Schema(description = "Name des Kandidaten", example = "Lotta")
        String vorname,

        @Schema(description = "Nachname des Kandidaten", example = "Meagi")
        String nachname,
        @Schema(description = "Studiengang des Kandidaten", example = "Informatik")
        String studiengang,

        // @Schema(description = "Beschreibung des Kandidaten", example = "")
        String beschreibung // für Ergebnisse
) {}