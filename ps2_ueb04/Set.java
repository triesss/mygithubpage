/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grp13_ueb04;

/**
 *
 * @author Paul
 */
public class Set {

    private Element item;

    /**
     * prüft, ob Elemente enthalten sind
     *
     * @return true, wenn es leer ist und false wenn Elemente enthalten sind
     */
    public boolean isEmpty() {
        return (item == null);
    }

    /**
     * fügt ein neues Element mit übergebenem Wert zu, wenn der Wert noch nicht
     * existiert
     *
     * @param value anzufügender Wert
     */
    public void addElement(int value) {
        if (this.isEmpty()) {
            item = new Element();
            item.setValue(value);
        } else if (!item.containsValue(value)) {
            item = item.insertElementSorted(value);
        }
    }

    /**
     * prüft, ob der Wert schon in der Elementliste existiert
     *
     * @param value zu prüfender Wert
     * @return true, wenn bereits vorhanden, ansonsten false
     */
    public boolean existsElement(int value) {
        return this.isEmpty()
                ? false
                : item.containsValue(value);
    }

    /**
     * löscht ein Element diesen Wertes; falls es nicht vorhanden ist, passiert
     * nichts
     *
     * @param value zu löschender Wert
     */
    public void deleteElement(int value) {
        // man könnte zuerst prüfen, ob der zu löschende Wert überhaupt
        // enthalten ist, dies würde jedoch keine Laufzeit sparen, da die Liste
        // in beiden fällen einmal komplett durchlaufen wird. Dass nichts
        // passiert, wenn der Wert nicht enthalten ist, ist in
        // Element.deleteElement() sichergestellt.
        if (!this.isEmpty()) {
            item = item.deleteElement(value);
        }
    }

    /**
     * liefert eine Stringdarstellung des Mengeninhalts mit umschließenden
     * geschweiften Klammern
     *
     * @return String des Mengeninhaltes
     */
    public String showValues() {
        if (this.isEmpty()) {
            return "{}";
        } else {
            return "{" + item.showValues() + "}";
        }
    }

    /**
     * liefert eine neue Menge, die die Vereinigung der aktuellen mit der
     * übergebenen abbildet
     *
     * @param other übergebene Menge die mit aktueller Menge vereinigt werden
     * soll
     * @return Vereinigungsmenge
     */
    public Set union(Set other) {
        Set unifiedSet = new Set();

//        // wenn die aktuelle Menge leer ist, besteht die Vereinigungsmenge nur
//        // aus der übergebenen Menge
//        if(this.isEmpty() && other.isEmpty()){
//            return unifiedSet;
//        }
//        else if (this.isEmpty()) {
//            for (int i = 0; i < other.item.size(); i++) {
//                unifiedSet.addElement(other.item.getValueAt(i));
//            }
//        } // wenn die aktuelle Menge leer ist, besteht die Vereinigungsmenge nur
//        // aus der übergebenen Menge
//        else if (other.isEmpty()) {
//            for (int i = 0; i < this.item.size(); i++) {
//                unifiedSet.addElement(this.item.getValueAt(i));
//            }
//        } // wenn beide Mengen gefüllt sind, müssen die Elemente nacheinander
//        // angefügt werden. Dass es nicht zu doppelten Einträgen kommt, 
//        // berücksichtigt die Methode addElement(int value).
//        else {
//
//            for (int i = 0; i < this.item.size(); i++) {
//                unifiedSet.addElement(this.item.getValueAt(i));
//            }
//            for (int i = 0; i < other.item.size(); i++) {
//                unifiedSet.addElement(other.item.getValueAt(i));
//            }
//        }
        if (!isEmpty()) {
            unifiedSet.addElementList(item);
        }
        if (!other.isEmpty()) {
            unifiedSet.addElementList(other.item);
        }
        return unifiedSet;
    }

    /**
     * liefert eine neue Menge, die die Schnittmenge der aktuellen mit der
     * übergebenen abbildet
     *
     * @param other
     * @return Referenz auf den neuen Set-Container intersecedSet
     */
    public Set intersectuion(Set other) {
        Set intersecedSet = new Set();
        

        if (!this.isEmpty() && !other.isEmpty()) {
            int size = other.item.size();
            for (int i = 0; i < size; i++) {
                if (this.item.containsValue(other.item.getValueAt(i))) {
                    intersecedSet.addElement(other.item.getValueAt(i));
                }
            }
        }
        return intersecedSet;
    }

    /**
     * liefert eine neue Menge, die die Differenzmenge der aktuellen mit der
     * übergebenen abbildet
     *
     * @param other
     * @return Referenz auf dern neuen Set-Container diffSet
     */
    public Set diff(Set other) {
        Set diffSet = new Set();
        
        
        if (!this.isEmpty()) {
            if (other.isEmpty()) {
                diffSet.item = diffSet.addElementList(this.item);
            } else {
                int size = this.item.size();
                for (int i = 0; i < size; i++) {
                    if (!other.existsElement(this.item.getValueAt(i))) {
                        diffSet.addElement(this.item.getValueAt(i));
                    }
                }
            }
        }
        return diffSet;
    }

    /**
     * prüft, ob die übergebene Menge die gleichen Werte enthält wie die
     * aktuelle
     *
     * @param other
     * @return true:beide Sets sind leer ODER alle Elemente aus other sind in
     * this; false: eine der Sets ist leer ODER ein Element aus other ist nicht
     * in this;
     */
    private boolean isSame(Set other) {
        
        // wenn beide Mengen leer sind, sind sie identisch
        if (this.isEmpty() && other.isEmpty()) {
            return true;
        } 
        // wenn nur eine der beiden Mengen leer ist, sind sie nicht identisch
        else if (this.isEmpty() || other.isEmpty()) {
            return false;
        } 
        // in allen anderen Fällen müssen die Werte verglichen werden
        else {
         // todo kein return in for
         // todo other darf nicht leer sein
         // todo element.isSame  nutzen
            return this.item.isSame(other.item);
            
            // for (int i = 0; i < size; i++) {
            //     this.item.isSame(other.item);
            //     if (!this.item.isSame()existsElement(other.item.getValueAt(i))) {
            // 
            //     }
            //     if (!this.existsElement(other.item.getValueAt(i))) {
            //         same = false;
            //     }
            // }
        }
    }

    /**
     * prüft, ob die übergebene Menge eine echte Teilmenge der aktuellen ist.
     *
     * @param other
     * @return true, wenn Set B echte Teilmenge von Set A ist, false, wenn nicht
     */
    public boolean isProperSubSet(Set other) {
        //todo das ist nicht das gleiche
        if (other.isEmpty()) {
            return false;
        } else if (this.isEmpty() && !other.isEmpty()) {
            return true;
        } else {
            return this.diff(other).isEmpty() && !this.isSame(other);
        }
    }

    /**
     * fügt alle noch nicht enthaltenen Werte einer übergebenen Liste vom Typ
     * Element als Elemente zu
     *
     * @param list
     * @return Referenz auf das aktuelle, erweiterte Element item
     */
    private Element addElementList(Element list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            addElement(list.getValueAt(i));
        }
        return this.item;
    }
}
