package htw.gruppe.backend.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

    @Component
    public class JwtUtilFilter extends OncePerRequestFilter {

        private final JwtUtil jwtUtil;

        // JwtUtil wird über den Konstruktor reingegeben
        public JwtUtilFilter(JwtUtil jwtUtil) {
            this.jwtUtil = jwtUtil;
        }

        @Override
        protected void doFilterInternal(HttpServletRequest request,
                                        HttpServletResponse response,
                                        FilterChain filterChain)
                throws ServletException, IOException {

            // Authorization Header auslesen
            String authHeader = request.getHeader("Authorization");

            // Wenn kein Header oder nicht mit "Bearer " → einfach weitermachen
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }

            // Token aus dem Header ausschneiden (ab Zeichen 7, nach "Bearer ")
            String token = authHeader.substring(7);

            // Matrikelnummer (Username) aus dem Token holen
            String username = jwtUtil.extractUsername(token);

            // Nur weiter machen, wenn noch niemand im SecurityContext eingeloggt ist
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                // Token prüfen (Gültigkeit + Ablaufdatum)
                if (jwtUtil.validateToken(token, username)) {

                    // Authentifizierung-Objekt bauen (ohne Rollen → leere Liste)
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(
                                    username,
                                    null,
                                    Collections.emptyList()
                            );

                    // User im SecurityContext als eingeloggt markieren
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }

            // Filterkette fortsetzen
            filterChain.doFilter(request, response);
        }
    }
