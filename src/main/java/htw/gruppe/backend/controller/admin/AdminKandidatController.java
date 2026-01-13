package htw.gruppe.backend.controller.admin;

import htw.gruppe.backend.entity.Kandidat;
import htw.gruppe.backend.repository.KandidatenRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Admin - Kandidaten")
@RestController
@RequestMapping("/api/admin/kandidaten")
public class AdminKandidatController
{
    private final KandidatenRepository kandidatenRepository;

    public AdminKandidatController(KandidatenRepository kandidatenRepository) {
        this.kandidatenRepository = kandidatenRepository;
    }

    @GetMapping
    public ResponseEntity<List<Kandidat>> getAllKandidaten() {
        return ResponseEntity.ok(kandidatenRepository.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKandidat(@PathVariable Long id) {
        if (!kandidatenRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        kandidatenRepository.deleteById(id);
        return ResponseEntity.noContent().build();

    }

}
