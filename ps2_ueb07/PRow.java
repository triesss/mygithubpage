package grp13_ueb07;

/**
 * eine Parkreihe wird abgebildet
 * Initial besteht eine Reihe aus einer einzigen riesigen Lücke.
 * @author Gerit
 * 
 *
 */
public abstract class PRow {

    protected int length;
    
    /**
     * liefert die Länge der Reihe
     * @return die Länge der Reihe
     */
    public int getLength() {
        return this.length;
    }

    //DEFINITIONEN abstrakter Methoden
    public abstract boolean isEmpty();
    public abstract boolean isOptimallyFilled();
    public abstract boolean hasFreeGap(int carLength);
    public abstract int getLargestGap();
    public abstract int findBestPosition(int carlength);
    public abstract boolean occupyPlace(int position, int carLength);
    public abstract int parkCar(int carLength);
    public abstract void freePlaceAt(int position, int length);
    
    public  boolean equals(Object obj) {
        return (obj instanceof PRow) && this.getLength() == ((PRow) obj).getLength();
    }
    public abstract String toString();

    
    public static void main(String[] args) {

    }

}
