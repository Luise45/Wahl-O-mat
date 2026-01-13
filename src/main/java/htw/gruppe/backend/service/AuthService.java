/**
 * 
 * @author ??
 * 
 */

package htw.gruppe.backend.service;

import htw.gruppe.backend.entity.Kandidat;
import htw.gruppe.backend.repository.KandidatenRepository;
import htw.gruppe.backend.record.AuthResponse;
import htw.gruppe.backend.record.LoginRequest;
import htw.gruppe.backend.security.JwtUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class AuthService {

    private final KandidatenRepository userRepository;
    private final JwtUtil jwtUtil;

    // Konstruktor
    public AuthService(KandidatenRepository userRepository,  JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    public AuthResponse login (LoginRequest request){
        // User finden
        Kandidat user = userRepository.findByMatrikelnummer(request.matrikelnummer())
                .orElseThrow(() -> new RuntimeException("Ungültige Anmeldedaten"));

        if (!request.password().equals(user.getPassword())) {
            throw new RuntimeException("Falsches Passwort");
        }

        // JWT Token generieren
        String token = jwtUtil.generateToken(user.getMatrikelnummer());

        // Response erstellen (Record-Syntax)
        return new AuthResponse(
                token,
                user.getMatrikelnummer(),
                "Login erfolgreich"
        );

    }


}
