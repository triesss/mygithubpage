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
public class Rectangle extends Figure{
    private double width;
    private double depth;
    
    /**
     * Konstruktor setzt Farbe und Seitenlängen des Rechteckes
     * @param color Farbe
     * @param width Breite des Rechteckes
     * @param depth Tiefe des Rechteckes
     */
    protected Rectangle(Color color, double width, double depth){
        super.color = color;
        this.width = width;
        this.depth = depth;
    }
    
    /**
     * überschriebene Methode getArea liefert die Fläche des Rechteckes
     * @return Fläche des Rechteckes
     */
    protected double getArea(){
        return (this.width * this.depth);
    }
    
    /**
     * überschriebene Methode getPerimeter liefert den Umfang des Rechteckes
     * @return Umfang des Rechteckes
     */
    protected double getPerimeter(){
        return ((this.width + this.depth) * 2);
    }

    /**
     * überschriebene Methode toString gibt die bekannten Eigenschaften des
     * Rechteckes als String zurück
     * @return Eigenschaften Farbe, Seitenlängen, Fläche und Umfang als String
     */
    public String toString(){
        return (String.format("%11s Rechteck mit "
                + "Kantenlängen ( %5.2f,  %5.2f),         %s", 
                (this.color != null) ? this.color.toString() : "farblos", 
                this.width, this.depth, getDescription()));
    }
}

