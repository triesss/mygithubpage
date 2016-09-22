package grp13_ueb02;

/**
 * gegebene Daten:
 * - gemessene Geschwindigkeitswerte
 * - erlaubte Höchstgeschwindigkeit am Messpunkt
 * - Messung innerorts oder außerorts
 * - maximal messbare Geschwindigkeit
 * 
 
 public Routinen:
 getAllowedSpeed()                          - liefert die am Messpunkt erlaubte Höchstgeschwindigkeit
 isInTown()                                 - true, wenn Messung innerorts durchgeführt wurde
 getMaxSpeed()                              - liefert die höchste messbare Geschwindigkeit

 countOccurencesInRange(minRange, maxRange) - liefert die Anzahl der Messwerte in einem bestimmten 
                                              Bereich (Grenzen inklusive)
 getValuesInRange(minRange, maxRange)       - liefert ein Array mit den Messwerten innerhalb 
                                              der Grenzen (inklusive)
 getAverage()                               - liefert den arithmetische Mittelwert aus den Messwerten 
 getMedian()                                - liefert den Median aus den Messwerten
 *
 * @author Gerit
 */
public class MeasuredValues {

    /** 
     * gemessene Geschwindigkeiten
     */
    private static final int[] MEASURED_VALUES = {
        45, 58, 57, 61, 63, 65, 62, 48, 49, 48, 75, 183, 55, 72, 77, 71, 70,
        60, 160, 165, 62, 63, 45, 46, 47, 66
    };
    
    /**
     * für diese Messreihe erlaubte Geschwindigkeit, innerorts
     */
    private static final int     ALLOWED_SPEED = 50; 
    private static final boolean IN_TOWN       = false;
    
    /**
     * für alle Messungen maximal messbare Geschwindigkeit
     */
    private static final int MAX_SPEED     = 999;
    
    //--------------------------------------------------------------------------
    // --- public ---
    
    /**
     * liefert die in dieser Messreihe erlaubte Geschwindigkeit
     * @return die in dieser Messreihe erlaubte Geschwindigkeit
     */
    public static int getAllowedSpeed(){
	return ALLOWED_SPEED;
    }

    /**
     * liefert true, wenn die Messung innerorts war
     * @return true, wenn die Messung innerorts war, sonst false
     */
    public static boolean isInTown() {
 	return IN_TOWN;
   }
    
    /**
     * liefert die maximal messbare Geschwindigkeit
     * @return die maximal messbare Geschwindigkeit
     */
    public static int getMaxSpeed(){
	return MAX_SPEED; 
    }
    
    /**
     * zählt, wieviele Elemente des Arrays innerhalb der Grenzen liegen
     * 
     * @param minRange Min-Grenze inklusive
     * @param maxRange Max-Grenze inklusive
     * @return Anzahl der passenden Elemente
     */
    public static int countOccurencesInRange(int minRange, int maxRange) {
	int count = 0;
        for (int i = 0; i < MEASURED_VALUES.length; i++) {
            if ((MEASURED_VALUES[i] >= minRange) && (MEASURED_VALUES[i] <= maxRange)) {
                count = count + 1;
            }
        }
        return count;
    }
    
    /**
     * erzeugt ein Array mit Punktwerten innerhalb übergebener Grenzen
     * 
     * @param minRange  untere Grenze (inklusive)
     * @param maxRange  obere  Grenze (inklusive)
     * @return ein neues Array nur mit den gewünschten Punktwerten
     */
    public static int[] getValuesInRange(int minRange, int maxRange) {
        int count = 0;
        int[] valuesInRange = new int[countOccurencesInRange(minRange, maxRange)];

        for (int i = 0; i < MEASURED_VALUES.length; i++) {
            if ((MEASURED_VALUES[i] >= minRange) && (MEASURED_VALUES[i] <= maxRange)) {
                valuesInRange[count] = MEASURED_VALUES[i];
                count = count + 1;
            }
        }
	return valuesInRange;
    }
    
    /**
     * liefert den Mittelwert der Messwerte
     * 
     * @return Mittelwert der Messwerte dieser Klasse
     */
    public static float getAverage () {
	int sum = 0;
        
        for (int i = 0; i < MEASURED_VALUES.length; i++) {
            sum = sum + MEASURED_VALUES[i];
        }
        return ((float) sum / MEASURED_VALUES.length);
    }
    
    /**
     * Der Median teilt eine Liste von Werten in zwei Hälften. 
     * Er kann auf folgende Weise bestimmt werden:
     *   Alle Werte werden geordnet.
     *   Wenn die Anzahl der Werte ungerade ist, ist die mittlere Zahl der Median.
     *   Wenn die Anzahl der Werte gerade ist, wird der Median meist als 
     *     arithmetisches Mittel der beiden mittleren Zahlen definiert, 
     *     die dann Unter- und Obermedian heißen.
     * 
     * @return Median der Werte dieses Arrays
     */
    public static float getMedian () {
        int[] sorted;
        int arraylength;
        
        // sortiertes Array übertragen um die Methodenaufrufe von getSortedArray zu reduzieren
        sorted = ArraySort.getSortedArray(MEASURED_VALUES);
        arraylength = sorted.length;
        
        // Bei Arrays mit ungerader Anzahl gibt es exakt ein mittleres Element
        if ((arraylength % 2) != 0) {
            // Einfacher Fall, es gibt genau eine Mitte
            return (sorted[arraylength / 2]);
        } else {
            
            return (sorted[(arraylength / 2) - 1] + sorted[arraylength / 2]) / 2.0f;
        }
    }
    
}
