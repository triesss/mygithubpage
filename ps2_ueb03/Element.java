/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grp13_ueb03;

/**
 *
 * @author Paul
 */
public class Element {

    private int value;
    private Element next;

    // Übernommene Methoden aus dem Skript
    /**
     * Anhängen eines neuen Listenelements hinter den vorhandenen
     * Listenelementen
     *
     * @param value anzuhängendes Listenelement
     * @return Liste nach dem Anhängen
     */
    public Element appendElement(int value) {
        if (this.next == null) {
            Element newElement = new Element();
            newElement.setValue(value);
            this.next = newElement;
        } else {
            this.next = this.next.appendElement(value);
        }
        return this;
    }

    /**
     * Sortiertes Einfügen eines neuen Listenelements
     *
     * @param value einzufügendes Listenelement
     * @return Liste nach dem Einfügen
     */
    public Element insertElementSorted(int value) {
        if (this.value > value) {
            // vor akutelles einfügen
            Element newElement = new Element();
            newElement.setValue(value);
            newElement.setNext(this);
            return newElement;
        } else if (this.next == null) {
            // hinter letztes anhängen
            Element newElement = new Element();
            newElement.setValue(value);
            this.next = newElement;
            return this;
        } else {
            // Stelle suchen
            this.next = this.next.insertElementSorted(value);
            return this;
        }
    }

    /**
     * Löschen eines Listenelements
     *
     * @param value zu löschendes Element
     * @return Liste nach dem Löschen
     */
    public Element deleteElement(int value) {
        if (this.value == value) {
            return this.next;
        } else {
            if (this.next != null) {
                this.next = this.next.deleteElement(value);
            }
            return this;
        }
    }

    // eigene Methoden

    /**
     * Liefert den Wert des Listenelements
     *
     * @return Wert des Listenelements
     */
    public int getValue() {
        return value;
    }

    /**
     * Setzt den Wert eines Listenelementes
     *
     * @param value zu setzender Wert
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Setzt den nachfolger eines Listenelementes
     *
     * @param next zu setzender Nachfolger
     */
    public void setNext(Element next) {
        this.next = next;
    }

    /**
     * Liefert die Anzahl der Einträge einer Liste
     *
     * @return Anzahl der Listeneinträge
     */
    public int size() {
        int count;

        if (this.next != null) {
            count = 1 + this.next.size();
        } else {
            count = 1;
        }
        return count;
    }

    /**
     * Liefert die Summe aller Listeneinträge
     *
     * @return Summe aller Listeneinträge
     */
    public int sum() {
        int sum;
        if (this.next != null) {
            sum = this.value + this.next.sum();
        } else {
            return this.value;
        }
        return sum;
    }

    /**
     * prüft die Listeneinträge aufsteigend sortiert sind
     *
     * @return true wenn sortiert, false wenn nicht sortiert
     */
    public boolean isSorted() {
        boolean check = false;

        // erster Fall: es gibt einen Nachfolger und der ist größer oder gleich
        // dann darf die Rekursion die Liste weiter durchsuchen
        if ((this.next != null) && (this.next.getValue() >= this.value)) {
            check = this.next.isSorted();
        } // zweiter Fall: es gibt einen Nachfolger aber dessen Wert ist kleiner
        // dann soll die Rekursion abbrechen und false liefern
        else if ((this.next != null) && (this.next.getValue() < this.value)) {
            check = false;
        } // dritter Fall: es gibt keinen Nachfolger bzw. die Rekursion ist am Ende angekommen
        // dann ist die Liste sortiert und es wird true zurück geliefert
        else {
            check = true;
        }

        return check;
    }

