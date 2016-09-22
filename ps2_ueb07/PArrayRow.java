package grp13_ueb07;

import java.util.Arrays;

/**
 * eine Parkreihe wird abgebildet Initial besteht eine Reihe aus einer einzigen
 * riesigen Lücke.
 *
 * @author Gerit
 *
 *
 */
public class PArrayRow extends PRow {

    private Gap[] gapArray;

    /**
     * Konstruktor enthält Länge, damit gleich eine Lücke über diese Länge
     * angelegt werden kann
     *
     * @param length Länge der Reihe
     */
    public PArrayRow(int length) {
        gapArray = new Gap[1];
        this.length = length;
        this.gapArray[0] = new Gap(0, length);
    }

    /**
     * Konstruktor zum Testen bekommt alle Belegungen in Array übergeben
     *
     * @param length Länge der Reihe
     * @param carList zweidimensionales Array [alle Belegungen][Position und
     * Länge der Belegung]
     */
    public PArrayRow(int length, int[][] carList) {
        this(length);
        for (int[] singleCar : carList) {
            this.occupyPlace(singleCar[0], singleCar[1]);
        }
    }

    /**
     * liefert die Länge der Reihe
     *
     * @return die Länge der Reihe
     */
    @Override
    public int getLength() {
        return this.length;
    }

    /**
     * prüft, ob die Reihe aus einer einzigen Lücke besteht
     *
     * @return true, wenn die Reihe aus einer einzigen Lücke besteht
     */
    @Override
    public boolean isEmpty() {
//        // wäre eigentlich auch zu prüfen, ob die einzige Lücke auch die gesamte Länge der Reihe
//        // abdeckt, dann passen aber die asserts nicht mehr
        return this.gapArray.length == 1 && this.gapArray[0].getLength() == this.length;
//        return this.gapArray.length == 1;
    }

    /**
     * prüft, ob keine Lücken enthalten sind
     *
     * @return true, wenn keine Lücke enthalten ist
     */
    @Override
    public boolean isOptimallyFilled() {
        return this.gapArray.length == 0;
    }

    /**
     * prüft, ob eine freie Lücke für das Auto vorhanden ist
     *
     * @param carLength Länge des Autos
     * @return true, wenn Lücke vorhanden
     */
    @Override
    public boolean hasFreeGap(int carLength) {
        boolean check = false;
        for (Gap gapArray1 : this.gapArray) {
            if (gapArray1.getLength() >= carLength) {
                check = true;
            }
        }
        return check;
    }

    /**
     * liefert die größte Lücke in der Reihe
     *
     * @return die größte Lücke in der Reihe
     */
    @Override
    public int getLargestGap() {
        int largest = 0;
        for (Gap gapArray1 : this.gapArray) {
            if (gapArray1.getLength() > largest) {
                largest = gapArray1.getLength();
            }
        }
        return largest;
    }

    /**
     * liefert die Position der kleinsten pasenden Lücke in der Reihe
     *
     * @param carlength Länge des unterzubringenden Autos
     * @return die Position der kleinsten passenden Lücke in der Reihe
     */
    @Override
    public int findBestPosition(int carlength) {
        int kleinstpassende = -1;
        // gesamtes Array durchlaufen
        for (int i = 0; i < this.gapArray.length; i++) {
            // wenn passende Lücke gefunden wird
            if (this.gapArray[i].getLength() >= carlength) {
                // Index merken, wenn das der erste Treffer ist
                if (kleinstpassende == -1) {
                    kleinstpassende = i;
                } // Index nur merken, wenn die gefundene, passende Lücke kleiner ist als die bisher
                // kleinste gefundene, passende Lücke
                else if (this.gapArray[i].getLength() < this.gapArray[kleinstpassende].getLength()) {
                    kleinstpassende = i;
                }
            }

        }
        return kleinstpassende == -1
                ? kleinstpassende
                : this.gapArray[kleinstpassende].getStart();
    }

