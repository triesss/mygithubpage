package grp13_ueb07;

/**
 * eine Parkreihe wird abgebildet
 * Initial besteht eine Reihe aus einer einzigen riesigen Lücke.
 * @author Gerit
 * 
 * @todo row = new Row(30, new int[][]{{24, 6}});
         row.freePlaceAt(22, 8);
 */
public abstract class Row {

    int length;

    /**
     * Konstruktor enthält Länge, damit gleich eine Lücke über diese Länge
     * angelegt werden kann
     * @param length Länge der Reihe
     */
//    public Row(int length) {
//        this.gapList = new GapListEmptyElement();
//        this.length = length;
//        gapList = gapList.insert(new Gap(0, length));
//    }
    
    /**
     * Konstruktor zum Testen bekommt alle Belegungen in Array übergeben
     * @param length   Länge der Reihe
     * @param carList zweidimensionales Array [alle Belegungen][Position und Länge der Belegung]
     */
//    public Row(int length, int[][] carList) {
//        this(length);
//        for (int[] singleCar : carList) {
//            this.occupyPlace(singleCar[0], singleCar[1]);
//        }
//    }
    
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
    public abstract boolean isEmpty();

    /**
     * prüft, ob keine Lücken enthalten sind
     * @return true, wenn keine Lücke enthalten ist
     */
    public abstract boolean isOptimallyFilled();

    /**
     * prüft, ob eine freie Lücke für das Auto vorhanden ist
     * @param carLength Länge des Autos
     * @return true, wenn Lücke vorhanden
     */
    public abstract boolean hasFreeGap(int carLength);

    /**
     * liefert die größte Lücke in der Reihe
     * @return die größte Lücke in der Reihe
     */
    public abstract int getLargestGap();

    /**
     * liefert die Position der kleinsten pasenden Lücke in der Reihe
     * @param carLength Länge des unterzubringenden Autos
     * @return die Position der kleinsten passenden Lücke in der Reihe
     */
    public abstract int findBestPosition(int carLength);

    /**
     * belegt Lücke
     * @param position  an der Position
     * @param carLength mit der Länge
     * @return true, wenn die Lücke noch frei war
     */
    public abstract boolean occupyPlace(int position, int carLength);

    /**
     * belegt Lücke für dieses Auto
     * @param carLength
     * @return Position des Autos, -1, wenn keine passende Lücke vorhanden
     */
    public abstract int parkCar(int carLength);

    /**
     * gibt den Platz an position wieder frei.
     * Die Angabe muss nicht exakt sein, sondern darf mit einer bestehenden
     * Lücke überlappen. Es wird dann nur der belegte Teil freigegeben.
     * @param position Startposition der Freigabe
     * @param length   Länge der Freigabe
     */
    public abstract void freePlaceAt(int position, int length);
    
    /**
     * überschreibt equals()
     * @param obj soll auch eine Row sein
     * @return true, wenn Reihen gleich sind
     */
    
    @Override
    public abstract boolean equals(Object obj);
    
    /**
     * liefert eine Stringdarstellung der Lückenliste
     * @return eine Stringdarstellung der Lückenliste
     */
    @Override
    public abstract String toString();

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
        
//        Row row;
//        row = new Row(30, new int[][]{{24, 6}});
//        row.freePlaceAt(22, 8);
//        assert row.isEmpty();
    }

}
