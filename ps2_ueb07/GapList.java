/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grp13_ueb07;

/**
 *
 * @author Paul
 */
public interface GapList {
    // Methoden die von GapListEmptyElement implementiert werden
    boolean equals(Object obj);
    String toString();
    
    // Methoden die von GapListElement implementiert werden
    Gap getPayload();
    GapList getNext();
    boolean isEmpty();
    int length();
    boolean hasJustOneElement();
    boolean hasGap(int length);
    boolean hasGapAt(int position, int length);
    int getLargestGap(int yetLargest);
    int getLargestGap();
    int getSmallestGapBiggerThan(int minLength, int yetMinLength, int foundPosition);
    int findPositionFor(int length);
    GapList remove(int position, int length);
    GapList remove(Gap gap);
    GapList insert(Gap gap);
    GapList insert(int position, int length);
}
