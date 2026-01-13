package htw.gruppe.backend.controller.admin;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;
import org.springframework.web.bind.annotation.*;


import htw.gruppe.backend.entity.Aussage;
import htw.gruppe.backend.repository.AussagenRepository;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Admin - Aussagen")
@RestController
@RequestMapping("/api/admin/aussagen")
public class AdminAussagenController {
    private final AussagenRepository aussagenRepository;

    public AdminAussagenController(AussagenRepository aussagenRepository) {
        this.aussagenRepository = aussagenRepository;
    }

    @GetMapping
    public ResponseEntity<List<Aussage>> getAllAussagen() {
        return ResponseEntity.ok(aussagenRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Aussage> createAussage(@RequestBody Aussage aussage) {
       

         return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(aussagenRepository.save(aussage));
    }

    @PutMapping("/{id}/aktiv")
    public ResponseEntity<Aussage> setAktiv(
            @PathVariable Long id,
            @RequestParam boolean aktiv
    ) {
        return aussagenRepository.findById(id)
                .map(aussage -> {
                    aussage.setAktiv(aktiv);
                    return ResponseEntity.ok(aussagenRepository.save(aussage));
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
