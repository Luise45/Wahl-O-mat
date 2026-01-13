package htw.gruppe.backend.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Diese Methode ist für das Cross Origin definiert
 * <p>
 *     Diese Methode überschreibt das vordefinierte WebMvc interface. Wir erlauebn cross
 *     Origin zwischen backend 8080 und frontend 2400 für Methoden get, post, put für alle headers
 * </p>
 * @author Tabatt
 * @version 1.0
 */
@Configuration
public class CorsConfig {


    @Bean
    public WebMvcConfigurer CustomCors() {
        return new WebMvcConfigurer() {
            @Override
            /**
             *  Wir überschreiben die Methode addCorsMapping,
             *  dem backend das frontend 4200 zu erlauben
             */
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:4200")
                        .allowedMethods("GET", "POST", "PUT")
                        .allowedHeaders("*");
            }
        };
    }

}
