package htw.gruppe.backend.record;

import io.swagger.v3.oas.annotations.media.Schema;

//dto record für Swagger
@Schema(description = "User data transfer object")

/**
 * Aussage Dto für Aussagen get Endpunkt
 */

public record AussageDto(

        @Schema(description = "Aussagen id fuer die 15 Aussagen. Nicht auto gen", example = "1") Long id,

        @Schema(description = "Die Aussage", example = "Wir moechten gerne..") String aussageText,

        @Schema(description = "Zeigt an, ob eine Aussage aktiv ist. Wird vom Admin verwaltet", example = "true") Boolean aktiv

) {

}