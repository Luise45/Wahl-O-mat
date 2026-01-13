package htw.gruppe.backend.security;

import htw.gruppe.backend.entity.Kandidat;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    // Secret Key für das Signieren der Token
    private static final String SECRET = "eO5AE8UBVdHJ7uJu7Z4dP7mJtwrc7glNQ4wNZyZM9IA=";

    // // Wie lange ein Token gültig ist (24h)
    private static final long EXPIRATION_TIME = 86400000;

    // Erstellt aus dem Secret Key einen kryptografischen Schlüssel
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    // generiert Token
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    // erstellt den Token
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Username aus dem Token auslesen
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    // Alle Claims auslesen (brauchen wir für Username und Ablaufzeit)
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Token prüfen (Username muss stimmen und darf nicht abgelaufen sein)
    public boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    // Überprüfen, ob das Token abgelaufen ist
    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

// für Passwrt vergessen
    public String generatePasswordResetToken(Kandidat kandidat) {
        long expirationMillis = 3600000; // 1 hour
        return Jwts.builder()
                .setSubject(kandidat.getMatrikelnummer())
                .claim("type", "password-reset")
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }
}

