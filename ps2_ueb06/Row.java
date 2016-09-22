package grp13_ueb06;

/**
 * eine Parkreihe wird abgebildet
 * Initial besteht eine Reihe aus einer einzigen riesigen Lücke.
 * @author Gerit
 * 
 * @todo row = new Row(30, new int[][]{{24, 6}});
         row.freePlaceAt(22, 8);
 */
public class Row {

    int length;
    GapList gapList;

    /**
     * Konstruktor enthält Länge, damit gleich eine Lücke über diese Länge
     * angelegt werden kann
     * @param length Länge der Reihe
     */
    public Row(int length) {
        this.gapList = new GapList();
        this.length = length;
        gapList = gapList.insert(new Gap(0, length));
    }
    
    /**
     * Konstruktor zum Testen bekommt alle Belegungen in Array übergeben
     * @param length   Länge der Reihe
     * @param carList zweidimensionales Array [alle Belegungen][Position und Länge der Belegung]
     */
    public Row(int length, int[][] carList) {
        this(length);
        for (int[] singleCar : carList) {
            this.occupyPlace(singleCar[0], singleCar[1]);
        }
    }
    
    /**
     * liefert die Länge der Reihe
     * @return die Länge der Reihe
     */
    public int getLength() {
        return this.length;
    }

    /**
     * prüft, ob die Reihe aus einer einzigen Lücke besteht
     * @return true, wenn die Reihe aus einer einzigen Lücke besteht
     */
    public boolean isEmpty() {
        return gapList.hasJustOneElement() && gapList.getPayload().getStart() == 0;
    }

    /**
     * prüft, ob keine Lücken enthalten sind
     * @return true, wenn keine Lücke enthalten ist
     */
    public boolean isOptimallyFilled() {
        return gapList.isEmpty();
    }

    /**
     * prüft, ob eine freie Lücke für das Auto vorhanden ist
     * @param carLength Länge des Autos
     * @return true, wenn Lücke vorhanden
     */
    public boolean hasFreeGap(int carLength) {
        return gapList.findPositionFor(carLength) != -1;
    }

    /**
     * liefert die größte Lücke in der Reihe
     * @return die größte Lücke in der Reihe
     */
    public int getLargestGap() {
        return gapList.getLargestGap();
    }

    /**
     * liefert die Position der kleinsten pasenden Lücke in der Reihe
     * @param carlength Länge des unterzubringenden Autos
     * @return die Position der kleinsten passenden Lücke in der Reihe
     */
    public int findBestPosition(int carlength) {
         return gapList.findPositionFor(carlength);
    }

    /**
     * belegt Lücke
     * @param position  an der Position
     * @param carLength mit der Länge
     * @return true, wenn die Lücke noch frei war
     */
    public boolean occupyPlace(int position, int carLength) {        
        if( gapList.hasGapAt(position, carLength) ){
                this.gapList = this.gapList.remove(position, carLength);
                return true;
        }        
        return false;
    }

    /**
     * belegt Lücke für dieses Auto
     * @param carLength
     * @return Position des Autos, -1, wenn keine passende Lücke vorhanden
     */
    public int parkCar(int carLength) {
        int pos;
        if( gapList.findPositionFor(carLength) != -1 ){      
                pos = gapList.findPositionFor(carLength);
                this.gapList = this.gapList.remove(pos, carLength);
                return pos;
        }
        return -1;
    }

    /**
     * gibt den Platz an position wieder frei.
     * Die Angabe muss nicht exakt sein, sondern darf mit einer bestehenden
     * Lücke überlappen. Es wird dann nur der belegte Teil freigegeben.
     * @param position Startposition der Freigabe
     * @param length   Länge der Freigabe
     */
    public void freePlaceAt(int position, int length) {
        gapList = gapList.insert(position, length);
    }
    
    /**
     * überschreibt equals()
     * @param obj soll auch eine Row sein
     * @return true, wenn Reihen gleich sind
     */
    @Override
    public boolean equals(Object obj) {
        if( obj != null && obj instanceof Row){
            Row row = (Row) obj;
            if ( this.gapList.equals(row.gapList) && this.length == row.length ) return true;
        }
        return false;
    }
    
    /**
     * liefert eine Stringdarstellung der Lückenliste
     * @return eine Stringdarstellung der Lückenliste
     */
    @Override
    public String toString() {
        return "{" + gapList.toString() + "}";
    }

    // --- Tests ------------------------------------------------
     
    
    public static void main(String[] args) {
//        Row row = new Row(20);
//        //System.out.println(row.toString());
//        assert "  0.. 20".equals(row.toString());
//        assert 0 == row.findBestPosition(5);
//        assert row.occupyPlace(row.findBestPosition(5),5);
//        assert row.isEmpty();
//        assert 15 == row.getLargestGap();
//        assert !row.isOptimallyFilled();
//        assert row.occupyPlace(7, 3);
//        assert !row.isEmpty();
//        //System.out.println(row.toString());
//        assert "  5..  7,  10.. 20".equals(row.toString());
//        assert row.gapList.hasGapAt(5, 2);
//        assert row.gapList.hasGapAt(10, 10);
//        assert 10 == row.parkCar(6);
//        //System.out.println(row.toString());
//        assert "  5..  7,  16.. 20".equals(row.toString());
//        assert 4 == row.getLargestGap();
//        assert 5 == row.parkCar(2);
//        assert 16 == row.parkCar(4);
//        assert row.isOptimallyFilled();
//        //System.out.println(row.toString());
//        assert "".equals(row.toString());
//        int[][] carList = { {2,2}, {5,7}, {15,4}, {20,1} };
//        Row roow = new Row( 25,carList );
//        System.out.println(roow.toString());
        
        Row row;
        row = new Row(30, new int[][]{{24, 6}});
        row.freePlaceAt(22, 8);
        assert row.isEmpty();
    }

}
