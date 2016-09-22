/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grp13_ueb07;

import java.util.Arrays;

/**
 *
 * @author Alex
 */
public class ArrayRow extends Row{
    Gap[] gapArray;
    
    public ArrayRow(int length){
        gapArray = new Gap[1];
        this.length = length;
        this.gapArray[0] = new Gap(0, length);
    }
    
    @Override
    public boolean isEmpty(){
        return this.hasJustOneElement() && this.gapArray[0].getStart() == 0;
    }
    
    @Override
    public boolean isOptimallyFilled(){
        return gapArray[0] == null;
    }
    
    @Override
    public boolean hasFreeGap(int carLength){
        //TODO    
        return false;
    }
    
    @Override
    public int getLargestGap(){
        //TODO    
        return -1;
    }
    
    @Override
    public int findBestPosition(int carLength){
        //TODO
        return -1;
    }
    
    @Override
    public boolean occupyPlace(int position, int carLength){
        //TODO
        return false;
    }
    
    @Override
    public int parkCar(int carLength) {
        //TODO
        return -1;
    }
    
    @Override
    public void freePlaceAt(int position, int length) {
        gapArray = this.insert(position, length);
    }
    
    @Override
    public String toString(){
        //TODO
        return "";
    }
    
    private boolean hasJustOneElement(){
        if(gapArray[1] == null) return true;
        else return false;
    }
    
    private Gap[] insert(Gap gap){
        for (int i = 0 ; i < gapArray.length ; i++){
            if (gapArray[i] == null){
                gapArray[i] = gap;
                return gapArray;
            }
            else if (gapArray[i].contains(gap)){
                return this.gapArray;
            }
            else if (gapArray[i].overlaps(gap) || (gapArray[i].touches(gap))){
                this.gapArray[i] = this.gapArray[i].union(gap);
                return gapArray;
            }
            else if (gapArray[i].succeeds(gap)){
                Gap[] tempgap = new Gap[gapArray.length +1];
                System.arraycopy(gapArray, 0, tempgap, 0, i);
                tempgap[i] = gap;
                System.arraycopy(gapArray, i, tempgap, i+1, gapArray.length-i);
                gapArray = tempgap;
                return gapArray;
            }
            else if (i == gapArray.length - 1 ) {
                Gap[] tempgap = new Gap[gapArray.length +1];
                System.arraycopy(gapArray, 0, tempgap, 0, gapArray.length);
                tempgap[gapArray.length] = gap;
                gapArray = tempgap;
                return gapArray;
            }
        }
        return gapArray;
    }
    
    private Gap[] insert(int position, int length){
        Gap gap = new Gap(position, position + length);
        gapArray = this.insert(gap);
        return gapArray;
    }
   
    //TODO funktioniert nicht fuer Bereiche ueber mehrere Gaps innerhalb des Arrays
    private Gap[] remove(int position, int length){
        for (int i = 0 ; i < gapArray.length ; i++){
            if(position >= this.gapArray[i].getStart() && (position + length) <= this.gapArray[i].getEnd()){
                Gap[] luecken;
                luecken = this.gapArray[i].sub(new Gap(position, (position + length)));
                this.gapArray[i] = null;
                for (Gap luecken1 : luecken) {
                    gapArray = this.insert(luecken1);
                }
                return this.gapArray;
            }            
        }
        return gapArray;
    }
    
    private Gap[] remove(Gap gap){
        gapArray = this.remove(gap.getStart(), gap.getLength());
        return gapArray;
    }
    
    @Override
    public boolean equals(Object obj){
        if( obj != null && obj instanceof ArrayRow){
            ArrayRow row = (ArrayRow) obj;
            if ( Arrays.equals(this.gapArray, row.gapArray) && this.length == row.length ) return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + Arrays.deepHashCode(this.gapArray);
        hash = 73 * hash + this.length;
        return hash;
    }
    
    public static void main(String[] args){
        ArrayRow row = new ArrayRow(20);
//        for (Gap gapArray1 : row.gapArray) {
//            System.out.println(gapArray1.getLength());
//        }
        assert !row.gapArray[0].contains(new Gap(5,25));
        assert row.gapArray[0].overlaps(new Gap(5,25));
        row.gapArray = row.insert(new Gap(5,25));
        row.gapArray = row.insert(new Gap(35,40));
        row.gapArray = row.insert(new Gap(26,32));
        row.gapArray = row.insert(new Gap(2,6));
        row.gapArray = row.remove(0, 12);
        row.gapArray = row.insert(new Gap(33,34));
        row.gapArray = row.insert(42,6);        
        row.gapArray = row.remove(new Gap(0,60));
        for (Gap gapArray1 : row.gapArray) {
            System.out.println(gapArray1.getLength() + " Start:" + gapArray1.getStart()+" Ende:"+gapArray1.getEnd());
        }
    }
}
