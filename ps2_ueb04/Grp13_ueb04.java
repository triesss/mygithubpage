/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grp13_ueb04;

import java.util.Arrays;

/**
 *
 * @author
 */
public class Grp13_ueb04 {

    static final String TRENNZEICHEN = "/";

    /**
     * erhält als Parameter zwei Mengen und die dem Programm übergebene
     * Argumentliste und füllt die Mengen mit den zu Zahlen umgewandelten
     * Strings aus der Argumentliste
     *
     * @param setArray Array bestehend aus 2 Mengen
     * @param stringArray Argumentliste
     */
    private static void getSetsFromArgs(Set[] setArray, String[] stringArray) {

        // Werte in Array schreiben        
        String[][] bufferString = new String[2][stringArray.length];
        int arrayCount = 0;

        // die erste For-Schleife überträgt die Werte solange bis das Ende des
        // Arrays erreicht ist oder das Trennzeichen erkannt wird
        for (int i = 0; i < stringArray.length; i++) {
            if (!stringArray[i].contains(TRENNZEICHEN)) {
                bufferString[0][i] = stringArray[i];
            } else {
                // wenn das Trennzeichen erreicht ist, wird die Position in die
                // Hilfsvariable arrayCount weggespeichert und die Schleife
                // verlassen
                arrayCount = i + 1;
                break;
            }
        }
        
        // wenn zuvor ein Trennzeichen erkannt wurde, werden die nachfolgenden
        // Werte in die zweite Menge geschrieben
        if (!(arrayCount == 0)) {
            // die zweite Schleife zählt nur noch die restlichen Einträge des
            // übergebenen Arrays ab, beginnt beim Abspeichern aber mit Index 0
            for (int i = 0; i < (stringArray.length - arrayCount); i++) {
                bufferString[1][i] = stringArray[i + arrayCount].replace(",", "");
            }
        }

        // Werte aus Array in Set speichern
        for (int i = 0; i < 2; i++) {
            setArray[i] = new Set();
            for (int j = 0; j < bufferString[i].length; j++) {
                if (!(bufferString[i][j] == null)) {
                    setArray[i].addElement(Integer.valueOf(bufferString[i][j]));
                }
            }
        }
    }

    /**
     * ruft mit den zwei Mengen übergebenen Mengen jede der über die Methoden
     * von Set angebotenen Mengenoperationen auf und gibt die Ergebnisse aus
     *
     * @param setArray Array bestehend aus 2 Mengen
     */
    private static void testClassSet(Set[] setArray) {
        System.out.println("Menge A              : " + setArray[0].showValues());
        System.out.println("Menge B              : " + setArray[1].showValues());
        System.out.println("Vereinigung     A ∪ B: " + setArray[0].union(setArray[1]).showValues());
        System.out.println("Schnittmenge    A ∩ B: " + setArray[0].intersectuion(setArray[1]).showValues());
        System.out.println("Differenzmenge  A \\ B: " + setArray[0].diff(setArray[1]).showValues());
        System.out.println("echte Teilmenge A ⊂ B: " + setArray[0].isProperSubSet(setArray[1]));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Set[] SetArray = new Set[2];

        System.out.println("Argumente            : " + Arrays.toString(args));

        getSetsFromArgs(SetArray, args);
        testClassSet(SetArray);
    }

}
