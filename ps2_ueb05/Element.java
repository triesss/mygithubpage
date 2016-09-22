/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grp13_ueb05;

/**
 * Klasse Element komplett neu erstellt
 * @author Paul
 */
public class Element extends ElementList {
    
    protected Figure value;
    protected ElementList next;
    
    /**
     * Konstruktor für Element
     * @param value Figur
     * @param next Zeiger auf nächsten Listeneintrag
     */
    public Element(Figure value, ElementList next){
        this.value = value;
        this.next = next;
    }
    
    /**
     * Methode überschreibt die gleichnamige Methode der Superklasse
     * @return false
     */
    public boolean isEmpty(){
        return false;
    }
    
    /**
     * Methode fügt eine Figur sortiert in die Liste ein
     * @param value einzufügende Figur
     * @return neue Liste
     */
    public ElementList append(Figure value){
        // wenn die übergebene Figur eine größere Fläche hat, wird sie vor das
        // aktuelle Element eingesetzt
        if (this.value.compare(value) == -1){
            Element nextValue = new Element(this.value, next);
            this.value = value;
            this.next = nextValue;
        } 
        // wenn die übergebene Figur eine gleich große oder kleinere Fläche
        // besitzt, soll dahinter eingefügt werden. Dazu wird die Methode mit
        // dem Nachfolgeelement rekursiv aufgerufen
        else {
            this.next = this.next.append(value);
        }
        return this;
    }

    /**
     * Methode erzeugt einen String der zeilenweise die Beschreibung der Figuren
     * enthält. Dazu wird die Methode solange rekursiv aufgerufen, bis das
     * Listenende erreicht ist
     * @param i übergebener Zähler für die Zeilennummerierung
     * @return String mit Figurenbeschreibungen
     */
    public String toString(int i){
        if (next.isEmpty()){
            return (String.format("%2d.%s%n", i, this.value.toString()));
        } else {
            return (String.format("%2d.%s%n%s", i, this.value.toString(), 
                    next.toString(i+1)));
        }
    }

    
    // Main für Tests der Klasse Element
    public static void main(String[] args) {
        ElementList list = new ElementList();
        list = list.append(new Rectangle(new Color(0,0,0), 2, 3));
        assert(1 == list.lengthOfList());
        list = list.append(new Circle(new Color(0xFF, 0xFF, 0xFF), 3));
        assert(2 == list.lengthOfList());
    }
}
    
// Methoden zur neuen Listenstruktur aus dem Skript, die für diese Aufgabe
// nicht benötigt werden
    
//    public int lengthOfList(){
//        return (1 + this.next.lengthOfList());
//    }
    
//    public void printList(){
//        System.out.println(this.value);
//        this.next.printList();
//    }

//    public ElementList find(Figure value){
//        if (this.value == value){
//            return this;
//        } else {
//            return this.next.find(value);
//        }
//    }
    
//    public ElementList insertAt(Figure value, int pos){
//        if (pos < 0 || pos > this.lengthOfList()){
//            System.err.println("Fehler: Falsche Einfügeposition");
//            return this;
//        } else {
//            if (pos == 0){
//                return new Element(value, this);
//            } else {
//                this.next = this.next.insertAt(value, pos - 1);
//                return this;
//            }
//        }
//    }
    
//    public ElementList remove(Figure value){
//        if (this.value == value){
//            return this.next;
//        } else {
//            this.next = this.next.remove(value);
//            return this;
//        }
//    }

