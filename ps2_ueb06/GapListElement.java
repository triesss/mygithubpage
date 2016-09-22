package grp13_ueb06;

import static java.lang.Integer.max;

/**
 *
 * @author Gerit
 */
public class GapListElement extends GapList {

    /**
     * Attribute der Liste sind ein nächstes Element und eine Lücke als Nutzlast
     */
    private GapList next;
    private Gap payload;

    /**
     * Konstruktor für ein neues Listenelement
     *
     * @param gap Lücke als Nutzlast
     * @param tail Referenz auf weitere Listenelemente
     */
    public GapListElement(Gap gap, GapList tail) {
        this.payload = gap;
        this.next = tail;
    }

    /**
     * liefert die Nutzlast (Getter)
     *
     * @return die Nutzlast
     */
    @Override
    public Gap getPayload() {
        return this.payload;
    }

    /**
     * liefert das nächste Element (Getter)
     *
     * @return das nächste Element
     */
    @Override
    public GapList getNext() {
        return this.next;
    }

    /**
     * liefert false, da mindestens ein Listenelement vorhanden sein muss
     *
     * @return false, da mindestens ein Listenelement vorhanden sein muss
     */
    @Override
    public boolean isEmpty() {
        return false;
    }

    /**
     * liefert Länge der Liste
     *
     * @return Länge der Liste
     */
    @Override
    public int length() {
        return (1 + this.next.length());
    }

    /**
     * prüft, ob die gesamte Liste nur aus einem Element (außer dem Leerelement)
     * besteht
     *
     * @return true, wenn die gesamte Liste nur aus einem Element (außer dem
     * Leerelement) besteht
     */
    @Override
    public boolean hasJustOneElement() {
        return this.next.isEmpty();
    }

    /**
     * prüft, ob Lücke mit dieser Länge vorhanden ist
     *
     * @param length Länge der benötigten Lücke
     * @return true, wenn eine Lücke mit dieser Länge vorhanden ist
     */
    @Override
    public boolean hasGap(int length) {
        if (this.payload.getLength() >= length) {
            return true;
        } else if (!this.next.isEmpty()) {
            return this.next.hasGap(length);
        } else {
            return false;
        }
    }

    /**
     * enthält eine Lücke an der Position, Startposition und Länge muss nicht
     * mit einer bestehenden übereinstimmen
     *
     * @param position Position der Lücke
     * @param length Länge der Lücke
     * @return true, wenn Lücke gefunden wurde, die Lücke mit den Angaben
     * enthält
     */
    @Override
    public boolean hasGapAt(int position, int length) {
        if (this.payload.contains(position)) {
            if (position + length <= this.payload.getEnd()) {
                return true;
            }
        }
        if (!this.next.isEmpty()) {
            return this.next.hasGapAt(position, length);
        }
        return false;
    }

    /**
     * liefert die Länge der größten Lücke
     *
     * @param yetLargest die bisher größte Länge
     * @return die Länge der größten Lücke
     */
    @Override
    int getLargestGap(int yetLargest) {
        // Wenn es noch ein folgendes Listenelement gibt, wird die Rekursion
        // fortgeführt. Dabei wird entweder die aktuelle Länge oder der
        // übergebene Parameter weiter gereicht (je nach dem welcher Größer ist)

        if (!this.next.isEmpty() && this.payload.getLength() >= yetLargest) {
            return this.next.getLargestGap(this.payload.getLength());
        } else if (!this.next.isEmpty() && this.payload.getLength() < yetLargest) {
            return this.next.getLargestGap(yetLargest);
        } else if (this.next.isEmpty() && this.payload.getLength() >= yetLargest) {
            return this.payload.getLength();
        } else {
            return yetLargest;
        }
    }

    /**
     * liefert die Länge der größten Lücke in der Liste
     *
     * @return die Länge der größten Lücke
     */
    @Override
    public int getLargestGap() {
        if (this.next.isEmpty()) {
            return this.payload.getLength();
        } else {
            return this.next.getLargestGap(this.payload.getLength());
        }
//        if (this.next.length() > this.payload.getLength()) {
//            return this.next.getLargestGap(this.next.length());
//        } else {
//            return this.next.getLargestGap(this.payload.getLength());
//        }
    }

