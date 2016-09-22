/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grp13_ueb02;

/**
 *
 * @author Paul
 */
public class Grp13_ueb02 {
    
    /**
     * Gibt aufsteigend sortiert die ueberschreitungen aus, sofern vorhanden 
     * 
     * @param indexOfFine 
     */
    public static void printValuesOfOffenders(int indexOfFine) {
        int[] offender = ArraySort.getSortedArray(Fines.getData(indexOfFine));

        if (Fines.getData(indexOfFine).length != 0) {
            System.out.printf("%d Ueberschreitung(en) [%3d ..%3d km/h] mit %3d Euro Strafe: ",
                    Fines.getData(indexOfFine).length, Fines.getMinSpeedForFine(indexOfFine), Fines.getMaxSpeedForFine(indexOfFine), Fines.getFineForIndex(indexOfFine));
            for (int i = 0; i < offender.length; i++) {
                if (i != 0) System.out.printf(", ");
                System.out.printf("%3d", offender[i]);
            }
            System.out.println("");
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String location = MeasuredValues.isInTown() ? "innerorts":"ausserorts"; 
        
        //Erstellung des data arrays
        Fines.createDataArray(Fines.getNoofFineRanges(), MeasuredValues.getValuesInRange(MeasuredValues.getAllowedSpeed(), MeasuredValues.getMaxSpeed()));
        
        System.out.printf("Messung                 :  %s\n",
                location);
        System.out.printf("erlaubte Geschwindigkeit: %3d %7s \n",
                MeasuredValues.getAllowedSpeed(),"km/h");
        System.out.printf("mittlere Geschwindigkeit: %6.2f %2s \n",
                MeasuredValues.getAverage(),"km/h");
        System.out.printf("Median Geschwindigkeit  : %6.2f %2s \n\n",
                MeasuredValues.getMedian(),"km/h");
        
        for (int i = 0; i < Fines.getNoofFineRanges(); i++) {
            printValuesOfOffenders(i);
        }

    }
}
