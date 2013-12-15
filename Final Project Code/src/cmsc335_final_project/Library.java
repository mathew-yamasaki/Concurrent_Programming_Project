/******************************************************************************
 *    Student: Mathew Yamasaki
 *       Date: Tuesday, May 15, 2012
 *      Class: Object Oriented and Concurrent Programming (CMSC 335)
 * Instructor: Dr. Mihaela Dinsoreanu
 *        JVM: 1.6
 * 
 * Class Description: This class creates one ArrayList for Book objects and one
 * for Author objects.  Methods are included to search each ArrayList by book
 * index, title, genre, and author index.  Map data structure are implemented
 * to support efficient searching by book and author indices.
 ******************************************************************************/
package cmsc335_final_project;

import java.util.*;
import javax.swing.JTextArea;

public class Library {
    
    // Maps to hold index keys and author/book names
    static Map<Object, String> authorMap = new HashMap<Object, String>();
    static Map<Object, String> bookMap = new HashMap<Object, String>();
    
    // variable to return output to NewJFrame
    private String output; 
    
    // create ArrayList to hold Book and Author objects
    static ArrayList<Book> books = new ArrayList<Book>();
    static ArrayList<Author> authors = new ArrayList<Author>();
    
    /** Add Book object to Library ArrayList */
    public void addBookToLibrary(Book b) {
        books.add(b);
    }
     
    /** Add Author object to Library ArrayList */
    public  void addAuthorToLibrary(Author a) {
        authors.add(a);
    }
    
    /** Add author to map */
    public void addAuthorToMap(Author a) {
        authorMap.put(a.getIndex(), a.getAuthorName());
    }
    
    /** search map for author index and return name if found */
    public String authorMapIterator(Map m, int i) {
        authorMap = m;
        Set s = m.entrySet();
        Iterator it = s.iterator();

        while (it.hasNext()) {
            Map.Entry e = (Map.Entry)it.next();
            int key = (Integer)e.getKey();
            String value = (String)e.getValue(); 
            //System.out.println(key);
            if (i == key) {
                output = value;
                break;
            } 
            else {
                output = "Author not found";
            }
        }
        return output;
    } // end method authorMapIterator
    
    /** add book to bookMap Map - Called in the DataScanner class */
    public void addBookToMap(Book b) {
        bookMap.put(b.getbookIndex(), b.getbookTitle());
    }
    
    /** iterate through book map and return book title from index */
    public String bookMapIterator(Map m, int i) {
        bookMap = m;
        Set s = m.entrySet();
        Iterator it = s.iterator();

        while (it.hasNext()) {
            Map.Entry e = (Map.Entry)it.next();
            int key = (Integer)e.getKey();
            String value = (String)e.getValue(); 
            //System.out.println(key);
            if (i == key) {
                output = value;
                break;
            } 
            else {
                output = "Book not found";
            }
        }
        return output;
    } // end method bookMapIterator

    /** search Book ArrayList by genre and return titles */
    public void getTitleFromGenre(ArrayList<Book> b, String g, JTextArea t) {
        t.setText("");
        boolean exists = false;
        for (Book c : b) {
            if (c.getGenre().equalsIgnoreCase(g)) {
                exists = true;
                t.append(c.getbookTitle() + "\n");
            } 
        }
        if (!exists) {
            t.append("Genre not found");
        }
    }
    
    /** search by book index and return title */
    public String getTitleFromIndex(ArrayList<Book> b, int i) {
        for (Book c : b) { 
            if (i == c.getbookIndex()) {
                output = c.getbookTitle();
                break;
            } // end if
            else
                output = "Title not found";
        } // end for
        return output;
    } // end method
    
    /** Search Library by book title and return tile, author, and price */
    public String getInfoFromTitle(ArrayList<Book> b, ArrayList<Author> a, String t) {
        
        String n = null; // book title
        String m = null; // author name
        double p = 0;    // price
        
        for (Book c : b) { 
                if (t.equalsIgnoreCase(c.getbookTitle())) {
                    n = c.getbookTitle();
                    p = c.getPrice();
                    
                    for (Author e : a) {
                        if (c.getAuthorIndex() == e.getIndex())
                        m = e.getAuthorName();
                    } // end nested for
                    output = "    Title: " + n +
                             " \nAuthor: " + m +
                             "\n  Price: $" + p;
                    break;
                } // end if
                else
                    output = "Book not found";
            } // end for
        return output;
    } // end method
            
    /** Search Book ArrayList by genre and returns book titles */
    public String getBooksByGenre(ArrayList<Book> b, String g) {
        
        for (Book c : b) {
            if (c.getGenre().equalsIgnoreCase(g)) {
                output = c.getbookTitle();
                break;
            }
            else {
                output = "Genre " + g + " not found";
            }
        }
        return output;
    } // end method
    
    /** Search for author by index and return info */
    public String findAuthorByIndex(ArrayList<Author> a, ArrayList<Book> b, int index) {
        
      String n = null; // author name
      String m = null; // book written
        
        for (Author e : a) {
            if (e.getIndex() == index) {
                n = e.getAuthorName();
            }
            for (Book q : b) {
                if (q.getAuthorIndex() == index) {
                    m = q.getbookTitle();
                    output = "         Author: " + n + 
                             "\nBooks written: " + m + "\n";
                break;
                } // end if
                else 
                    output = "Author not found";
            } // end nested for
        } // end for
            return output;
    } // end method
    
    /**  method used in the NewJFrame class to display sorted results */ 
    public String printSortedBookList(ArrayList<Book> b, JTextArea t) {
        // clear text area
        t.setText("");

        // set tab size
        t.setTabSize(12);
        
        // add header
        t.append("Index\tTitle\tGenre\tPrice\n");
        
        for (Book c : b) {
            output = c.getbookIndex() + "\t" + c.getbookTitle() + "\t" + c.getGenre() + "\t$" + c.getPrice() + "\n";
            t.append(output);
        }
        return output;
    }
} // end class Library