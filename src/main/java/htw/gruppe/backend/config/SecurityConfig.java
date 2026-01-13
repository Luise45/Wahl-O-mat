package htw.gruppe.backend.config;

import htw.gruppe.backend.security.JwtUtilFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

/**
 * Security configuration für Login
 *
 * @author Karsli
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtUtilFilter jwtUtilFilter;

    public SecurityConfig(JwtUtilFilter JwtUtilFilter) {
        this.jwtUtilFilter = JwtUtilFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    
        

        http
                // CSRF aus für APIs, weil keine Sessions
                .csrf(csrf -> csrf.disable())
                .cors(cors -> {
                })
                // Regeln für Endpoints
                .authorizeHttpRequests(auth -> auth
                                        /**
                                         * Hier wird angegebne welche Endpunkte, ohne login benutzt werden dürfen
                                         */
                                        .requestMatchers("/api/auth/login").permitAll()
                                        .requestMatchers("/api/aussage").permitAll()
                                        .requestMatchers("/api/kandidaten").permitAll()
                                        //.requestMatchers("/api/kandidaten_antworten").permitAll()
                                        .requestMatchers("/api/kandidaten_antworten/**").permitAll()
                                         .requestMatchers("/api/profil/**").permitAll()
                                .requestMatchers("/api/forgot-password").permitAll()
                                .requestMatchers("api/reset-password").permitAll()
                                        .requestMatchers("/match/**").permitAll()


                                         .requestMatchers("/api/gremien").permitAll()
                                /**
                                 *  für swagger
                                 */

                                .requestMatchers("/swagger-ui/**").permitAll()
                                .requestMatchers("/v3/api-docs/**").permitAll()

                                .requestMatchers("/api/profil/**").authenticated()


                        // alles andere nur mit Login erlaubt
                        .anyRequest().authenticated()
                        //.anyRequest().permitAll()
                )

                // Keine Sessions -> Anwendung läuft im JWT-Stateless-Modus
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                .addFilterBefore(jwtUtilFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }

    /**
     * CorsConfig für Security
     * <p>
     * CorsConfig interface für Security. Die Endpunkte gehen erst durch die filter Chain, deswegen muss hier nochmal ein interface
     * für die Configuration definiert werden. Das backedn muss cross origin mit dem frontend erlauben.
     * </p>
     *
     */

    @Bean
    public CorsConfigurationSource corsConfiguration1() {
        /**
         * Interface erstellen
         */
        CorsConfiguration configurations = new CorsConfiguration();
        /**
         * erlaubt für die URL alle Endpunkte mit get/post/put
         */
        configurations.setAllowedOrigins(List.of("http://localhost:4200"));
        configurations.setAllowedMethods(List.of("GET", "POST", "PUT"));
        /**
         * Alle Arten von headers sind erlaubt.
         */
        configurations.setAllowedHeaders(List.of("*"));
        /**
         * Login token ist erlaubt
         */
        configurations.setAllowCredentials(true);
        /**
        * CORS Regeln sollen für alle paths der URL funktionieren
        */
        UrlBasedCorsConfigurationSource paths = new UrlBasedCorsConfigurationSource();
        paths.registerCorsConfiguration("/**", configurations);
        return paths;
    }

}

