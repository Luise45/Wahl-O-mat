package htw.gruppe.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
    name = "kandidataufwahllistecopy",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"kandidat_id", "wahlliste_id"})
    }
)
public class KandidatAufWahlliste {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kandidat_auf_wahlliste_id")
    private Long id;

    
    @ManyToOne(optional = false)
    @JoinColumn(name = "kandidat_id", nullable = false)
    private Kandidat kandidat;

    
    @ManyToOne(optional = false)
    @JoinColumn(name = "wahlliste_id", nullable = false)
    private Wahlliste wahlliste;

    public KandidatAufWahlliste() {
    }

    public KandidatAufWahlliste(Kandidat kandidat, Wahlliste wahlliste) {
        this.kandidat = kandidat;
        this.wahlliste = wahlliste;
    }

    public Long getId() {
       return id;
    }

    public Kandidat getKandidat() {
        return kandidat;
    }

    public Wahlliste getWahlliste() {
        return wahlliste;
    }
}
    

