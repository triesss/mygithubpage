/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grp13_ueb05;

/**
 *
 * @author Paul
 */
public class ElementList {

    /**
     * Methode gibt an, dass die Liste leer ist, sofern sie nicht in einer
     * erbenden Klasse überschrieben wird
     * @return true
     */
    public boolean isEmpty(){
        return true;
    }
    
    /**
     * Methode gibt die Länge der Liste an
     * @return Länge der Liste
     */
    public int lengthOfList(){
        return 0;
    }

    /**
     * Methode zum Einfügen einer neuen Figur in eine leere Liste
     * @param value einzufügende Figur
     * @return neue Liste
     */
    public ElementList append(Figure value){
        return new Element(value, this);
    }

    /**
     * Methode gibt einen leeren String zurück, sofern sie nicht in einer
     * erbenden Klasse überschrieben wird
     * @param value Zahlenwert für Zeilennummerierung
     * @return leerer String
     */    
    public String toString(int i){
        return "";
    }
}

// Methoden zur neuen Listenstruktur aus dem Skript, die für diese Aufgabe
// nicht benötigt werden
    
//    public ElementList find(Figure value){
//        return this;
//    }
    
//    public ElementList insertAt(Figure value, int pos){
//        if (pos == 0){
//            return new Element(value, this);
//        } else {
//            System.err.println("Fehler: Falsche Einfügeposition");
//            return this;
//        }
//    }
    
//    public ElementList remove(Figure value){
//        return this;
//    }
    
//    public void printList(){
//        System.out.println("Ende");
//    }
