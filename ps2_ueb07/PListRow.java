package grp13_ueb07;

import java.util.Objects;

/**
 * eine Parkreihe wird abgebildet
 * Initial besteht eine Reihe aus einer einzigen riesigen Lücke.
 * @author Gerit
 * 
 * 
 */
public class PListRow extends PRow {

    private GapList gapList;

    /**
     * Konstruktor enthält Länge, damit gleich eine Lücke über diese Länge
     * angelegt werden kann
     * @param length Länge der Reihe
     */
    public PListRow(int length) {
        this.gapList = new GapListEmptyElement();
        this.length = length;
        gapList = gapList.insert(new Gap(0, length));
    }
    
    /**
     * Konstruktor zum Testen bekommt alle Belegungen in Array übergeben
     * @param length   Länge der Reihe
     * @param carList zweidimensionales Array [alle Belegungen][Position und Länge der Belegung]
     */
    public PListRow(int length, int[][] carList) {
        this(length);
        for (int[] singleCar : carList) {
            this.occupyPlace(singleCar[0], singleCar[1]);
        }
    }
    
    /**
     * liefert die Länge der Reihe
     * @return die Länge der Reihe
     */
    @Override
    public int getLength() {
        return this.length;
    }

    /**
     * prüft, ob die Reihe aus einer einzigen Lücke besteht
     * @return true, wenn die Reihe aus einer einzigen Lücke besteht
     */
    @Override
    public boolean isEmpty() {
        return gapList.hasJustOneElement() && gapList.getPayload().getStart() == 0;
    }

    /**
     * prüft, ob keine Lücken enthalten sind
     * @return true, wenn keine Lücke enthalten ist
     */
    @Override
    public boolean isOptimallyFilled() {
        return gapList.isEmpty();
    }

    /**
     * prüft, ob eine freie Lücke für das Auto vorhanden ist
     * @param carLength Länge des Autos
     * @return true, wenn Lücke vorhanden
     */
    @Override
    public boolean hasFreeGap(int carLength) {
        return gapList.findPositionFor(carLength) != -1;
    }

    /**
     * liefert die größte Lücke in der Reihe
     * @return die größte Lücke in der Reihe
     */
    @Override
    public int getLargestGap() {
        return gapList.getLargestGap();
    }

    /**
     * liefert die Position der kleinsten pasenden Lücke in der Reihe
     * @param carlength Länge des unterzubringenden Autos
     * @return die Position der kleinsten passenden Lücke in der Reihe
     */
    @Override
    public int findBestPosition(int carlength) {
         return gapList.findPositionFor(carlength);
    }

    /**
     * belegt Lücke
     * @param position  an der Position
     * @param carLength mit der Länge
     * @return true, wenn die Lücke noch frei war
     */
    @Override
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
    @Override
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
    @Override
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
        if( obj != null && obj instanceof PListRow){
            PListRow row = (PListRow) obj;
            if ( this.gapList.equals(row.gapList) && this.length == row.length ) return true;
        }
        return false;
    }

    // automatisch erzeugt
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.gapList);
        return hash;
    }
    
    /**
     * liefert eine Stringdarstellung der Lückenliste
     * @return eine Stringdarstellung der Lückenliste
     */
    @Override
    public String toString() {
//        if ("".equals(gapList.toString())) return gapList.toString();
//        else 
            return "{" + gapList.toString() + "}";
    }

    // --- Tests ------------------------------------------------
     
    
    public static void main(String[] args) {
        PRow row = new PListRow(20);
        //System.out.println(row.toString());
        assert "{  0.. 20}".equals(row.toString());
        assert 0 == row.findBestPosition(5);
        assert row.occupyPlace(row.findBestPosition(5),5);
//        assert row.isEmpty();
        assert 15 == row.getLargestGap();
        assert !row.isOptimallyFilled();
        assert row.occupyPlace(7, 3);
        assert !row.isEmpty();
        //System.out.println(row.toString());
        assert "{  5..  7,  10.. 20}".equals(row.toString());
        assert 10 == row.parkCar(6);
        //System.out.println(row.toString());
        assert "{  5..  7,  16.. 20}".equals(row.toString());
        assert 4 == row.getLargestGap();
        assert 5 == row.parkCar(2);
        assert 16 == row.parkCar(4);
        assert row.isOptimallyFilled();
        //System.out.println(row.toString());
        assert "".equals(row.toString());
        int[][] carList = { {2,2}, {5,7}, {15,4}, {20,1} };
        PRow roow = new PListRow( 25,carList );
        //System.out.println(roow.toString());
        
        PArrayRow anotherRow;
        anotherRow = new PArrayRow(30, new int[][]{{24, 6}});
        //System.out.println(anotherRow.toString());
        anotherRow.freePlaceAt(22, 8);
        //System.out.println(anotherRow.toString());
        assert anotherRow.isEmpty();
    }

}