//
//
///**
// * alte Version der Klasse Element
// * @author Paul
// */
//public class Element {
//
//    private int value;
//    private Element next;
//
//    // Übernommene Methoden aus dem Skript
//    /**
//     * Anhängen eines neuen Listenelements hinter den vorhandenen
//     * Listenelementen
//     *
//     * @param value anzuhängendes Listenelement
//     * @return Liste nach dem Anhängen
//     */
//    public Element appendElement(int value) {
//        if (this.next == null) {
//            Element newElement = new Element();
//            newElement.setValue(value);
//            this.next = newElement;
//        } else {
//            this.next = this.next.appendElement(value);
//        }
//        return this;
//    }
//
//    /**
//     * Sortiertes Einfügen eines neuen Listenelements
//     *
//     * @param value einzufügendes Listenelement
//     * @return Liste nach dem Einfügen
//     */
//    public Element insertElementSorted(int value) {
//        if (this.value > value) {
//            // vor akutelles einfügen
//            Element newElement = new Element();
//            newElement.setValue(value);
//            newElement.setNext(this);
//            return newElement;
//        } else if (this.next == null) {
//            // hinter letztes anhängen
//            Element newElement = new Element();
//            newElement.setValue(value);
//            this.next = newElement;
//            return this;
//        } else {
//            // Stelle suchen
//            this.next = this.next.insertElementSorted(value);
//            return this;
//        }
//    }
//
//    /**
//     * Löschen eines Listenelements
//     *
//     * @param value zu löschendes Element
//     * @return Liste nach dem Löschen
//     */
//    public Element deleteElement(int value) {
//        if (this.value > value) {
//            return this;
//        } else if (this.value == value) {
//            return this.next;
//        } else {
//            if (this.next != null) {
//                this.next = this.next.deleteElement(value);
//            }
//            return this;
//        }
//    }
//
//    // eigene Methoden
//    /**
//     * Liefert den Wert des Listenelements
//     *
//     * @return Wert des Listenelements
//     */
//    public int getValue() {
//        return value;
//    }
//
//    /**
//     * Setzt den Wert eines Listenelementes
//     *
//     * @param value zu setzender Wert
//     */
//    public void setValue(int value) {
//        this.value = value;
//    }
//
//    /**
//     * Setzt den nachfolger eines Listenelementes
//     *
//     * @param next zu setzender Nachfolger
//     */
//    public void setNext(Element next) {
//        this.next = next;
//    }
//
//    /**
//     * Liefert die Anzahl der Einträge einer Liste
//     *
//     * @return Anzahl der Listeneinträge
//     */
//    public int size() {
//        int count;
//
//        if (this.next != null) {
//            count = 1 + this.next.size();
//        } else {
//            count = 1;
//        }
//        return count;
//    }
//
//    /**
//     * #####FUER DIESE AUFGABE NICHT BENOETIGT##### Liefert die Summe aller
//     * Listeneinträge
//     *
//     * @return Summe aller Listeneinträge
//     */
////    public int sum() {
////        int sum;
////        if (this.next != null) {
////            sum = this.value + this.next.sum();
////        } else {
////            return this.value;
////        }
////        return sum;
////    }
//    /**
//     * #####FUER DIESE AUFGABE NICHT BENOETIGT##### prüft die Listeneinträge
//     * aufsteigend sortiert sind
//     *
//     * @return true wenn sortiert, false wenn nicht sortiert
//     */
////    public boolean isSorted() {
////        boolean check = false;
////
////        // erster Fall: es gibt einen Nachfolger und der ist größer oder gleich
////        // dann darf die Rekursion die Liste weiter durchsuchen
////        if ((this.next != null) && (this.next.getValue() >= this.value)) {
////            check = this.next.isSorted();
////        } // zweiter Fall: es gibt einen Nachfolger aber dessen Wert ist kleiner
////        // dann soll die Rekursion abbrechen und false liefern
////        else if ((this.next != null) && (this.next.getValue() < this.value)) {
////            check = false;
////        } // dritter Fall: es gibt keinen Nachfolger bzw. die Rekursion ist am Ende angekommen
////        // dann ist die Liste sortiert und es wird true zurück geliefert
////        else {
////            check = true;
////        }
////
////        return check;
////    }
//    /**
//     * prüft ob im aktuellen Listenelement oder folgenden Elementen, der
//     * übergebene Wert enthalten ist
//     *
//     * @param value zu prüfender Wert
//     * @return true wenn der Wert enthalten ist, false wenn nicht
//     */
//    public boolean containsValue(int value) {
//        boolean check = false;
//
//        if (this.value <= value) {
//
//        // erster Fall: der Wert des aktuellen Listenelements stimmt mit dem übergabenen Wert überein
//            // dann wird true zurück geliefert
//            if (this.value == value) {
//                check = true;
//            } // zweiter Fall: es gibt einen Nachfolger der geprüft werden kann
//            // Rekursion startet
//            else if (this.next != null) {
//                check = this.next.containsValue(value);
//            }
//        // dritter Fall: Wert war bisher nicht identisch und es gibt keinen Nachfolger
//            // dann bleibt die Prüfvariable "check" auf dem Initialisierungswert false
//        }
//        return check;
//    }
//
//    /**
//     * liefert den Wert des Listenelements und allen folgenden Elementen als
//     * String
//     *
//     * @return Wert des Listenelements und allen folgenden Elementen als String
//     */
//    public String showValues() {
//        String valueList = "";
//
//        if (this.next != null) {
//            valueList = this.value + " " + this.next.showValues();
//        } else {
//            valueList += this.value;
//        }
//
//        return valueList;
//
//    }
//
//    /**
//     * liefert den Wert eines bestimmten Listenelementes
//     *
//     * @param index Indexwert des Listenelementes ausgegeben werden soll
//     * @return Wert des Listenelements (Ausnahme: Falscher Indexwert führt - es
//     * wird MAX_VALUE zurück gegeben)
//     */
//    public int getValueAt(int index) {
//        int count = 0;
//
//        // erster Fall: die richtige Position der Liste ist erreicht
//        // dann wird der aktuelle Wert zurück gegeben (über getValue wegen dem Geheimnisprinzip)
//        if (count == index) {
//            return this.getValue();
//        } // zweiter Fall: die richtige Position der Liste ist noch nicht erreicht und es gibt noch Nachfolger
//        // dann wird die Rekursion gestartet (dabei wird der Indexwert runter gezählt)
//        else if ((this.next != null) && (count < index)) {
//            return this.next.getValueAt(index - 1);
//        } // dritter Fall: die richtige Position ist nicht erreicht und es gibt keinen Nachfolger mehr
//        // dann soll laut Aufgabenstellung Integer.MAX_VALUE zurück geliefert werden
//        else {
//            return Integer.MAX_VALUE;
//        }
//    }
//
//    /**
//     * #####FUER DIESE AUFGABE NICHT BENOETIGT#####
//     *
//     * Fügt ein neues Element mit dem übergebenen Wert an der Stelle index ein
//     *
//     * @param value Wert der eingefügt werden soll
//     * @param index Position in der Liste in der das Element eingefügt werden
//     * soll
//     * @return veränderte Liste (Ausnahme: falscher Indexwert - es wird die
//     * unveränderte Liste zurück gegeben)
//     */
////    public Element insertValueAt(int value, int index) {
////        int count = 0;
////
////        // erster Fall: die richtige Position der Liste ist erreicht
////        // dann wird der Wert mittels der Methode insertElement eingefügt
////        // diese Methode beachtet, dass kein Element verloren geht und liefert
////        // die gesamte Liste zurück
////        if (count == index) {
////            Element next = new Element();
////            next.setValue(value);
////            next.setNext(this);
////            return next;
//////            next.value = this.value;
//////            next.next = this.next;
//////            this.value = value;
//////            this.next = next;
//// //           return this;
////        } // zweiter Fall: die richtige Position der Liste ist noch nicht erreicht und es gibt noch Nachfolger
////        // dann wird die Rekursion gestartet (dabei wird der Indexwert runter gezählt)
////        else if ((this.next != null) && (count < index)) {
////            this.next = this.next.insertValueAt(value, index - 1);
////            return this;
////        } // dritter Fall: die richtige Position ist nicht erreicht und es gibt keinen Nachfolger mehr
////        // dann soll laut Aufgabenstellung die unveränderte Liste zurück geliefert werden
////        else {
////            return this;
////        }
////    }
//    /**
//     * #####FUER DIESE AUFGABE NICHT BENOETIGT#####
//     *
//     * fügt ein neues Element mit dem übergebenen Wert vor das aktuelle Element
//     * ein
//     *
//     * @param value einzufgender Wert
//     * @return veränderte Liste
//     */
////    public Element insertValueAtFront(int value) {
////        // das Einfügen an erster Stelle, ist identisch zu dem Einfügen an der
////        // Indexposition 0, daher kann die entsprechende Methode genutzt werden
////        return this.insertValueAt(value, 0);
////    }
//    // neue Methode für Aufgabe 4
//    /**
//     * prüft, ob dies und die jeweiligen Folgeelemente die gleichen Werte
//     * enthalten und die Listen gleich lang sind
//     *
//     * @param other Prüfelement
//     * @return true wenn Gleichheit festgestellt, sonst false
//     */
//    public boolean isSame(Element other) {
//        if (other == null) {
//            return false;
//        }
//        // wenn der Wert des aktuellen Elementes identisch ist und es in BEIDEN
//        // Listen nachfolgende Elemente gibt, wird die Rekursion gestartet bzw.
//        // weiter geführt
//        if ((this.value == other.value) && (this.next != null) && (other.next != null)) {
//            return this.next.isSame(other.next);
//        } // wenn der Wert des aktuellen Elementes identisch ist und es in BEIDEN
//        // Listen keine Folgeelemente gibt, wird die Rekursion mit return true
//        // beendet
//        else if ((this.value == other.value) && (this.next == null) && (other.next == null)) {
//            return true;
//        } // in allen anderen Fällen (Werte nicht identisch oder eine der Listen
//        // ist länger als die andere) wird mit return false beendet
//        else {
//            return false;
//        }
//    }
//}