    /**
     * liefert die Position der kleinsten Lücke, die größer ist als minLength
     *
     * @param minLength Mindestgröße der gesuchten Lücke
     * @param yetMinLength bisher kleinste gefundene Lücke mit Mindestgröße
     * @param foundPosition Position der bisher kleinsten gefundenen Lücke mit
     * Mindestgröße
     * @return Position der kleinsten Lücke, die größer ist als minLength, -1,
     * wenn keine passende Lücke gefunden wurde
     */
    @Override
    int getSmallestGapBiggerThan(int minLength, int yetMinLength, int foundPosition) {
        // aktuelle Position und Länge übernehmen, wenn es der erste Fund ist
        if (yetMinLength == 0 && this.payload.getLength() >= minLength) {
            yetMinLength = this.payload.getLength();
            foundPosition = this.payload.getStart();
        } // aktuelle Position und Länge übernehmen, wenn die aktuelle Lücke kleiner ist als die
        // bisher gefundene
        else if (this.payload.getLength() < yetMinLength && this.payload.getLength() >= minLength) {
            yetMinLength = this.payload.getLength();
            foundPosition = this.payload.getStart();
        }

        // rekursiver Aufruf, wenn es noch Nachfolgeelemente gibt, ansonsten Ausgabe des Ergebnisses
        if (!this.next.isEmpty()) {
            return this.next.getSmallestGapBiggerThan(minLength, yetMinLength, foundPosition);
        } else {
            return foundPosition;
        }
    }

    /**
     * sucht eine passende Lücke, die mindestens die übergebene Länge bietet,
     * jedoch möglichst klein ist
     *
     * @param length
     * @return passende Lücke, die mindestens die übergebene Länge bietet,
     * jedoch möglichst klein ist, -1, wenn keine passende Lücke gefunden wurde
     */
    @Override
    public int findPositionFor(int length) {
        return this.getSmallestGapBiggerThan(length, 0, -1);
    }

    /**
     * belegt Platz an der angegebenen Position mit der angegebenen Länge.
     * Dadurch kann eine Lücke komplett verschwinden (ausgefüllt werden), oder
     * verkürzt werden oder zwei Lücken entstehen, wenn die Belegung mittig
     * erfolgt. Die Nutzung von Gap.sub() ist sinnvoll.
     *
     * @param position Position der Belegung
     * @param length Länge der Belegung
     * @return die evtl. veränderte Liste
     */
    @Override
    public GapList remove(int position, int length) {
        // wenn die Position innerhalb der aktuellen Lücke liegt, wird die Funktion gap.sub(gap)
        // genutzt um die Lücke (teilweise) aufzufüllen oder aufzuteilen
        if (position >= this.payload.getStart() && (position + length) <= this.payload.getEnd()) {
            // Liste mit Array-Rückgabe aus gap.sub(gab) neu aufbauen
            Gap[] luecken;
            GapList liste = this.next;
            luecken = this.payload.sub(new Gap(position, (position + length)));
            for (int i = 0; i < luecken.length; i++) {
                liste = liste.insert(luecken[i]);
            }
            return liste;
        } // wenn die Position nicht innerhalb der aktuellen Lücke liegt, wird die Liste rekursiv
        // weiter durchgegangen
        else if (!this.next.isEmpty()) {
            return this.next.remove(position, length).insert(this.payload);
        } // am Ende wird die unveränderte Restliste zurück gegeben
        else {
            return this;
        }
    }

    /**
     * entfernt eine Lücke aus der Liste. Die Lücke muss nicht mit den gleichen
     * Grenzen in der Liste stehen! Entfernen einer Lücke ist gleichbedeutend
     * mit Okkupieren einer Stelle.
     *
     * @param gap freizugebendes Lücke
     * @return die Liste mit freigegebenem Lücke
     */
    @Override
    public GapList remove(Gap gap) {
        return this.remove(gap.getStart(), gap.getLength());
    }

