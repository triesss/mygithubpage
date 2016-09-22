/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grp13_ueb02;

/**
 *
 * @author Alexander
 */
public class Fines {

    /**
     * Bussgeldbereiche (Überschreitung bis zu x km/h) Fährt also jemand bei
     * erlaubten 50 Stundenkilometern außerorts 60 km/h, so muss er 10 Euro
     * bezahlen, bei 61 km/h muss er 20 Euro bezahlen.
     */
    private static final int[] RANGES = {
        10, 15, 20, 25, 30, 40, 50, 60, 70
    };
    /**
     * Bussgelder in Euro bei Geschwindigkeitsüberschreitung außerorts und
     * innerorts
     */
    private static final int[][] FINES = {
        {10, 20, 30, 70, 80, 120, 160, 240, 440, 600},
        {15, 25, 35, 80, 100, 160, 200, 280, 480, 680}
    };

    private static int[][] data;

    /**
     *
     * @return Anzahl der Fines
     */
    public static int getNoofFineRanges() {
        int noOfFines;

        noOfFines = MeasuredValues.isInTown() ? FINES[1].length : FINES[0].length;
        return noOfFines;
    }

    /**
     *
     * @param indexOfFine
     * @return Mindestgeschwindigkeit fuer uebergebenes Fine
     */
    public static int getMinSpeedForFine(int indexOfFine) {
        int minSpeed;

        if (indexOfFine == 0) {
            minSpeed = MeasuredValues.getAllowedSpeed() + 1;
        } else {
            minSpeed = (MeasuredValues.getAllowedSpeed() + RANGES[indexOfFine - 1] + 1);
        }

        return minSpeed;
    }

    /**
     *
     * @param indexOfFine
     * @return Maximalgeschwindigkeit fuer uebergebenes Fine
     */
    public static int getMaxSpeedForFine(int indexOfFine) {
        int maxSpeed;

        maxSpeed = (indexOfFine == FINES[1].length - 1) ? MeasuredValues.getMaxSpeed() : MeasuredValues.getAllowedSpeed() + RANGES[indexOfFine];

        return maxSpeed;
    }

    /**
     *
     * @param indexOfFine
     * @return fine at index
     */
    public static int getFineForIndex(int indexOfFine) {
        return MeasuredValues.isInTown() ? FINES[1][indexOfFine] : FINES[0][indexOfFine];
    }

    /**
     * Erstellt die Klassenvariable data und befuellt sie mit bereinigten Werten
     *
     * @param numOfFines
     * @param measuredValues
     */
    public static void createDataArray(int numOfFines, int[] measuredValues) {
        data = new int[numOfFines][];

        for (int i = 0; i < numOfFines; i++) {
            data[i] = MeasuredValues.getValuesInRange(getMinSpeedForFine(i), getMaxSpeedForFine(i));
        }
    }

    /**
     *
     * @param indexOfFine
     * @return data-array for fine-index
     */
    public static int[] getData(int indexOfFine) {
        return data[indexOfFine];
    }
}
