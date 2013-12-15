/******************************************************************************
 *    Student: Mathew Yamasaki
 *       Date: Tuesday, May 15, 2012
 *      Class: Object Oriented and Concurrent Programming (CMSC 335)
 * Instructor: Dr. Mihaela Dinsoreanu
 *        JVM: 1.6
 * 
 * Class Description: Reads a text file and sends the string to the required 
 * class when a designated character is encountered.
 ******************************************************************************/

package cmsc335_final_project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JOptionPane;
 
public class DataScanner {
    public static void readFile(String fileName) {
        try {
            Scanner scanner = new Scanner(new File(fileName));
            
            scanner.useDelimiter(System.getProperty("line.separator")); 
 
            while (scanner.hasNextLine()) {
                parseLine(scanner.next());
            }
          scanner.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Input file not found", "Error",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
      } // end method readFile
    
    public static void parseLine(String line) {
        
        // Create new required objects
        Library library = new Library();
        JTableLibraryView jt = new JTableLibraryView();
        TreeNodeCreator tnc = new TreeNodeCreator();
        
        // Skip empty strings
        if (line.length() == 0)
            return;
        
        // Create new Scanner object
        Scanner lineScanner = new Scanner(line);
        
        // Assign next line to String
        String readLine = lineScanner.nextLine();

        String tr = readLine.trim();
        
            switch (tr.charAt(0)) {
                case 'A':
                    library.addAuthorToLibrary(Author.newAuthor(tr));
                    library.addAuthorToMap(Author.newAuthor(tr)); 
                    jt.addAuthor(Author.newAuthor(tr));
                    break;
                case 'B':
                    library.addBookToLibrary(Book.newBook(tr));
                    library.addBookToMap(Book.newBook(tr));
                    jt.addToTableVector(Book.newBook(tr));
                    LibraryManager.LibraryQueue.LibraryQueue(Book.newBook(tr));
                    
                    tnc.addToMultiMap(Book.newBook(tr));
                    break;
                default:
                    JOptionPane.showMessageDialog(null, 
                            "Improperly formatted data file" +
                            "\nPlease ensure files are in the following format:" +
                            "\n\nFor Author:" +
                            "\nA:index:name:street address:city:state:zip:phone" +
                            "\nFor Book:" +
                            "\nB:index:Title:Genre:price:index of Author",
                            "System Error", JOptionPane.ERROR_MESSAGE);
                    System.exit(0);
                    break;
            }// end switch
    } // end method 
} // end class
