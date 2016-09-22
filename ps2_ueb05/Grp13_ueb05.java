
package grp13_ueb05;






public class Grp13_ueb05 {

    
    private static ElementList buildList( ElementList list ){
        //Color colorSTR = new Color();
        System.out.println("Kontrollausgabe:");
        
        // Aufbau der Liste aus der hervorgeht, dass Einfügepositionen am Anfang
        // in der Mitte und am Ende liegen können
        Figure figure = new Square(Color.colorName("YELLOW"),2.5);
        System.out.printf(" 1.%s%n", figure.toString());
        list = list.append(figure);
        // am Anfang anfügen
        figure = new Rectangle(new Color(0x12,0x34,0x56),7,8);
        System.out.printf(" 2.%s%n", figure.toString());
        list = list.append(figure);        
        // mit gleicher Fläche dahinter einfügen
        figure = new Rectangle(new Color(0x0,0x0,0xFF),7,8);
        System.out.printf(" 3.%s%n", figure.toString());
        list = list.append(figure);  
        // am Ende anfügen
        figure = new Rectangle(Color.colorName("ORANGE"),2,1);
        System.out.printf(" 4.%s%n", figure.toString());
        list = list.append(figure);         
        // in der Mitte einfügen
        figure = new Square(new Color(0,0,0),3.5);
        System.out.printf(" 5.%s%n", figure.toString());
        list = list.append(figure); 
        
        
        //weitere Figuren testen
        figure = new Circle(new Color(0xBE,0xBE,0xBE),3.5);       
        System.out.printf(" 6.%s%n", figure.toString());
        list = list.append(figure);
        figure = new Triangle(new Color(Color.Name.BLUEVIOLET), 5, 8, 9.4);
        System.out.printf(" 7.%s%n", figure.toString());
        list = list.append(figure);
        figure = new Circle(Color.colorName("farblos"), 6.75);
        System.out.printf(" 8.%s%n", figure.toString());
        list = list.append(figure);
        figure = new Triangle(Color.colorName("farblos"), 7, 8, 4);
        System.out.printf(" 9.%s%n", figure.toString());
        list = list.append(figure);
        figure = new Circle(new Color(Color.Name.GREEN), 3);
        System.out.printf("10.%s%n", figure.toString());
        list = list.append(figure);
        figure = new Rectangle(Color.colorName("red"), 7, 8);
        System.out.printf("11.%s%n", figure.toString());
        list = list.append(figure);
        figure = new Square(new Color(0xFF,0xFF,0x0), 2.5);
        System.out.printf("12.%s%n", figure.toString());
        list = list.append(figure);
        
        return list;
    }
    
    private static void printList(ElementList list){
        System.out.println(list.toString(1));
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ElementList list = new ElementList();
        list = buildList(list);
        
        System.out.println("------------------------------");
        System.out.println("Ausgabe in sortierter Reihenfolge:");
        printList(list);
    }
    
}
