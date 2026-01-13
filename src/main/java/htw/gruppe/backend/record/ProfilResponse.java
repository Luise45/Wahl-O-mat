package htw.gruppe.backend.record;

public record ProfilResponse(
        String vorname,
        String nachname,
        String fachbereich,
        String studiengang,
        String matrikelnummer,
        String beschreibung
) { }
