/******************************************************************************
 *    Student: Mathew Yamasaki
 *       Date: Tuesday, May 15, 2012
 *      Class: Object Oriented and Concurrent Programming (CMSC 335)
 * Instructor: Dr. Mihaela Dinsoreanu
 *        JVM: 1.6
 * 
 * Class Description: Creates a table whose data fields are populated by vectors.
 ******************************************************************************/

package cmsc335_final_project;

import java.awt.BorderLayout;
import java.util.Comparator;
import java.util.Vector;
import javax.swing.*;

public class JTableLibraryView extends JFrame {

    // variable declarations
    static final Vector rowData = new Vector();
    private final Vector cellData = new Vector();
    private static String aName;
    

    public JTableLibraryView() {
        super("Library Inventory");
        setSize(600, 300);
        setLocationRelativeTo(null);     
    }
    
     /** add author to table //to implement in later versions// */
    public void addAuthor(Author a) {
        aName = a.getAuthorName();
        //return aName;
    }

    /** adds information from object data fields to the data vector */
    public void addToTableVector(Book b) {

        cellData.add(b.getbookIndex());
        cellData.add(b.getbookTitle());            
        cellData.add(aName);
        cellData.add(b.getGenre());
        cellData.add(b.getPrice());    
        cellData.add(b.getCopiesAvailable());
        //System.out.println(cellData.toString());
        rowData.addElement(cellData);
        }
    
    /** inner class to sort book index table column */
    class IndexComparator implements Comparator {

        @Override
        public int compare(Object o1, Object o2) {
            Integer index1 = (Integer)(o1);
            Integer index2 = (Integer)(o2);
            return index1.compareTo(index2);
        }
    }
    
    /** inner class to sort prices */
    class PriceComparator implements Comparator {
        @Override
        public int compare(Object o1, Object o2) {
            Double price1 = (Double)(o1);
            Double price2 = (Double)(o2);
            return price1.compareTo(price2);
        }
    }
     
   

}