    /**
     * belegt Lücke
     *
     * @param position an der Position
     * @param carLength mit der Länge
     * @return true, wenn die Lücke noch frei war
     */
    @Override
    public boolean occupyPlace(int position, int carLength) {
        Gap car = new Gap(position, position + carLength);
        Gap[] subGaps;
        boolean check = false;
        for (int i = 0; i < this.gapArray.length; i++) {
            // wenn eine Lücke für die Position des Autos gefunden wurde
            if (this.gapArray[i].contains(car)) {
                subGaps = this.gapArray[i].sub(car);
                check = true;
                // sub() liefert ein Array zurück mit 0, 1 oder 2 neuen Lücken, diese müssen in das
                // aktuelle Array übernommen werden
                switch (subGaps.length) {
                    // wenn die Lücke vollständig belegt wurde
                    case 0:
                        // neues Array mit Länge - 1 anlegen, abgesehen von dem aktuellen Wert alle
                        // ArrayElemente übernehmen und wieder in this.gatArray zurück schreiben
                        Gap[] newGap = new Gap[this.gapArray.length - 1];
                        for (int j = 0; j < this.gapArray.length; j++) {
                            if (i > j) {
                                newGap[j] = this.gapArray[j];
                            } else if (i < j) {
                                newGap[j - 1] = this.gapArray[j];
                            }
                        }
                        this.gapArray = newGap.clone();
                        break;
                    // wenn eine neue (verkürzte) Lücken entstanden ist
                    case 1:
                        // aktuelle Lücke überschreiben mit neuer verkürzter Lücke
                        this.gapArray[i] = subGaps[0];
                        break;
                    // wenn zwei neue Lücken entstanden sind
                    case 2:
                        // neues Array mit Länge + 1 anlegen, Rückgabewerte aus sub einfügen und
                        // wieder in this.gatArray zurück schreiben
                        Gap[] newGaps = new Gap[this.gapArray.length + 1];
                        for (int j = 0; j < this.gapArray.length + 1; j++) {
                            if (i > j) {
                                newGaps[j] = this.gapArray[j];
                            } else if (i == j) {
                                newGaps[j] = subGaps[0];
                            } else if (i + 1 == j) {
                                newGaps[j] = subGaps[1];
                            } else {
                                newGaps[j] = this.gapArray[j - 1];
                            }
                        }
                        this.gapArray = newGaps.clone();
                        break;
                }
            }
        }
        return check;
    }

    /**
     * belegt Lücke für dieses Auto
     *
     * @param carLength
     * @return Position des Autos, -1, wenn keine passende Lücke vorhanden
     */
    @Override
    public int parkCar(int carLength) {
        int pos;
        if (this.findBestPosition(carLength) != -1) {
            pos = this.findBestPosition(carLength);
            this.occupyPlace(pos, carLength);
            return pos;
        }
        return -1;
    }

    /**
     * gibt den Platz an position wieder frei. Die Angabe muss nicht exakt sein,
     * sondern darf mit einer bestehenden Lücke überlappen. Es wird dann nur der
     * belegte Teil freigegeben.
     *
     * @param position Startposition der Freigabe
     * @param length Länge der Freigabe
     */
    @Override
    public void freePlaceAt(int position, int length) {
        boolean check = false;
        int indexofstart = -1;
        int indexofend = -1;
        Gap newGap = new Gap(position, position + length);
        
        // Array durchlaufen und prüfen ob die neue Lücke eine/mehrere vorhandene Lücken berührt,
        // beinhaltet oder überlappt
        for (int i = 0; i < this.gapArray.length; i++) {
            if (indexofstart == -1 && 
                    newGap.overlaps(this.gapArray[i]) || 
                    this.gapArray[i].touches(newGap) || 
                    this.gapArray[i].contains( newGap.getStart())) 
                indexofstart = i;
            if (this.gapArray[i].overlaps(newGap) || 
                    this.gapArray[i].touches(newGap) || 
                    this.gapArray[i].contains( newGap.getEnd())) 
                indexofend = i;           
        }

        // wenn wenn eine/mehrere Überlappungen/Berührungen festgestellt wurden und der
        // freizugebende Platz innerhalb der Row liegt        
        if(indexofstart != -1 && indexofend != -1 && this.getLength() >= newGap.getEnd()){
            Gap newGaps = new Gap ( this.gapArray[indexofstart].getStart(), 
                    this.gapArray[indexofend].getEnd());
            newGaps = newGaps.union(newGap);

            int diff = indexofend-indexofstart;
            Gap[] newGapArray = new Gap[this.gapArray.length - diff];

            System.arraycopy(gapArray, 0, newGapArray, 0, indexofstart);
            newGapArray[indexofstart] = newGaps;                
            if(indexofend != gapArray.length -1 ) System.arraycopy(gapArray, indexofend + 1, 
                    newGapArray, indexofstart + 1 , gapArray.length-indexofend - 1);

            this.gapArray = newGapArray.clone();
            check = true;
        }
        
        // wenn der freizugebende Platz keine vorhandene Lücke überlappt/berührt aber
        // innerhalb der Row liegt
        if (check == false && this.getLength() >= newGap.getEnd()) {
            // neues Array mit Länge + 1 erstellen
            Gap[] newGaps = new Gap[this.gapArray.length + 1];
            // bisherige ArrayElemente übernehmen
            for (int i = 0; i < this.gapArray.length; i++) {
                if (this.gapArray[i].getStart() < position + length) {
                    newGaps[i] = this.gapArray[i];
                } else {
                    newGaps[i + 1] = this.gapArray[i];
                }
            }
            // leer gebliebenes ArrayElement mit neuer Lücke füllen
            for (int i = 0; i < newGaps.length; i++) {
                if (newGaps[i] == null) {
                    newGaps[i] = new Gap(position, position + length);
                }
            }
            this.gapArray = newGaps.clone();
        }
    }

