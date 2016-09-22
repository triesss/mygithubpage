/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grp13_ueb01;

/**
 * Aufgabe: Vordefinierten Zahlenbereich durchlaufen und pruefen, ob die
 * jeweilige Zahl ein Palindrom- und/oder eine Dreieckszahl ist
 *
 * @author Alexander
 */
public class alex {

    /**
     * leichte Variante mit itoa
     *
     * @param n
     * @return true wenn n ein palindrom ist, sonst false
     */
    public static boolean isPalindrom(int n) {
        String temp = Integer.toString(n);
        char[] dummy = temp.toCharArray();
        for (int i = 0, j = (dummy.length - 1); i <= j; i++, j--) {
            if (dummy[i] != dummy[j]) {
                return false;
            }
        }
        return true;
    }

    /**
     * nicht so leichte Variante mit mathematischer Loesung
     *
     * @param n
     * @return true wenn n ein palindrom ist, sonst false
     */
    public static boolean isPalindromMathematic(int n) {
        int exp = (int) Math.log10(n) + 1;
        boolean isPalindrom = true;
        for (int i = 1, j = exp; (i <= j) && isPalindrom; i++, j--) {
            if (numAtPos(n, i) != numAtPos(n, j)) {
                isPalindrom = false;
            }
        }
        return isPalindrom;
    }

    /**
     * log10(n)+1 ermittelt die anzahl der ziffern von n 
     * danach werden die zahlen von links nach rechts bis zur stelle p durchlaufen
     * (9340 % 10^3)/10^2) ergibt z.B. 3
     * @param n - number
     * @param p - position
     * @return Nummer an p-ter Stelle von n
     */
    public static int numAtPos(int n, int p) {
        int countOfDigits = (int) Math.log10(n) + 1;
        int divider = 1;
        for (int i = 1; i != countOfDigits; i++) {
            divider *= 10;
        }
        int num = n / divider;
        for (int i = p; i != countOfDigits; i++, divider /= 10) {
            num = (n % divider) / (divider / 10);
        }
        return num;
    }

    /**
     *
     * @param n
     * @return true wenn n eine Dreieckszahl ist, sonst false
     */
    public static boolean isTriangularNumber(int n) {
        int triangularNumber = 0;
        boolean isTriangularNumber = false;
        for (int i = 1; (triangularNumber <= n) && !isTriangularNumber; i++) {
            triangularNumber = triangularNumber(i);
            if (n == triangularNumber) {
                isTriangularNumber = true;
            }
        }
        return isTriangularNumber;
    }

    /**
     * errechnet die n-te Dreieckszahl
     * @param n
     * @return die n-te Dreieckszahl
     */
    public static int triangularNumber(int n) {
        int res = 0;
        for (int i = 1; i <= n; i++) {
            res += i;
        }
        return res;
    }
}
