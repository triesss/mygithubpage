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
public class Triangle extends Figure{
    private double a, b , c;
    
    /**
     * Konstruktor
     * @param color
     * @param a
     * @param b
     * @param c 
     */
    public Triangle(Color color, double a, double b, double c){
        super.color = color;
        this.a = a;
        this.b = b;
        this.c = c;        
    }
    
    /**
     * liefert Fläche
     * @return 
     */
    @Override
    public double getArea(){
        double s = (this.a + this.b + this.c )/2;
        return Math.sqrt( (s*(s-this.a)*(s-this.b)*(s-this.c))  );
    }
    
    /**
     * liefert Umfang
     * @return 
     */
    @Override
    public double getPerimeter(){
        return this.a + this.b + this.c;
    }
    
    public String toString(){
        return (String.format("%11s Rechteck mit "
                + "Kantenlängen ( %5.2f,  %5.2f,  %5.2f), %s", 
                (this.color != null) ? this.color.toString() : "farblos", 
                this.a, this.b, this.c, getDescription()));
    }    
}
