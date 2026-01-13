/**
 * Entitaet für Kandidat
 * @author Karsli
 * @author Schmidt
 * @author Tabatt
 */

package htw.gruppe.backend.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "kandidat")
public class Kandidat {
    /**
     * primary key ist kandidaten_id und wird auto generated.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kandidat_id")
    private Long id;
    /**
     * Column Namen
     */
    @Column(unique = true, nullable = false)
    private String matrikelnummer;

    private String fachbereich;
    private String password;
    private String vorname;
    private String nachname;
    private String beschreibung;
    private String studiengang;

    /**
     * Eine Kandidat kann viele Kandidaten Antworten haben durch one to many
     * Kandidat giebt die reference kandidat_id
     */

    @OneToMany(mappedBy = "kandidat", cascade = CascadeType.ALL, orphanRemoval = true)
    //@JsonManagedReference("kandidat_id")

    private List<KandidatenAntwort> Kandidatenantworten;

    @OneToMany(mappedBy = "kandidat")
    private List<KandidatAufWahlliste> wahllisten;

    public Kandidat(String matrikelnummer, String  fachbereich, String vorname, String password, String nachname, String beschreibung, String studiengang) {


        this.matrikelnummer = matrikelnummer;
        this.fachbereich = fachbereich;
        this.password = password;
        this.vorname = vorname;
        this.nachname = nachname;
        this.beschreibung = beschreibung;
        this.studiengang = studiengang;
    }

    public Kandidat() {

    }

    public Long getId() {

        return this.id;
    }

    public String getMatrikelnummer() {
        return this.matrikelnummer;
    }

    public String getFachbereich() {
        return this.fachbereich;
    }

    public String getPassword() {
        return this.password;
    }

    public String getVorname() {
        return this.vorname;
    }

    public String getNachname() {
        return this.nachname;
    }

    public String getBeschreibung() {
        return this.beschreibung;
    }

    public String getStudiengang() {
        return this.studiengang;
    }

    public void setMatrikelnummer(String matrikelnummer) {
        this.matrikelnummer = matrikelnummer;
    }

    public void setFachbereich(String fachbereich) {
        this.fachbereich = fachbereich;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public void setStudiengang(String studiengang) {
        this.studiengang = studiengang;
    }
/**
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Kandidat kandidat))
            return false;
        return Objects.equals(getId(), kandidat.getId())
                && Objects.equals(getMatrikelnummer(), kandidat.getMatrikelnummer())
                && Objects.equals(getFachbereich(), kandidat.getFachbereich())
                && Objects.equals(getPassword(), kandidat.getPassword())
                && Objects.equals(getVorname(), kandidat.getVorname())
                && Objects.equals(getNachname(), kandidat.getNachname())
                && Objects.equals(getBeschreibung(), kandidat.getBeschreibung())
                && Objects.equals(getStudiengang(), kandidat.getStudiengang())
                && Objects.equals(antworten, kandidat.antworten);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getMatrikelnummer(), getFachbereich(), getPassword(), getVorname(), getNachname(),
                getBeschreibung(), getStudiengang(), antworten);
    }

    @Override
    public String toString() {
        return "Kandidat{" +
                "id=" + id +
                ", matrikelnummer='" + matrikelnummer + '\'' +
                ", fachbereich='" + fachbereich + '\'' +
                ", password='" + password + '\'' +
                ", vorname='" + vorname + '\'' +
                ", nachname='" + nachname + '\'' +
                ", beschreibung='" + beschreibung + '\'' +
                ", studiengang='" + studiengang + '\'' +
                ", antworten=" + Kandidatenantworten +
                '}';
    } */
}
