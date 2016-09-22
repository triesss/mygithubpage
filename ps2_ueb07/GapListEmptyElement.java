/**
 * Hiermit wird eine Freispeicherliste abgebildet, es können also mehrere
 * Elemente mit jeweils einer Lücke enthalten sein.
 */
package grp13_ueb07;

/**
 * Listenende
 * @author Gerit
 */
public class GapListEmptyElement implements GapList {
    
    /**
     * erzeugt eine Assertion und liefert null, da eine leere Liste kein Element
     * enthält
     * @return null, da eine leere Liste kein Element enthält
     */
    @Override
    public Gap getPayload() {
        assert false;
        return null;
    }

    /**
     * erzeugt eine Assertion und liefert null, da eine leere Liste kein Element
     * und somit auch keinen Nachfolger enthält
     * @return null, da eine leere Liste kein Element und somit auch keinen
     *         Nachfolger enthält
     */
    @Override
    public GapList getNext() {
        assert false;
        return null;
    }

    /**
     * liefert true, da hier eine leere Liste vorliegt
     * @return true, da hier eine leere Liste vorliegt
     */
    @Override
    public boolean isEmpty() {
        return true;
    }

    /**
     * liefert 0, da eine leere Liste keine Elemente enthält
     * @return 0, da eine leere Liste keine Elemente enthält
     */
    @Override
    public int length() {
        return 0;
    }

    /**
     * prüft, ob die gesamte Liste nur aus einem Element (außer dem Leerelement)
     * besteht
     * @return false, da nicht einmal ein Element enthalten ist
     */
    @Override
    public boolean hasJustOneElement() {
        return false;
    }

    /**
     * prüft, ob Lücke mit dieser Länge vorhanden ist
     * @param length Länge der benötigten Lücke
     * @return false, da so eine Lücke wohl nicht vorhanden ist
     */
    @Override
    public boolean hasGap(int length) {
        return false;
    }

    /**
     * enthält eine Lücke an der Position
     * @param position
     * @param length
     * @return false, da an dieser Stelle keine Lücke mehr zu finden ist
     */
    @Override
    public boolean hasGapAt(int position, int length) {
        return false;
    }

    /**
     * liefert die Länge der größten Lücke
     * @param yetLargest die Länge der größten Lücke
     * @return die Länge der größten Lücke
     */
    @Override
    public int getLargestGap(int yetLargest) {
        return yetLargest;
    }

    /**
     * liefert die größte Lücke, wenn dies aufgerufen wird, ist keine Lücke
     * enthalten
     * @return 0
     */
    @Override
    public int getLargestGap() {
        return 0;
    }

    /**
     * liefert die Position der kleinsten Lücke, die größer ist als minLength,
     * kann an dieser Stelle nur die bisher gefundene Position zurückgeben
     * @param minLength     Mindestgröße der gesuchten Lücke
     * @param yetMinLength  bisher kleinste gefundene Lücke mit Mindestgröße
     * @param foundPosition Position der bisher kleinsten gefundenen Lücke mit
     *                      Mindestgröße
     * @return Position der bisher gefundenen kleinsten Lücke, die größer ist
     *         als minLength
     */
    @Override
    public int getSmallestGapBiggerThan(int minLength, int yetMinLength, int foundPosition) {
        return foundPosition;
    }

    /**
     * sucht eine passende Lücke, die mindestens die übergebene Länge bietet,
     * jedoch möglichst klein ist
     * @param length gesuchte Mindestlänge
     * @return -1, da ohne GapElemente keine Lücke vorhanden ist
     */
    @Override
    public int findPositionFor(int length) {
        return -1;
    }

    /**
     * ist die Suche nach der zu belegenden Lücke hier gelandet, wird nichts
     * verändert
     * @param position Position, an der die Belegung erfolgen soll
     * @param length   Länge der Belegung
     * @return this
     */
    @Override
    public GapList remove(int position, int length) {
        return this;
    }

    /**
     * entfernt die übergebene Lücke
     * @param gap Lücke, die zu entfernen ist
     * @return die leere Liste
     */
    @Override
    public GapList remove(Gap gap) {
        return this;
    }

    /**
     * liefert eine neue Lücke mit dieser als Nachfolger
     * @param gap die Lücke, die der Liste zugefügt werden soll
     * @return ein neues Listenelement mit diesem als Nachfolger
     */
    @Override
    public GapList insert(Gap gap) {
        return new GapListElement(gap, this);
    }

    /**
     * liefert eine neue Lücke mit dieser als Nachfolger
     * @param position
     * @param length
     * @return ein neues Listenelement mit diesem als Nachfolger
     */
    @Override
    public GapList insert(int position, int length) {
        return this.insert(new Gap(position, position + length));
    }
    
    /**
     * vergleicht zwei Elemente
     * @param obj soll auch ein GapList sein
     * @return true, wenn beide Elemente das Listenende sind
     */
    @Override
    public boolean equals(Object obj) {
        return (obj != null && obj instanceof GapListEmptyElement);
    }

    /**
     * liefert für dieses letzte Element einen Leerstring
     * @return Leerstring
     */
    @Override
    public String toString() {
        return "";
    }

    // automatisch erzeugt
    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

}
