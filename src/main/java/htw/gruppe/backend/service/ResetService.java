package htw.gruppe.backend.service;

import htw.gruppe.backend.entity.ForgotPassword;
import htw.gruppe.backend.entity.Kandidat;
import htw.gruppe.backend.repository.KandidatenRepository;
import htw.gruppe.backend.repository.ResetPasswortRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Diese Klasse isr der Service fuer die Funktion 'Passwort zuruecksetzen'
 * 
 * Der Service ist zustaendig fuer die Methode fuer Email absenden,
 * Token generieren der mit der Email geschickt wird
 * und die Methode die das Passwort erneuert.
 * 
 * 
 * @author Tabatt
 * @version
 */
@Service
public class ResetService implements ResetInterface {

    private final JavaMailSender mailSender;
    private final ResetPasswortRepository resetPasswortRepository;
    private final KandidatenRepository kandidatenRepository;
    // private final PasswordEncoder passwordEncoder;

    public ResetService(JavaMailSender mailSender, ResetPasswortRepository resetPasswortRepository,
            KandidatenRepository kandidatenRepository) {
        this.mailSender = mailSender;
        this.resetPasswortRepository = resetPasswortRepository;
        this.kandidatenRepository = kandidatenRepository;
        // this.passwordEncoder = passwordEncoder;
    }

    /**
     * Methode fuer generierung der Email zum 'Password reset'
     * 
     * @param to      'Adressant' der Email
     * @param subject der Email
     * @param text    Inhalt der Email
     */
    @Override
    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        message.setFrom("fiwproj@Student.HTW-Berlin.de");
        mailSender.send(message);
    }

    /**
     * Erstellt token fuer die Email, die der Kandidat erhaelt
     * 
     * @param matrikelnummer des Kandidaten
     * @return token fuer die Email
     */
    public String ResetTokenErstellen(String matrikelnummer) {
        String token = UUID.randomUUID().toString();

        ForgotPassword reset = new ForgotPassword();
        reset.setToken(token);
        reset.setMatrikelnummer(matrikelnummer);
        reset.setExpiresAt(LocalDateTime.now().plusMinutes(30));

        resetPasswortRepository.save(reset);
        return token;
    }

    /**
     * Passwort erneuern
     * 
     * @param token       aus der Email
     * @param newPassword das neue Passwort, dass gespeichert werden soll
     * @return boolean ob Passwort reset erfolgreich war
     */
    public boolean resetPassword(String token, String newPassword) {
        ForgotPassword resetToken = resetPasswortRepository.findByToken(token)
                .orElseThrow(() -> new IllegalArgumentException("Invalider Token"));
        if (resetToken.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Token zu alt");
        }

        Kandidat kandidat = kandidatenRepository
                .findByMatrikelnummer(resetToken.getMatrikelnummer())
                .orElseThrow(() -> new IllegalArgumentException("Kandidat nicht gefunden"));

        kandidat.setPassword(newPassword);
        kandidatenRepository.save(kandidat);
        resetPasswortRepository.delete(resetToken);

        return true;
    }
}