    /**
     * fügt die Lücke als neues Listenelement sortiert ein. Überschneidet
     * (overlaps) oder berührt (touches) die einzufügende Lücke gap eine in der
     * Liste vorhandene Lücke, so werden diese beiden vereinigt (union) und in
     * die Liste eingefügt, die vormals vorhandene ausgekettet.
     *
     * @param gap Lücke, die als Element einzufügen ist
     * @return die Liste mit eingefügter Lücke
     */
    @Override
    public GapList insert(Gap gap) {
        //GapList liste = new GapList();

        if (this.payload.contains(gap)) {
            return this;
        } else if (this.payload.overlaps(gap) || this.payload.touches(gap)) {
            return this.next.insert(this.payload.union(gap));
        }
        else if (this.payload.succeeds(gap)) {
            // vor diese einfügen  
            GapListElement newElement = new GapListElement(gap, this);
            return newElement;
        } else {
            // weiterleiten
            this.next = this.next.insert(gap);
            return this;
        }

        // wenn die einzufügende Lücke vor, in oder direkt an dem ersten Listenelement liegt
        /*if (gap.getStart() <= this.payload.getEnd()) {
         // ist die einzufügende Lücke vollständig im ersten Listenelement enthalten, wird nichts
         // eingefügt, sondern nur die aktuelle Liste zurück gegeben
         if (this.payload.contains(gap)) {
         liste = this;
         } // Überlappt oder grenzt die einzufügende Lücke direkt an das erste Listenelement an,
         // wird eine Vereinigung der beiden angefügt. Entweder vor die folgenden Listenelemente
         // oder an eine neue Liste, wenn es keine Nachfolgeelemente gibt.
         else if (this.payload.overlaps(gap) || this.payload.touches(gap)) {
         liste = this.next.insert(this.payload.union(gap));
         } // Existiert weder eine Überlappung noch ein direktes Angrenzen, wird die Lücke vor das
         // erste Listenelement eingefügt. Hierfür wird die Methode der Superklasse genutzt um
         // eine Rekursion zu vermeiden.
         else {
         liste = this.next.insert(gap);

         }
         } // wenn die einzufügende Lücke hinter dem ersten Listenelement liegt
         else {
         // gibt es keine weiteren Listenelemente, wird das erste Element vor die einzufügende
         // Lücke gehängt
         if (this.next.isEmpty()) {
         liste = liste.insert(gap).insert(this.payload);
         } // gibt es Nachfolgeelemente, startet ein rekursiver Aufruf der Funktion, indem die
         // einzufügende Lücke mit dem Rest der Liste erneut aufgerufen wird und erst
         // anschließend wieder das erste Element vorn angehängt wird
         else {
         liste = this.next.insert(gap).insert(this.payload);
         }
         }
         return liste;*/
//        // erste Version - unübersichtlich
//        if (!this.payload.overlaps(gap) && !this.payload.touches(gap)) {
//            if (gap.getStart() < this.payload.getStart()) {
//                return super.insert(gap);
//            } else if (this.next.isEmpty()) {   
//                GapList liste = new GapList();
//                liste = liste.insert(gap);
//                liste = liste.insert(this.payload);
//                return liste;
//            } else {
//                return this.next.insert(this.payload);
//            }
//        } else if (!this.next.isEmpty()) {
//            return this.next.insert(this.payload.union(gap));
//        } else {
//            return this;
//        }
    }

    /**
     * fügt die Lücke als neues Listenelement sortiert ein.
     *
     * @param position Startposition der Lücke
     * @param length Länge der Lücke
     * @return die Liste mit eingefügter Lücke
     */
    @Override
    public GapList insert(int position, int length) {
        Gap luecke = new Gap(position, position + length);
        return this.insert(luecke);
    }

    /**
     * überschreibt equals. Vergleicht rekursiv alle Elemente.
     *
     * @param obj zu vergleichendes Objekt muss auch ein GapListElement sein
     * @return true, wenn dies und alle folgenden Elemente gleich sind
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof GapListElement)) {
            return false;
        } // wenn (irgendwo) eine Abweichung gefunden wird, wird mit false abgebrochen
        else if (!this.payload.equals(((GapListElement) obj).payload)) {
            return false;
        } // wenn es gleich ist und es weitere Elemente gibt, wird die Rekursion fortgesetzt
        else if (!this.next.isEmpty()) {
            return this.next.equals(((GapListElement) obj).next);
        } // wenn es (bis hier hin) gleich war und es keine Elemente mehr gibt, wird die Rekursion mit
        // true beendet
        else {
            return true;
        }
    }

    /**
     * liefert eine Auflistung der enthaltenen Lücken
     *
     * @return in einem String die Aufzählung aller enthaltenen Elemente
     */
    @Override
    public String toString() {
        if (!this.next.isEmpty()) {
            //System.out.println(this.payload.toString() + ", " + this.next.toString());
            return (this.payload.toString() + ", " + this.next.toString());
        } else {
            return (this.payload.toString());
        }
    }

