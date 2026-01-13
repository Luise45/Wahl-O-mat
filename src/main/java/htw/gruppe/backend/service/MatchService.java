/**
 *
 * MATCHING-LOGIK DES WAHL-O-MATS
 *
 * Berechnet die Übereinstimmung ("Match") zwischen:
 *  - den Antworten eines Kandidaten  (candidateValues)
 *  - den Antworten eines Wählers     (voterValues)
 *
 * Berechnungskonzept:
 *  1. Vergleich erfolgt frageweise (1–10 Skala).
 *  2. Neutralbereich (5 & 6) wird speziell behandelt:
 *      - beide neutral  → 100% Übereinstimmung
 *      - einer neutral → 50% Übereinstimmung
 *  3. Unterschiedswerte werden in Prozent umgerechnet:
 *      Abstand 0 → 100%
 *      Abstand 1 → 90%
 *      Abstand 2 → 80%
 *      ...
 *  4. Null-Werte (fehlende DB-Werte) werden abgefangen und als 0 interpretiert.
 *  5. Am Ende wird der Durchschnitt aller Fragen gebildet.
 *
 * Rückgabewert:
 *  → eine Match-Zahl zwischen 0 und 100
 * 
 * @author Dumke
 * 
 */

package htw.gruppe.backend.service;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class MatchService {

    /**
     * Berechnet den Gesamt-Matchwert zwischen Kandidat und Wähler.
     * 
     * @param candidateValues Liste aller Antwortwerte eines Kandidaten
     * @param voterValues     Liste aller Antwortwerte des Nutzers
     * @return Matchwert zwischen 0 und 100
     */

    public double calculateTotalMatch(List<Integer> candidateValues, List<Integer> voterValues) {

        double sum = 0;

        // Es wird die kleinere Listenlänge genutzt, um Indexfehler zu vermeiden.
        int count = Math.min(candidateValues.size(), voterValues.size());

        for (int i = 0; i < count; i++) {

            Integer cVal = candidateValues.get(i);
            Integer vVal = voterValues.get(i);

            // Null-Werte abfangen (wenn in der DB answer_value = null)
            if (cVal == null || vVal == null) {
                System.out.println("WARN: Null-Wert bei Index " + i);
                cVal = 0; // neutral interpretieren
                vVal = 0;
            }

            //// Einzelmatching berechnen
            sum += calculateMatchSingle(cVal.intValue(), vVal.intValue());
        }

        // Falls count == 0 (z. B. Kandidat hat keine Antworten), Rückgabe 0
        if (count == 0) {
            return 0;
        }

        // Durchschnitt bilden
        return sum / count;
    }

    /**
     * Berechnet den Matchwert für eine einzelne Frage.
     *
     * @param candidateValue Antwortwert des Kandidaten (1–10)
     * @param voterValue     Antwortwert des Wählers (1–10)
     * @return Prozentwert der Übereinstimmung (0–100)
     */

    public double calculateMatchSingle(int candidateValue, int voterValue) {

        /*
         * "Neutral" auskommentiert weil verfälschtes Ergebnis??
         * 
         * // Neutraler Bereich (5 und 6) → (5=6 → maximale Übereinstimmung)
         * if (isNeutral(candidateValue) && isNeutral(voterValue)) {
         * return 100.0;
         * }
         * 
         * // Wenn nur einer neutral ist → halbe Übereinstimmung
         * if (isNeutral(candidateValue) || isNeutral(voterValue)) {
         * return 50.0;
         * }
         */

        // Abstand 0 → 100%, Abstand 1 → 90%, ..., Abstand 10 → 0%
        int distance = Math.abs(candidateValue - voterValue);
        return Math.max(0, 100 - distance * 10);
    }

    /*
     * // Prüft, ob ein Wert im Neutralbereich liegt
     * private boolean isNeutral(int value) {
     * return value == 5 || value == 6;
     * }
     */
}
