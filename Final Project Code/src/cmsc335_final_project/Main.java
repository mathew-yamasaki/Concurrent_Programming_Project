/******************************************************************************
 *    Student: Mathew Yamasaki
 *       Date: Tuesday, May 15, 2012
 *      Class: Object Oriented and Concurrent Programming (CMSC 335)
 * Instructor: Dr. Mihaela Dinsoreanu
 *        JVM: 1.6
 * 
 * Class Description: Calls DataScanner to read a text file and creates the GUI.
 ******************************************************************************/

package cmsc335_final_project;

public class Main {
   
    public static void main(String[] args) {
        
        // Call method to read data file
        DataScanner.readFile("test2.txt");
        
        //  Create new NewJFrame GUI
        LibraryGUI frame = new LibraryGUI();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

      }
} // end class Main
