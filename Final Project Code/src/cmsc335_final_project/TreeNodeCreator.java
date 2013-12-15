/******************************************************************************
 *    Student: Mathew Yamasaki
 *       Date: Sunday, April 29, 2012
 *      Class: Object Oriented and Concurrent Programming (CMSC 335)
 * Instructor: Dr. Mihaela Dinsoreanu
 *        JVM: 1.6
 * 
 * Class Description: Creates and adds nodes to a JTree using a multimap.
 * 
 * ArrayListMultiMap and Multimap are contained in the Google Collections Library 
 * Release 1.0 (FINAL - 20091230) downloaded from:
 * 
 * http://code.google.com/p/google-collections/wiki/Releases 
 ******************************************************************************/
package cmsc335_final_project;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import javax.swing.JFrame;
import javax.swing.tree.DefaultMutableTreeNode;


public class TreeNodeCreator extends JFrame{
    
    // create multimap to hold K = genre and V = book title
    static Multimap<String, String> multiMap = ArrayListMultimap.create();
    
    /** constructor */
    public TreeNodeCreator() {
        
        // set JFame properties
        super("Library Tree View");
        setSize(300, 300);
        setLocationRelativeTo(null);
    } // end constructor
    
    /** add genre and title to multimap */
    public void addToMultiMap(Book b) {
        multiMap.put(b.getGenre(), b.getbookTitle());
    } // end method
    
    /** add child node to top node */
    public void nodeAdder(DefaultMutableTreeNode top) {
        Set genreSet = multiMap.keySet(); // get key set
        Iterator keyIterator = genreSet.iterator(); // create iterator for set
        
        while (keyIterator.hasNext()) {
            String key = (String)keyIterator.next(); // cast to string
            DefaultMutableTreeNode genreNode = new DefaultMutableTreeNode(key);
            top.add(genreNode); // add key to parent node
            
            Collection values = (Collection)multiMap.get(key); // return values from key
            Iterator valuesIterator = values.iterator(); // create iterator
            
            while (valuesIterator.hasNext()) {
                DefaultMutableTreeNode bookNode = 
                        new DefaultMutableTreeNode(valuesIterator.next());
                genreNode.add(bookNode); // add value to node
                
            } // end while
        } // end while
    } // end method nodeAdder
} // end class TreeNodeCreator
