package htw.gruppe.backend.controller;

import htw.gruppe.backend.entity.Kandidat;
import htw.gruppe.backend.repository.KandidatenRepository;
import htw.gruppe.backend.service.ResetInterface;
import htw.gruppe.backend.service.ResetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

/**
 * Controller fuer die Funktion 'Passwort vergessen'
 * @author Tabatt
 * @version 2.0
 */

    @RestController
    @RequestMapping("/api")
    public class ForgotPasswordController  {

        private final KandidatenRepository kandidatenRepository;
        private final ResetInterface resetInterface;
        private final ResetService resetService;

        public ForgotPasswordController(KandidatenRepository kandidatenRepository, ResetInterface resetInterface, ResetService resetService) {
            this.kandidatenRepository = kandidatenRepository;
            this.resetInterface = resetInterface;
            this.resetService = resetService;
        }

    /**
     * POST fuer passwort vergessen, triggert Email mit reset Link
     * @param request fuer post
     * @return erfolgreich oder error
     */
        @PostMapping("/forgot-password")
        public ResponseEntity<String> forgotPassword(@RequestBody Map<String, String> request) {

            String matrikelnummer = request.get("matrikelnummer");
            if (matrikelnummer == null || matrikelnummer.isEmpty()) {
                return ResponseEntity.badRequest().body("Matrikelnummer fehlt");}

            Optional<Kandidat> kandidatOpt = kandidatenRepository.findByMatrikelnummer(matrikelnummer);
            if (kandidatOpt.isEmpty()) {
                return ResponseEntity.badRequest().body("Kandidat nicht gefunden"); }

            String token = resetService.ResetTokenErstellen(matrikelnummer);
            String studentEmail = matrikelnummer;

            String Link = " http://localhost:4200/reset-password?token=" + token;

            resetInterface.sendEmail(
                    studentEmail,
                    "Passwort zurücksetzen",
                    "Bitte klicken Sie auf den folgenden Link: " + Link
            );
            //Reset token nur fuer dev, spaeter bitte loeschen
            return ResponseEntity.ok("Reset token: " + token +" "+ "E-Mail zum Zurücksetzen wurde gesendet.");

        }

    /**
     * POST fuer neues Passwort speichern
     * @param body
     * @return erfolgreich oder error
     */
        @PostMapping("/reset-password")
        public ResponseEntity<?> resetPassword(@RequestBody Map<String, String> body) {
            String token = body.get("token");
            String newPassword = body.get("newPassword");
            resetService.resetPassword(token, newPassword);

            return ResponseEntity.ok(Map.of("message", "Passwort updated"));
        }
    }







