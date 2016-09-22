package grp13_ueb02;

/**
 *
 * public Routinen: getSortedArray() - sortiert ein Array in aufsteigender Reihenfolge
 *
 * @author Paul
 */
public class ArraySort {

    /**
     * sortiert ein Array in aufsteigender Reihenfolge (Bubble Sort)
     * @param unsorted unsortiertes Array
     * @return sortiertes Array
     */
    public static int[] getSortedArray(int[] unsorted) {
        int[] copy = unsorted.clone();
        
        
        int act;
        int next;

        // einfachste Umsetzung von Bubble Sort mit statischer Anzahl Durchläufen (könnte noch optimiert werden)
        for (int i = 0; i < copy.length; i++) {
            for (int j = 0; j < (copy.length-1); j++) {
                act = copy[j];
                next = copy[j+1];
                if (next < act) {
                    copy[j] = next;
                    copy[j+1] = act;
                }
            }
        }
        return copy;
    }
}
