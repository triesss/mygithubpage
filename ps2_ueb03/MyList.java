/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grp13_ueb03;

/**
 *
 * @author Alexander
 */
public class MyList {

    //private:
    private Element elements;

    //public:
    public boolean isEmpty() {
        return elements == null;
    }

    public void appendElement(int value) {
        if (this.isEmpty()) {
            elements = new Element();
            elements.setValue(value);
        } else {
            elements = elements.appendElement(value);
        }
    }

    public void insertElementSorted(int value) {
        if (this.isEmpty()) {
            appendElement(value);
        } else {
            elements = elements.insertElementSorted(value);
        }
    }

    public void deleteElement(int value) {
        if (!isEmpty()) {
            elements = elements.deleteElement(value);
        }
    }

    /**
     * liefert die Anzahl der Listenelemente
     *
     * @return groesse der Liste
     */
    public int size() {
        if (this.isEmpty()) {
            return 0;
        } else {
            return elements.size();
        }
    }

    /**
     * liefert die Summe aller in der Liste enthaltenen Werte
     *
     * @return summe aller elemente
     */
    public int sum() {
        if (this.isEmpty()) {
            return 0;
        } else {
            return elements.sum();
        }
    }

    /**
     * liefert true, wenn die Liste aufsteigend sortiert ist
     *
     * @return isSorted()
     */
    public boolean isSorted() {
        if (this.isEmpty()) {
            return true;
        } else {
            return elements.isSorted();
        }
    }

    /**
     * liefert true, wenn bereits ein Element mit diesem Wert in der Liste
     * enthalten ist.
     *
     * @param value
     * @return bei leerer liste per default true
     */
    public boolean containsValue(int value) {
        if (this.isEmpty()) {
            return false;
        } else {
            return elements.containsValue(value);
        }
    }

    /**
     * liefert einen String, der umschlossen von geschweiften Klammern die in
     * der Liste enthaltenen Werte
     *
     * @return
     */
    public String showValues() {
        if (this.isEmpty()) {
            return "{}";
        } else {
            return "{" + elements.showValues() + "}";
        }
    }

    /**
     * liefert den Wert an der Stelle index. Ist der Index ungültig, so wird
     * Integer.MAX_VALUE zurückgegeben
     *
     * @param index
     * @return Wert an Stelle Index, Integer.MAX_VALUE, wenn leer
     */
    public int getValueAt(int index) {
        if (this.isEmpty()) {
            return Integer.MAX_VALUE;
        } else {
            return elements.getValueAt(index);
        }
    }

    /**
     * fügt den Wert value an der Stelle index ein. Ist der Index ungültig,
     * bleibt die Liste unverändert
     *
     * @param value
     * @param index
     */
    public void insertValueAt(int value, int index) {
        //todo leere Liste
        if (this.isEmpty()) {
            elements = new Element();
            elements.setValue(value);
        }
        else if (index == elements.size()) {
            elements.appendElement(value);
        } else {
            elements.insertValueAt(value, index);
        }
    }

    /**
     * fügt den Wert value an den Anfang der Liste
     *
     * @param value
     */
    public void insertValueAtFront(int value) {
        if (this.isEmpty()) {
            elements = new Element();
            elements.setValue(value);
        } else {
            elements.insertValueAtFront(value);
        }
    }

    /**
     * fügt ein Element mit dem Wert value vor dem ersten Element mit einem
     * größeren Wert ein, wenn noch kein Element mit diesem Wert vorhanden ist.
     * Sonst bleibt die Liste unverändert.
     *
     * @param value
     */
    public void insertSortedIfUnique(int value) {
        if (this.isEmpty()) {
            elements = new Element();
            elements.setValue(value);
        } else 
//            if (value < elements.getValueAt(0)) {
//            elements.insertValueAtFront(value);
//        } else 
            //todo sollte auch so funktionieren
                if (!elements.containsValue(value)) {
                    elements = elements.insertElementSorted(value);
        }
    }
}
