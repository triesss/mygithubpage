/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grp13_ueb05;

/**
 *
 * @author Alexander
 */
public class Circle extends Figure{
    private double diameter;
    
    /**
     * Konstruktor
     * @param color
     * @param diameter 
     */
    public Circle(Color color, double diameter){
        super.color = color;
        this.diameter = diameter;
    }
    
    /**
     * liefert Fläche
     * @return 
     */
    @Override
    public double getArea(){
        return Math.PI * (diameter/2) * (diameter/2);
    }
    
    /**
     * liefert Umfang
     * @return 
     */
    @Override
    public double getPerimeter(){
        return Math.PI * this.diameter;
    }    
    
    /**
     * Ausgabe für Kreis
     * @return 
     */
    @Override
    public String toString(){
        return (String.format("%11s Kreis    mit "
                + "Durchmesser  ( %5.2f)                  %s", 
                (this.color != null) ? this.color.toString() : "farblos", 
                this.diameter,  getDescription()));        
    }
}