    /**
     * prüft ob im aktuellen Listenelement oder folgenden Elementen, der
     * übergebene Wert enthalten ist
     *
     * @param value zu prüfender Wert
     * @return true wenn der Wert enthalten ist, false wenn nicht
     */
    public boolean containsValue(int value) {
        boolean check = false;

        // erster Fall: der Wert des aktuellen Listenelements stimmt mit dem übergabenen Wert überein
        // dann wird true zurück geliefert
        if (this.value == value) {
            check = true;
        } // zweiter Fall: es gibt einen Nachfolger der geprüft werden kann
        // Rekursion startet
        else if (this.next != null) {
            check = this.next.containsValue(value);
        }
        // dritter Fall: Wert war bisher nicht identisch und es gibt keinen Nachfolger
        // dann bleibt die Prüfvariable "check" auf dem Initialisierungswert false

        return check;
    }

    /**
     * liefert den Wert des Listenelements und allen folgenden Elementen als
     * String
     *
     * @return Wert des Listenelements und allen folgenden Elementen als String
     */
    public String showValues() {
        String valueList = "";

        if (this.next != null) {
            valueList = this.value + " " + this.next.showValues();
        } else {
            valueList += this.value;
        }

        return valueList;

    }

    /**
     * liefert den Wert eines bestimmten Listenelementes
     *
     * @param index Indexwert des Listenelementes ausgegeben werden soll
     * @return Wert des Listenelements (Ausnahme: Falscher Indexwert führt - es
     * wird MAX_VALUE zurück gegeben)
     */
    public int getValueAt(int index) {
        int count = 0;

        // erster Fall: die richtige Position der Liste ist erreicht
        // dann wird der aktuelle Wert zurück gegeben (über getValue wegen dem Geheimnisprinzip)
        if (count == index) {
            return this.getValue();
        } // zweiter Fall: die richtige Position der Liste ist noch nicht erreicht und es gibt noch Nachfolger
        // dann wird die Rekursion gestartet (dabei wird der Indexwert runter gezählt)
        else if ((this.next != null) && (count < index)) {
            return this.next.getValueAt(index - 1);
        } // dritter Fall: die richtige Position ist nicht erreicht und es gibt keinen Nachfolger mehr
        // dann soll laut Aufgabenstellung Integer.MAX_VALUE zurück geliefert werden
        else {
            return Integer.MAX_VALUE;
        }
    }

    /**
     * Fügt ein neues Element mit dem übergebenen Wert an der Stelle index ein
     *
     * @param value Wert der eingefügt werden soll
     * @param index Position in der Liste in der das Element eingefügt werden
     * soll
     * @return veränderte Liste (Ausnahme: falscher Indexwert - es wird die
     * unveränderte Liste zurück gegeben)
     */
    public Element insertValueAt(int value, int index) {
        int count = 0;

        // erster Fall: die richtige Position der Liste ist erreicht
        // dann wird der Wert mittels der Methode insertElement eingefügt
        // diese Methode beachtet, dass kein Element verloren geht und liefert
        // die gesamte Liste zurück
        if (count == index) {
            Element next = new Element();
            next.setValue(value);
            next.setNext(this);
            return next;
//            next.value = this.value;
//            next.next = this.next;
//            this.value = value;
//            this.next = next;
 //           return this;
        } // zweiter Fall: die richtige Position der Liste ist noch nicht erreicht und es gibt noch Nachfolger
        // dann wird die Rekursion gestartet (dabei wird der Indexwert runter gezählt)
        else if ((this.next != null) && (count < index)) {
            this.next = this.next.insertValueAt(value, index - 1);
            return this;
        } // dritter Fall: die richtige Position ist nicht erreicht und es gibt keinen Nachfolger mehr
        // dann soll laut Aufgabenstellung die unveränderte Liste zurück geliefert werden
        else {
            return this;
        }
    }

    /**
     * fügt ein neues Element mit dem übergebenen Wert vor das aktuelle Element
     * ein
     *
     * @param value einzufgender Wert
     * @return veränderte Liste
     */
    public Element insertValueAtFront(int value) {
        // das Einfügen an erster Stelle, ist identisch zu dem Einfügen an der
        // Indexposition 0, daher kann die entsprechende Methode genutzt werden
        return this.insertValueAt(value, 0);
    }
}
