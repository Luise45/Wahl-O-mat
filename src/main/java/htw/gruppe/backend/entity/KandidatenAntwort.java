/**
 * Entitaet für Kandidaten Antworten
 *
 * @author Tabatt
 * @version 1.0
 */

package htw.gruppe.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "kandidaten_antworten")
public class KandidatenAntwort {
    /**
     * Kandidaten Antworten
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kandidaten_antwort_id")
    private Long id;

    @Column(name = "answer_value")
    private int answerValue;
    /**
     * hohlt die kandidaten id und die aussage id von Kandidat und Aussage
     */
    @ManyToOne
    @JoinColumn(name = "kandidat_id", nullable = false)
    //@JsonBackReference("kandidat_id")
    private Kandidat kandidat;

    @ManyToOne
    @JoinColumn(name = "aussage_id", nullable = false)
    //@JsonBackReference("aussage_id")
    private Aussage aussage;



    public KandidatenAntwort() {
    }

    /**
     * Konstruktor Kandidaten antworten fur den Controller
<<<<<<< HEAD
     * 
     * @param kandidat
     * @param aussage
     * @param answerValue
=======
     * @param kandidat für Kandidat
     * @param aussage für Aussagen
     * @param answerValue für Antwort des Kandidaten
>>>>>>> main
     */
    public KandidatenAntwort(Kandidat kandidat, Aussage aussage, int answerValue) {
        this.kandidat = kandidat;
        this.aussage = aussage;
        this.answerValue = answerValue;
    }

    public Long getId() {
        return id;
    }

    public Kandidat getKandidat() {
        return kandidat;
    }

    public void setKandidat(Kandidat kandidat) {
        this.kandidat = kandidat;
    }

    public Aussage getAussage() {
        return aussage;
    }

    public void setAussage(Aussage aussage) {
        this.aussage = aussage;
    }

    public int getAnswerValue() {
        return answerValue;
    }

    public void setAnswerValue(int answerValue) {
        this.answerValue = answerValue;
    }

    /**
     *
<<<<<<< HEAD
     * @param o the reference object with which to compare.
=======
     * @param o object
>>>>>>> main
     * @return equals object

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof KandidatenAntwort that))
            return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
     */
    /**
     *
     * @return string der Kandidaten Antworten

    @Override
    public String toString() {
        return "KandidatenAntwort{" +
                "id=" + id +
                ", kandidat=" + kandidat +
                ", aussage=" + aussage +
                ", answerValue=" + answerValue +
                '}';
      }
       */
}
