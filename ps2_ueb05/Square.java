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
public class Square extends Figure{
    private double width;
    
    /**
     * Konstruktor setzt Farbe und Seitenlänge des Quadrates
     * @param color Farbe
     * @param width Seitenlänge
     */
    protected Square(Color color, double width){
        super.color = color;
        this.width = width;
    }
    
    /**
     * überschriebene Methode getArea liefert die Fläche des Quadrates
     * @return Fläche des Quadrates
     */
    protected double getArea(){
        return (this.width * this.width);
    }
    
    /**
     * überschriebene Methode getPerimeter liefert den Umfang des Quadrates
     * @return Umfang des Quadrates
     */
    protected double getPerimeter(){
        return (this.width * 4);
    }
    
    /**
     * überschriebene Methode toString gibt die bekannten Eigenschaften des
     * Quadrates als String zurück
     * @return Eigenschaften Farbe, Seitenlänge, Fläche und Umfang als String
     */
    public String toString(){
        return (String.format("%11s Quadrat  mit "
                + "Kantenlänge  ( %5.2f),                 %s", 
                (this.color != null) ? this.color.toString() : "farblos", 
                this.width, getDescription()));
    }
}
