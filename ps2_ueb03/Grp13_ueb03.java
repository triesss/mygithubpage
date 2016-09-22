/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grp13_ueb03;

/**
 *
 * @author Paul
 */
public class Grp13_ueb03 {

    // Testmethoden aus der Aufgabenstellung
    /*
     * In der Hauptklasse sollen diverse Testmethoden erstellt werden, die in
     * der main() aufgerufen werden. Alle Testroutinen seien vom Typ void. Die
     * Testroutine ist immer analog zu ihrer getesteten Methode benannt. Alle in
     * ihr enthaltenen Testaufrufe sind möglichst unabhängig, bauen also nicht
     * auf den mit einem vorigen Test erzeugten Zustand auf.
     *
     * Es wird davon ausgegangen, dass die in der Vorlesung vorgestellte Methode
     * appendElement() funktioniert. Unter ihrer Verwendung werden alle anderen
     * Methoden getestet.
     */
    /**
     * erzeugt eine Liste mit 5 Testwerten von 10 bis 50
     *
     * @return eine Liste mit 5 Testwerten von 10 bis 50
     */
    private static MyList createTestList() {
        MyList list = new MyList();
        for (int i = 0; i < 5; i++) {
            list.appendElement((i + 1) * 10);
        }
        return list;
    }

    private static void testSize() {
        System.out.println("teste size()");
        // leere Liste
        MyList list = new MyList();
        System.out.println("erwarte: 0");
        System.out.println("bekomme: " + list.size());

        // volle Liste
        list = createTestList();
        System.out.println("erwarte: 5");
        System.out.println("bekomme: " + list.size());
        System.out.println("");
    }

    private static void testSum() {
        System.out.println("teste sum()");

        // leere Liste
        MyList list = new MyList();
        System.out.println("erwarte: 0");
        System.out.println("bekomme: " + list.sum());

        // volle Liste
        list = createTestList();
        System.out.println("erwarte: 150");
        System.out.println("bekomme: " + list.sum());
        System.out.println("");
    }

    private static void testIsSorted() {
        System.out.println("teste isSorted()");

        // leere Liste
        MyList list = new MyList();
        System.out.println("erwarte: true");
        System.out.println("bekomme: " + list.isSorted());

        // sortierte Liste
        list = createTestList();
        System.out.println("erwarte: true");
        System.out.println("bekomme: " + list.isSorted());

        // unsortierte Liste
        list.appendElement(0);
        System.out.println("erwarte: false");
        System.out.println("bekomme: " + list.isSorted());
        System.out.println("");
    }

    private static void testContainsValue() {
        System.out.println("teste containsValue()");

        MyList listE = new MyList();
        System.out.println("erwarte: false");
        System.out.println("bekomme: " + listE.containsValue(10));
        
        MyList list = createTestList();

        // wird erstes und letztes Element gefunden?
        System.out.println("erwarte: true");
        System.out.println("bekomme: " + list.containsValue(10));
        System.out.println("erwarte: true");
        System.out.println("bekomme: " + list.containsValue(50));

        // nicht enthaltene Elemente sollen nicht gefunden werden
        System.out.println("erwarte: false");
        System.out.println("bekomme: " + list.containsValue(0));
        System.out.println("erwarte: false");
        System.out.println("bekomme: " + list.containsValue(60));

        System.out.println("");
    }

    private static void testShowValues() {
        System.out.println("teste showValues()");

        // leere Liste
        MyList list = new MyList();
        System.out.println("erwarte: {}");
        System.out.println("bekomme: " + list.showValues());

        // volle Liste
        list = createTestList();
        System.out.println("erwarte: {10 20 30 40 50}");
        System.out.println("bekomme: " + list.showValues());
        System.out.println("");
    }

    private static void testGetValueAt() {
        System.out.println("teste getValueAt()");
        // am Anfang
        MyList list = createTestList();
        System.out.println("erwarte: 10");
        System.out.println("bekomme: " + list.getValueAt(0));

        // in der Mitte
        list = createTestList();
        System.out.println("erwarte: 30");
        System.out.println("bekomme: " + list.getValueAt(2));

        // am Ende
        list = createTestList();
        System.out.println("erwarte: 50");
        System.out.println("bekomme: " + list.getValueAt(4));

        System.out.println("");
    }

    private static void testInsertValueAt() {
        System.out.println("teste insertValueAt()");
        
        // todo Testfall für leere Liste
        MyList listE = new MyList();
        System.out.println("erwarte: {10}");
         listE.insertValueAt(10, 0);
        System.out.println("bekomme: " + listE.showValues());
        
        // am Ende einfügen
        MyList list = createTestList();
        list.insertValueAt(99, 5);
        System.out.println("erwarte: {10 20 30 40 50 99}");
        System.out.println("bekomme: " + list.showValues());

        // in der Mitte einfügen
        list = createTestList();
        list.insertValueAt(99, 3);
        System.out.println("erwarte: {10 20 30 99 40 50}");
        System.out.println("bekomme: " + list.showValues());

        // am Anfang einfügen
        list = createTestList();
        list.insertValueAt(99, 0);
        System.out.println("erwarte: {99 10 20 30 40 50}");
        System.out.println("bekomme: " + list.showValues());

        System.out.println("");
    }

    private static void testInsertValueAtFront() {
        System.out.println("teste insertValueAtFront()");

        // zu Beginn einer leeren Liste anfügen
        MyList list = new MyList();
        list.insertValueAtFront(99);
        System.out.println("erwarte: {99}");
        System.out.println("bekomme: " + list.showValues());

        // am Anfang einer bestehenden Liste einfügen
        list = createTestList();
        list.insertValueAtFront(99);
        System.out.println("erwarte: {99 10 20 30 40 50}");
        System.out.println("bekomme: " + list.showValues());
        System.out.println("Nun ist die Liste nicht mehr sortiert: " + !list.isSorted());
        System.out.println("");
    }

    private static void testInsertSortedIfUnique() {
        System.out.println("teste insertValueIfUnique()");

        // am Anfang enthaltenes Element darf nicht eingefügt werden
        MyList list = createTestList();
        list.insertSortedIfUnique(10);
        System.out.println("erwarte: {10 20 30 40 50}");
        System.out.println("bekomme: " + list.showValues());

        // am Ende enthaltenes Element darf nicht eingefügt werden
        list = createTestList();
        list.insertSortedIfUnique(50);
        System.out.println("erwarte: {10 20 30 40 50}");
        System.out.println("bekomme: " + list.showValues());

        // kleineres Element muss am Anfang eingefügt werden
        list = createTestList();
        list.insertSortedIfUnique(8);
        System.out.println("erwarte: {8 10 20 30 40 50}");
        System.out.println("bekomme: " + list.showValues());

        // mittleres Element muss in der Mitte eingefügt werden
        list = createTestList();
        list.insertSortedIfUnique(11);
        System.out.println("erwarte: {10 11 20 30 40 50}");
        System.out.println("bekomme: " + list.showValues());

        // größeres Element muss angehängt werden
        list = createTestList();
        list.insertSortedIfUnique(55);
        System.out.println("erwarte: {10 20 30 40 50 55}");
        System.out.println("bekomme: " + list.showValues());

        System.out.println("");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        testSize();
        testSum();
        testIsSorted();
        testContainsValue();
        testShowValues();
        testGetValueAt();
        testInsertValueAt();
        testInsertValueAtFront();
        testInsertSortedIfUnique();

    }
}
