/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grp13_ueb07;

/**
 *
 * @author Alex
 */
public class ListRow extends Row{
    GapList gapList;
    
    public ListRow(int length){
        this.gapList = new GapListEmptyElement();
        this.length = length;
        gapList = gapList.insert(new Gap(0, length));
    }
    
    @Override
    public boolean isEmpty(){
        return gapList.hasJustOneElement() && gapList.getPayload().getStart() == 0;
    }
    
    @Override
    public boolean isOptimallyFilled(){
        return gapList.isEmpty();
    }
    
    @Override
    public boolean hasFreeGap(int carLength){
        return gapList.findPositionFor(carLength) != -1;
    }    
    
    @Override
    public int getLargestGap(){
        return gapList.getLargestGap();
    }
    
    @Override
    public int findBestPosition(int carLength){
        return gapList.findPositionFor(carLength);
    }
    
    @Override
    public boolean occupyPlace(int position, int carLength){
        if( gapList.hasGapAt(position, carLength) ){
                this.gapList = this.gapList.remove(position, carLength);
                return true;
        }        
        return false;
    }
    
    @Override
    public int parkCar(int carLength) {
        int pos;
        if( gapList.findPositionFor(carLength) != -1 ){      
                pos = gapList.findPositionFor(carLength);
                this.gapList = this.gapList.remove(pos, carLength);
                return pos;
        }
        return -1;
    }
        
    @Override
    public void freePlaceAt(int position, int length) {
        gapList = gapList.insert(position, length);
    }
    
    @Override
    public boolean equals(Object obj){
        if( obj != null && obj instanceof ListRow){
            ListRow row = (ListRow) obj;
            if ( this.gapList.equals(row.gapList) && this.length == row.length ) return true;
        }
        return false;
    }
    
    @Override
    public String toString(){
        return "{" + gapList.toString() + "}"; 
    }


    public static void main(String[] args) {
        ListRow row = new ListRow(20);
        //System.out.println(row.toString());
        assert "{  0.. 20}".equals(row.toString());
        assert 0 == row.findBestPosition(5);
        assert row.occupyPlace(row.findBestPosition(5),5);
        assert !row.isEmpty();
        assert 15 == row.getLargestGap();
        assert !row.isOptimallyFilled();
        assert row.occupyPlace(7, 3);
        assert !row.isEmpty();
        //System.out.println(row.toString());
        assert "{  5..  7,  10.. 20}".equals(row.toString());
        assert row.gapList.hasGapAt(5, 2);
        assert row.gapList.hasGapAt(10, 10);
        assert 10 == row.parkCar(6);
        //System.out.println(row.toString());
        assert "{  5..  7,  16.. 20}".equals(row.toString());
        assert 4 == row.getLargestGap();
        assert 5 == row.parkCar(2);
        assert 16 == row.parkCar(4);
        assert row.isOptimallyFilled();
        //System.out.println(row.toString());
        assert "{}".equals(row.toString());
//        int[][] carList = { {2,2}, {5,7}, {15,4}, {20,1} };
//        ListRow roow = new ListRow( 25,carList );
//        System.out.println(roow.toString());
        
//        Row row;
//        row = new Row(30, new int[][]{{24, 6}});
//        row.freePlaceAt(22, 8);
//        assert row.isEmpty();
    }

}