package htw.gruppe.backend.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * <p>Enitaet fuer Passwort vergessen.
 * Getter und Setter fuer den 'ResetService' </p>
 * @version 1.0
 */
@Entity
public class ForgotPassword {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

         private String token;

        private LocalDateTime expiresAt;

        private String matrikelnummer;

        public boolean isExpired() {
            return expiresAt.isBefore(LocalDateTime.now());
        }





    public Long getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public String getMatrikelnummer() {
        return matrikelnummer;
    }

    public void setMatrikelnummer(String matrikelnummer) {
            this.matrikelnummer = matrikelnummer;
    }
}




