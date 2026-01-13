package htw.gruppe.backend.record;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(

        @NotBlank(message = "Matrikelnummer ist erforderlich")
        String matrikelnummer,

        @NotBlank(message = "Passwort ist erforderlich")
        String password
) {}
