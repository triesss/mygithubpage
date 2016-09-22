/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grp13_ueb01;

import grp13_ueb01.*;

/**
 * Fuer die Zahlen 5 bis 15. die durch 3 oder 5 teilbar sind, sollen die Anzahl
 * der Ziffern und 10 och die Zahl ausegegeben werden.
 *
 * @author minf101260
 */
public class Grp13_ueb01 {


    /**
     *
     */
    public static void main(String[] args) {
    final int MIN = 2600;
    final int MAX = 3000;

        //hier Zahlenbereich MIN - MAX durchlaufen und Methoden aufrufen!
        for (int i = MIN; i <= MAX; i++) {
            if (paul.vollkommen(i)) {
                System.out.printf("%5d: ist vollkommen\n", i);
            }

            if (paul.befreundet(i)) {
                /* an dieser Stelle würde eigentlich folgender Aufruf ausreichen
                 damit wäre die Funktion befreundeteZahl() aber überflüssig, was
                 dann nicht mehr der Aufgabenstellung entspricht
                
                 System.out.printf("%5d: ist befreundet mit: %d\n", i, paul.summeTeiler(i));
                 */
                System.out.printf("%5d: ist befreundet mit: %d\n", i, paul.befreundeteZahl(i));
            }

            /*  if (alex.isPalindrom(i)) {
             System.out.printf("%5d: ist ein Palindrom\n", i);
             }*/
            if (alex.isPalindromMathematic(i)) {
                System.out.printf("%5d: ist ein Palindrom\n", i);
            }

            if (alex.isTriangularNumber(i)) {
                System.out.printf("%5d: ist eine Dreieckszahl\n", i);
            }
        }
    }

}