    /**
     * überschreibt equals()
     *
     * @param obj soll auch eine Row sein
     * @return true, wenn Reihen gleich sind
     */
    @Override
    public boolean equals(Object obj) {
        boolean check = false;
        if (obj instanceof PArrayRow) {
            PArrayRow newArrayRow = (PArrayRow) obj;
            // die Arrays die selbe Länge haben, wird erstmal von einer Übereinstimmung ausgegangen
            if (super.equals(obj) && this.gapArray.length == newArrayRow.gapArray.length) {
                check = true;
                // dann werden die Arrays durchlaufen um eine eventuelle Abweichung festzustellen
                for (int i = 0; i < this.gapArray.length && check; i++) {
                    if (!this.gapArray[i].equals(newArrayRow.gapArray[i])) {
                        check = false;
                    }
                }
            }
        }
        return check;
    }

    // automatisch erzeugt
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Arrays.deepHashCode(this.gapArray);
        return hash;
    }

    /**
     * liefert eine Stringdarstellung der Lückenliste
     *
     * @return eine Stringdarstellung der Lückenliste
     */
    @Override
    public String toString() {
        String string = "{";
        if (this.gapArray.length != 0) {
            string = string + this.gapArray[0].toString();
        }
        for (int i = 1; i < this.gapArray.length; i++) {
//            if (i == 0) string = this.gapArray[i].toString();
//            else 
            string = string + ", " + this.gapArray[i].toString();
        }
        //      if (!"".equals(string)) string = string + "}";
        return string + "}";
    }

    // --- Tests ------------------------------------------------
    public static void main(String[] args) {
        PRow row = new PArrayRow(20);
//        System.out.println(row.toString());
        assert "{  0.. 20}".equals(row.toString());
        assert 0 == row.findBestPosition(5);
        assert row.occupyPlace(row.findBestPosition(5), 5);
//        assert row.isEmpty();
        assert 15 == row.getLargestGap();
        assert !row.isOptimallyFilled();
//        System.out.println(row.toString());
        assert row.occupyPlace(7, 3);
        assert !row.isEmpty();
//        System.out.println(row.toString());
        assert "{  5..  7,  10.. 20}".equals(row.toString());
        assert 10 == row.parkCar(6);
//        System.out.println(row.toString());
        assert "{  5..  7,  16.. 20}".equals(row.toString());
        assert 4 == row.getLargestGap();
        assert 5 == row.parkCar(2);
//        System.out.println(row.toString());
        assert 16 == row.parkCar(4);
        assert row.isOptimallyFilled();
//        System.out.println(row.toString());
//        assert "".equals(row.toString());
        
        // Test für Überlappung mehrerer Lücken
        // beginnt und endet in einer Lücke
        System.out.println("\nneue Lücke von 1 bis 13 - beginnt und endet in einer Lücke");
        int[][] carList = {{2, 2}, {5, 7}, {15, 4}, {20, 1}};
        PRow roow = new PArrayRow(25, carList);
        System.out.println(roow.toString());
        roow.freePlaceAt(1, 12);        
        System.out.println(roow.toString());

        // beginnt in belegtem Platz und endet in einer Lücke
        System.out.println("\nneue Lücke von 3 bis 22 - beginnt in belegtem Platz und endet in einer Lücke");
        roow = new PArrayRow(25, carList);
        System.out.println(roow.toString());
        roow.freePlaceAt(3, 19);        
        System.out.println(roow.toString());        
 
        // beginnt in einer Lücke und endet in belegtem Platz
        System.out.println("\nneue Lücke von 4 bis 17 - beginnt in einer Lücke und endet in belegtem Platz");
        roow = new PArrayRow(25, carList);
        System.out.println(roow.toString());
        roow.freePlaceAt(4, 13);        
        System.out.println(roow.toString());        
 
        // beginnt und endet in belegtem Platz
        System.out.println("\nneue Lücke von 3 bis 16 - beginnt und endet in belegtem Platz");
        roow = new PArrayRow(25, carList);
        System.out.println(roow.toString());
        roow.freePlaceAt(3, 13);        
        System.out.println(roow.toString());
        
        // beginnt und endet in der selben Lücke
        System.out.println("\nneue Lücke von 13 bis 14 - beginnt und endet in der selben Lücke");
        roow = new PArrayRow(25, carList);
        System.out.println(roow.toString());
        roow.freePlaceAt(13, 1);        
        System.out.println(roow.toString());       
        
        // beginnt und endet in dem selben belegten Platz
        System.out.println("\nneue Lücke von 17 bis 18 - beginnt und endet in dem selben belegten Platz");
        roow = new PArrayRow(25, carList);
        System.out.println(roow.toString());
        roow.freePlaceAt(17, 1);        
        System.out.println(roow.toString());           

        // neue Lücke nutzt die ganze Länge von Row
        System.out.println("\nneue Lücke von 0 bis 25 - neue Lücke nutzt die ganze Länge von Row");
        roow = new PArrayRow(25, carList);
        System.out.println(roow.toString());
        roow.freePlaceAt(0, 25);        
        System.out.println(roow.toString());  
 
        // neue Lücke geht über die Länge der Row hinaus
        System.out.println("\nneue Lücke von 0 bis 30 (bewusste Fehlersituation)");
        roow = new PArrayRow(25, carList);
        System.out.println(roow.toString());
        roow.freePlaceAt(0, 30);        
        System.out.println(roow.toString());         
 
        // neue Lücke beginnt vor erster Lücke und grenzt an
        System.out.println("\nneue Lücke von 0 bis 2 - neue Lücke beginnt vor erster Lücke und grenzt an");
        int[][] newcarList = {{0, 2}, {5, 7}, {15, 4}, {20, 5}};
        roow = new PArrayRow(25, newcarList);
        System.out.println(roow.toString());
        roow.freePlaceAt(0, 2);        
        System.out.println(roow.toString());         
 
        // neue Lücke beginnt vor erster Lücke und grenzt nicht an
        System.out.println("\nneue Lücke von 0 bis 1 - neue Lücke beginnt vor erster Lücke und grenzt nicht an");
        roow = new PArrayRow(25, newcarList);
        System.out.println(roow.toString());
        roow.freePlaceAt(0, 1);        
        System.out.println(roow.toString());  
 
        // neue Lücke beginnt nach letzter Lücke und grenzt an
        System.out.println("\nneue Lücke von 20 bis 24 - neue Lücke beginnt nach letzter Lücke und grenzt an");
        roow = new PArrayRow(25, newcarList);
        System.out.println(roow.toString());
        roow.freePlaceAt(20, 4);        
        System.out.println(roow.toString());  

        // neue Lücke beginnt nach letzter Lücke und grenzt nicht an
        System.out.println("\nneue Lücke von 22 bis 25 - neue Lücke beginnt nach letzter Lücke und grenzt nicht an");
        roow = new PArrayRow(25, newcarList);
        System.out.println(roow.toString());
        roow.freePlaceAt(22, 3);        
        System.out.println(roow.toString());

        // neue Lücke beginnt vor erster Lücke und endet nach letzter Lücke
        System.out.println("\nneue Lücke von 1 bis 23 - neue Lücke beginnt vor erster Lücke und endet nach letzter Lücke");
        roow = new PArrayRow(25, newcarList);
        System.out.println(roow.toString());
        roow.freePlaceAt(1, 22);        
        System.out.println(roow.toString());
        
        
        
        
        // Test mit Lücke Länge 0 nicht notwendig, weil es im Konstruktor von Gap verworfen wird
//        // neue Lücke mit Länge 0
//        System.out.println("\nneue Lücke mit Länge 0 (bewusste Fehlersituation)");
//        roow = new PArrayRow(25, newcarList);
//        System.out.println(roow.toString());
//        roow.freePlaceAt(16, 0);        
//        System.out.println(roow.toString());         
        
        
        
        PArrayRow anotherRow;
        anotherRow = new PArrayRow(30, new int[][]{{24, 6}});
//        System.out.println(anotherRow.toString());
        anotherRow.freePlaceAt(22, 8);
//        System.out.println(anotherRow.toString());
//        assert anotherRow.isEmpty();
        
        
        
    }

}