    //--- Testroutinen ---------------------------------------------------------
    // testet insert auf
    // Einfügen unabhängiger Elemente
    // Einfügen angrenzender Elemente -> müssen vereinigt werden
    // Einfügen überlappender Elemente -> dürfen nicht eingefügt werden
    private static void testInsert() {

        GapList list = new GapList();

        Gap gap = new Gap(5, 10);
        list = list.insert(gap);
        assert 1 == list.length() : "erstes Element wurde nicht zugefügt";
        assert "  5.. 10".equals(gap.toString()) : "gap.toString() ist fehlerhaft";
        assert "  5.. 10".equals(list.toString()) : "GapListElement.toString() oder GapList.toString() mit einem Element ist fehlerhaft";

        gap = new Gap(31, 40);
        list = list.insert(gap);
        // System.out.println(list.toString());
        assert 2 == list.length() : "unabhängige Lücke wurde nicht angefügt";
        assert "  5.. 10,  31.. 40".equals(list.toString()) : "GapListElement.toString() oder GapList.toString() mit zwei Elementen ist fehlerhaft";

        // 5..10,  31.. 40
        gap = new Gap(10, 20);
        list = list.insert(gap);
        // System.out.println(list.toString());
        assert 2 == list.length() : "aneinandergrenzende Lücken sollten vereinigt werden";
        assert "  5.. 20,  31.. 40".equals(list.toString());

        // 5..20,  31.. 40
        gap = new Gap(0, 5);
        list = list.insert(gap);
        // System.out.println(list.toString());
        assert 2 == list.length() : "aneinandergrenzende Lücken sollten vereinigt werden";
        assert "  0.. 20,  31.. 40".equals(list.toString());

        //  0..20,   31.. 40
        gap = new Gap(32, 35);
        // System.out.println(list.toString());
        list = list.insert(gap);
        // System.out.println(list.toString());
        assert 2 == list.length() : gap.toString() + " darf nicht zugefügt werden, ist bereits enthalten";
        assert "  0.. 20,  31.. 40".equals(list.toString());

        System.out.println("Test insert() erfolgreich");

    }

    /**
     * testet hasJustOneElement() isEmpty() hasGap()
     */
    private static void testHasRoutines() {

        GapList list = new GapList();

        assert list.isEmpty();
        assert !list.hasJustOneElement();
        assert !list.hasGap(5);

        list = list.insert(new Gap(5, 10));

        assert !list.isEmpty();
        assert list.hasJustOneElement();
        assert list.hasGap(1);
        assert list.hasGap(5);
        assert !list.hasGap(6);

        list = list.insert(new Gap(15, 55));

        assert !list.isEmpty();
        assert !list.hasJustOneElement();
        assert list.hasGap(15);
        assert list.hasGap(40);
        assert !list.hasGap(50);

        System.out.println("Test hasRoutines() erfolgreich");
    }

    /**
     * testet getLargestGap()
     */
    private static void testGetLargestGap() {

        GapList list = new GapList();

        assert 0 == list.getLargestGap();

        list = list.insert(new Gap(10, 15));
        assert 5 == list.getLargestGap();

        list = list.insert(new Gap(100, 106));
        assert 6 == list.getLargestGap();

        list = list.insert(new Gap(50, 57));
        assert 7 == list.getLargestGap();

        list = list.insert(new Gap(0, 8));
        assert 8 == list.getLargestGap();

        System.out.println("Test GetLargestGap() erfolgreich");
    }

    /**
     * testet remove() auf Entfernen in der Mitte einer Lücke Entfernen am
     * Anfang einer Lücke Entfernen am Ende einer Lücke nicht zulässiges
     * Entfernen
     */
    private static void testRemoveGap() {
        GapList list = new GapList();

        list = list.insert(new Gap(0, 20));
        list = list.insert(new Gap(31, 40));

        // Verkürzen eines Elements am Anfang, am Ende und mittig
        list = list.remove(new Gap(15, 20));
        // System.out.println(list.toString());
        assert 2 == list.length() : "erste Lücke darf nur verkürzt werden";
        assert "  0.. 15,  31.. 40".equals(list.toString());

        list = list.remove(new Gap(0, 5));
        // System.out.println(list.toString());
        assert 2 == list.length() : "erste Lücke darf nur verkürzt werden";
        assert "  5.. 15,  31.. 40".equals(list.toString());

        list = list.remove(new Gap(10, 12));   // 2 Lücken entstehen statt der ersten
        // System.out.println(list.toString());
        assert 3 == list.length() : "erste Lücke müsste aufgeteilt werden";
        assert "  5.. 10,  12.. 15,  31.. 40".equals(list.toString());

        // ungültige Entfernversuche
        list = list.remove(new Gap(10, 20));
        assert 3 == list.length() : "bereits belegter Platz darf nicht belegt werden";
        assert "  5.. 10,  12.. 15,  31.. 40".equals(list.toString());

        list = list.remove(new Gap(10, 13));
        assert 3 == list.length() : "nicht existente Lücke kann nicht entfernt werden";
        assert "  5.. 10,  12.. 15,  31.. 40".equals(list.toString());

        list = list.remove(new Gap(13, 20));
        assert 3 == list.length() : "bereits belegter Platz darf nicht belegt werden";
        assert "  5.. 10,  12.. 15,  31.. 40".equals(list.toString());

        // Rest mit genauer Angabe komplett entfernen
        list = list.remove(new Gap(31, 40));
        assert 2 == list.length() : "Lücke wird komplett belegt";
        assert "  5.. 10,  12.. 15".equals(list.toString());

        list = list.remove(new Gap(5, 10));
        list = list.remove(new Gap(12, 15));
        assert list.isEmpty() : "Liste soll jetzt leer sein";

        System.out.println("Test remove(gap) erfolgreich");
    }

