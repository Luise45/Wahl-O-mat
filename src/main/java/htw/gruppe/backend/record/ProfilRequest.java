package htw.gruppe.backend.record;

public record ProfilRequest(

        String vorname,
        String nachname,
        String fachbereich,
        String studiengang,
        String matrikelnummer,
        String beschreibung
) { }

