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
public class Figure{
    final double LIMIT = 0.1;
    protected Color color;
    
    /**
     * ermittelt die Fläche einer Figur
     * @return Defaultwert 0
     */
    protected double getArea(){
        return 0;
    }
    
    /**
     * berechnet den Umfang einer Figur
     * @return Defaultwert 0
     */
    protected double getPerimeter(){
        return 0;
    }
    
    /**
     * vergleicht die Größe der Flächen zweier Figuren
     * @param figure2
     * @return  -1 wenn die Fläche der übergebenen Figur größer ist,
     *          0 wenn die Abweichung der Flächen beider Figuren unter dem
     *            Grenzwert liegen
     *          1 wenn die Fläche der aktuellen Figur größer ist
     */
    protected int compare(Figure figure2){
        if (Math.abs(this.getArea() - figure2.getArea()) < LIMIT) {
            return 0;
        } else if (this.getArea() > figure2.getArea()){
            return 1;
        } else {
            return -1;
        }
    }
    
    /**
     * gibt die bekannten Eigenschaften der Figur als String zurück
     * @return Eigenschaften Farbe, Fläche und Umfang als String
     * @todo Wird NUR hier implementiert.
     */
    public String toString(){
        String colourStr = (color != null) ? this.color.toString() : "farblos";
        
        return (String.format("%11s Figur    mit "
                + "                                       %s", 
                colourStr, getDescription()));
    }
    
    protected String getDescription(){
        return (String.format("Umfang: %6.2f, Fläche:%7.2f", 
                this.getPerimeter(), this.getArea()));
    }
}
