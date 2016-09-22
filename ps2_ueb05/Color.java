/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grp13_ueb05;
import java.util.Arrays;
/**
 *
 * @author Alexander
 */
public class Color {

    private int[] rgb = null;

    public enum Name {

        RED, ORANGE, YELLOW, BLUEVIOLET, BLUE, GREEN, GREY, BLACK, WHITE
    };
    private static final int[][] code = new int[][]{
        /*R    G    B*/
        {0xFF, 0,       0}, /*RED*/
        {0xFF, 0x45,    0}, /*ORANGE*/
        {0xFF, 0xFF,    0}, /*YELLOW*/
        {0x8A, 0x2B, 0xE2}, /*BLUEVIOLET*/
        {0,    0,    0xFF}, /*BLUE*/
        {0,    0xFF,    0}, /*GREEN*/
        {0xBE, 0xBE, 0xBE}, /*GREY*/
        {0,    0,       0}, /*BLACK*/
        {0xFF, 0xFF, 0xFF}  /*WHITE*/};

    //Konstruktoren
    /**
     * Konstruktor int, int ,int 
     * @param r
     * @param g
     * @param b 
     */
    public Color(int r, int g, int b) {
        this(new int[]{r, g, b});
    }
    public Color() {this.rgb = null;}
    /**
     * Konstruktor int Array
     * @param rgb 
     */
    public Color(int[] rgb) {
        if( inRange(rgb) )this.rgb = rgb;
    }
    
    /**
     * Konstruktor enum
     * @param rgb 
     */
    public Color(Name rgb) {
        this( code[rgb.ordinal()] );
    }
    
    /**
     * Konstruktor String - "falsche" Eingabe bedeutet keine Farbe 
     * @param color 
     * @todo Das hier ist kein Konstruktor!
     */
    public static Color colorName(String color) {
        int[] rgbd = null;
        if(isColorName(color)){
            rgbd = code[Name.valueOf(color.toUpperCase()).ordinal()];
            return new Color(rgbd);
        }
        else return null;
    }
    
    
    //Methoden:
    /**
     * liefert true, wenn der Parameter einem definierten 
     * Farbbezeichner entspricht
     * @param colorName
     * @return 
     */
    public static boolean isColorName(String colorName) {
        for (Name c : Name.values()) {
            if (c.name().equals(colorName.toUpperCase())) {
                return true;
            }
        }
        return false;
    }
    /**
     * liefert die drei Farbwerte in einem Array zurück
     * @return 
     */
    public int[] getRGB() {
        return this.rgb;
    }
    
    /**
     * liefert den Wert des gewünschten Farbkanals
     * @param channel
     * @return 
     */
    public int getRGB(int channel) {
        return this.rgb[channel];
    }
    
    /**
     * liefert die drei Farbwerte in einem integer
     * ((rot=10, grün=128, blau=255) -> hexA80FF = dez688383)
     * @return 
     * @TODO: Ohne Strings, dafür mit Bitgefummel (das ist dann ein Einzeiler mit zwei Additionen und zwei Shift-Operationen)
     */
    public int getPackedRGB() {
        return (this.rgb[0] << 16) | (this.rgb[1] << 8) | (this.rgb[2]);
    }
    
    /**
     * liefert eine Stringdarstellung der Farbe: die Bezeichnung,
     * falls eine für die Farbwerte vorhanden ist,
     * sonst die Werte der 3 Farbkanäle in Hex-Darstellung.
     * @return 
     * @TODO: So ist das unperformant, Hex String nur bauen wenn er benötigt wird
     */
    public String toString(){
        String hexCode = "#";
        assert (rgb != null);
        
        for(int i = 0 ; i < code.length ; i++){
            if(Arrays.equals(code[i], rgb)){
                hexCode = Name.values()[i].toString();
                return hexCode.toUpperCase();
            }
        }
        for(int i = 0; i < 3 ;i++)hexCode += Integer.toHexString(this.rgb[i]);
        //System.out.println(hexCode.toUpperCase());
        return hexCode.toUpperCase();
    }    
    
    private static boolean inRange(int[] rgb){
        if(rgb == null) return true;
        for(int i = 0 ; i < 3 ; i++  ){
            if(rgb[i] < 0 || rgb[i] > 255 ) return false;
        }
        return true;
    }
    

    
    
    public static void main(String[] args ){
    //Konstruktor für einzelne Farbwerte ergibt definierte Farbe
    Color color = new Color(0xFF, 0, 0);
    assert Arrays.equals(new int[]{0xFF, 0, 0}, color.getRGB());
    assert "RED".equals(color.toString());
     
    //Konstruktor für einzelne Farbwerte ergibt undefinierte Farbe
    color = new Color(10, 128, 255);
    assert Arrays.equals(new int[]{10, 128, 255}, color.getRGB());
    assert "# A80FF".equals(color.toString());
    
    //Konstruktor für Farbnamen
    color = color.colorName("blue");
    assert Arrays.equals(new int[]{0,0,0xFF}, color.getRGB());
    assert "BLUE".equals(color.toString());
    
    //Konstruktor für enum Namen
    color = new Color(Name.GREEN);
    assert Arrays.equals(new int[]{0,0xFF,0}, color.getRGB());
    assert "GREEN".equals(color.toString());
    
    //Konstruktor für Array
    int[] array = new int[]{5, 81, 0xFF};
    color = new Color(array);
    assert Arrays.equals(new int[]{5, 81, 0xFF}, color.getRGB());
    assert "# 551FF".equals(color.toString());
    }
}