    /**
     * testet remove auf Entfernen in der Mitte einer Lücke Entfernen am Anfang
     * einer Lücke Entfernen am Ende einer Lücke nicht zulässiges Entfernen
     */
    private static void testRemovePosLen() {
        GapList list = new GapList();

        list = list.insert(new Gap(0, 20));
        list = list.insert(new Gap(31, 40));

        // Verkürzen eines Elements am Anfang, am Ende und mittig
        list = list.remove(15, 5);
        // System.out.println(list.toString());
        assert 2 == list.length() : "erste Lücke darf nur verkürzt werden";
        assert "  0.. 15,  31.. 40".equals(list.toString());

        list = list.remove(0, 5);
        // System.out.println(list.toString());
        assert 2 == list.length() : "erste Lücke darf nur verkürzt werden";
        assert "  5.. 15,  31.. 40".equals(list.toString());

        list = list.remove(10, 2);   // 2 Lücken entstehen statt der ersten
        // System.out.println(list.toString());
        assert 3 == list.length() : "erste Lücke müsste aufgeteilt werden";
        assert "  5.. 10,  12.. 15,  31.. 40".equals(list.toString());

        // ungültige Entfernversuche
        list = list.remove(10, 10);
        assert 3 == list.length() : "bereits belegter Platz darf nicht belegt werden";
        assert "  5.. 10,  12.. 15,  31.. 40".equals(list.toString());

        list = list.remove(10, 3);
        assert 3 == list.length() : "nicht existente Lücke kann nicht entfernt werden";
        assert "  5.. 10,  12.. 15,  31.. 40".equals(list.toString());

        list = list.remove(13, 7);
        assert 3 == list.length() : "bereits belegter Platz darf nicht belegt werden";
        assert "  5.. 10,  12.. 15,  31.. 40".equals(list.toString());

        // Rest mit genauer Angabe komplett entfernen
        list = list.remove(31, 9);
        assert 2 == list.length() : "Lücke wird komplett belegt";
        assert "  5.. 10,  12.. 15".equals(list.toString());

        list = list.remove(5, 5);
        list = list.remove(12, 3);
        assert list.isEmpty() : "Liste soll jetzt leer sein";

        System.out.println("Test testRemove(pos,len) erfolgreich");
    }

    /**
     * testet FindPositionFor den ersten kleinstmöglichen Platz zu finden
     * ungültige Suchangaben liefern Fehlerwert
     */
    private static void testFindPositionFor() {
        GapList list = new GapList();
        assert -1 == list.findPositionFor(1);

        list = list.insert(new Gap(0, 10));
        list = list.insert(new Gap(12, 15));
        list = list.insert(new Gap(20, 33));

        assert 0 == list.findPositionFor(10);
        assert 0 == list.findPositionFor(8);
        assert 12 == list.findPositionFor(3);
        assert 12 == list.findPositionFor(2);
        assert 12 == list.findPositionFor(1);
        assert 12 == list.findPositionFor(0);

        assert 20 == list.findPositionFor(11);

        assert -1 == list.findPositionFor(15);
        assert -1 == list.findPositionFor(14);

        System.out.println("Test findPositionFor() erfolgreich");
    }

    /**
     * Tests der geschriebenen Methoden per Assertions
     *
     * @param args
     */
    public static void main(String[] args) {

        testInsert();
        testHasRoutines();
        testGetLargestGap();
        testRemoveGap();
        testRemovePosLen();
        testFindPositionFor();

    }

}
