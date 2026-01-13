/**
 * Entitaet fur Ausage
 * @author Tabatt
 */

package htw.gruppe.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "aussage")
public class Aussage {
    /**
     * primary key ist id, nicht auto generated.
     * columns aussage_id, aussage_text und aktive sind vorhanden"
     */
    @Id
    @Column(name = "aussage_id")
    private Long id;

    @Column(name = "aussage_text")
    private String aussage_text;

        @Column(name = "aktiv")
        private Boolean aktiv;
    /**
     * Eine Aussage kann viele Kandidaten Antworten haben durch one to many
     * Parent ist Aussage und Kind ist Kandidaten Antwort.
     * Alle Antworten ohne aussage werden geloescht.
     *
     */
    @OneToMany(mappedBy = "aussage", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<KandidatenAntwort> kandidatenAntworten = new ArrayList<>();

    public Aussage() {
    }

    public Aussage(Long id, String aussage_text, Boolean aktiv) {
           this.id = id;
        this.aussage_text = aussage_text;
        this.aktiv = aktiv;
    }

    public Long getId() {

        return this.id;
    }

    public String getAussage_text() {
        return this.aussage_text;
    }

    public Boolean getAktiv() {
        return this.aktiv;
    }

    public void setAussage_text(String aussage_text) {
        this.aussage_text = aussage_text;
    }

    public void setAktiv(Boolean aktiv) {
        this.aktiv = aktiv;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Aussage aussage))
            return false;
        return Objects.equals(id, aussage.id) && Objects.equals(aussage_text, aussage.aussage_text)
                && Objects.equals(aktiv, aussage.aktiv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.aussage_text, this.aktiv);
    }

    @Override
    public String toString() {
        return "Aussage{" +
                "id=" + id +
                ", aussage_text='" + aussage_text + '\'' +
                ", aktiv=" + aktiv +
                '}';
    }
}
