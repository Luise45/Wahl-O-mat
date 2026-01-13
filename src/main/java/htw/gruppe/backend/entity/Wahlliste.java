package htw.gruppe.backend.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "wahlliste")
public class Wahlliste {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wahlliste_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    
    @ManyToOne(optional = false)
    @JoinColumn(name = "gremium_id", nullable = false)
    private Gremium gremium;

    
    
    @OneToMany(mappedBy = "wahlliste", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<KandidatAufWahlliste> kandidaten = new ArrayList<>();

    

    public Wahlliste() {}

    public Wahlliste(String name, Gremium gremium) {
        this.name = name;
        this.gremium = gremium;
    }

    

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Gremium getGremium() {
        return gremium;
    }

    public List<KandidatAufWahlliste> getKandidaten() {
        return kandidaten;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGremium(Gremium gremium) {
        this.gremium = gremium;
    }
}


