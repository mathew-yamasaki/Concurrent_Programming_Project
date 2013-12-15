/******************************************************************************
 *    Student: Mathew Yamasaki
 *       Date: Tuesday, May 15, 2012
 *      Class: Object Oriented and Concurrent Programming (CMSC 335)
 * Instructor: Dr. Mihaela Dinsoreanu
 *        JVM: 1.6
 * 
 * Class Description: This class creates a new Book object from a parsed line of 
 * text.
 ******************************************************************************/

package cmsc335_final_project;
import java.util.Comparator;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

public class Book implements Comparable<Book>{

    // variable declarations
    private static final int DEFAULT_NUMBER_OF_COPIES = 2;
    private int _bookIndex;
    private String _bookTitle;
    private String _genre;
    private double _price;
    private int _authorIndex;
    private static int _copiesAvailable;
    static ArrayBlockingQueue<Integer> q;// = new ArrayBlockingQueue<Integer>(0);
    
    /** No-args constructor */
    public Book() {    
    }
    
    /** Constructor */
    public Book(int index, String title, String genre, double price, 
            int authorIndex, int copiesAvailable) {
        _bookIndex = index;
        _bookTitle = title;
        _genre = genre;
        _price = price;
        _authorIndex = authorIndex;
        _copiesAvailable = copiesAvailable;
    }
    
    public Book(String title) {
        _bookTitle = title;
    }
     
    /** create a new Book object */
    public static Book newBook(String line) {
        
        
        Book b = new Book();
        
        // create new Scanner with : and whitespaces as delimiters
        Scanner sc = new Scanner(line).useDelimiter("\\s*:\\s*"); 
        
        sc.next(); // skip first token
       
        b._bookIndex = sc.nextInt();      
        b._bookTitle = sc.next();
        b._genre = sc.next();
        b._price = sc.nextDouble();
        b._authorIndex = sc.nextInt();
        
        // set default number of copies available if not specified in file
        if (!sc.hasNext()) {
            //q = new ArrayBlockingQueue<Integer>(DEFAULT_NUMBER_OF_COPIES);
            b._copiesAvailable = DEFAULT_NUMBER_OF_COPIES;
        } else {
            //q = new ArrayBlockingQueue<Integer>(sc.nextInt());
            b._copiesAvailable = sc.nextInt();
        }

        // create object
        return b = new Book(b._bookIndex, b._bookTitle, b._genre, b._price, 
                b._authorIndex, b._copiesAvailable);
    }
    
    @Override
    public String toString() {
        return "Book index: " + _bookIndex + ":" + 
               "Title: " + _bookTitle + ":" +
               "Genre: " + _genre + ":" +
               "Price: " + _price + ":" +
               "Author: " + _authorIndex;
    }
    
    /** Get book index number */
    public int getbookIndex() {
        return _bookIndex;
    }
     /** Get book title */
    public String getbookTitle() {
        return _bookTitle;
    }
    
    /** Get book genre */
    public String getGenre() {
        return _genre;
    }
    
    /** Get book price */
    public double getPrice() {
        return _price;
    }
    
    /** Get author index number */
    public int getAuthorIndex() {
        return _authorIndex;
    }
    
    /** Get copies available */
    public int getCopiesAvailable() {
        return _copiesAvailable;
    }

    /*****************************Comparators**********************************/
    
    /** method implements a Comparator to sort book indices in descending order */
    public static Comparator<Book> ascendingBookIndexComparator = new Comparator<Book>() {
        @Override
        public int compare(Book b1, Book b2) {
            int bookIdx1 = b1.getbookIndex();
            int bookIdx2 = b2.getbookIndex();
            
            if (bookIdx1 < bookIdx2)
                return -1;
            if (bookIdx1 > bookIdx2)
                return 1;
            else
                return 0;
        }
    };
    
    /** method implements a Comparator to sort book indices in ascending order */
    public static Comparator<Book> descendingBookIndexComparator = new Comparator<Book>() {
        @Override
        public int compare(Book b1, Book b2) {
            int bookIdx1 = b1.getbookIndex();
            int bookIdx2 = b2.getbookIndex();
            
            if (bookIdx1 > bookIdx2)
                return -1;
            if (bookIdx1 < bookIdx2)
                return 1;
            else
                return 0;
        }
    };
    
    /** method implements a Comparator to sort book titles in ascending order */
    public static Comparator<Book> ascendingBookTitleComparator = new Comparator<Book>() {
        @Override
        public int compare(Book b1, Book b2) {
            String bookTitle1 = b1.getbookTitle();
            String bookTitle2 = b2.getbookTitle();
            return bookTitle1.compareTo(bookTitle2);
        }
    };
    
    /** method implements a Comparator to sort book titles in descending order */
    public static Comparator<Book> descendingBookTitleComparator = new Comparator<Book>() {
        @Override
        public int compare(Book b1, Book b2) {
            String bookTitle1 = b1.getbookTitle();
            String bookTitle2 = b2.getbookTitle();
            return bookTitle2.compareTo(bookTitle1);
        }
    };
    
    /** method implements a Comparator to sort book genres in ascending order */
    public static Comparator<Book> ascendingBookGenreComparator = new Comparator<Book>() {
        
        @Override
        public int compare(Book b1, Book b2) {
            String bookGenre1 = b1.getGenre();
            String bookGenre2 = b2.getGenre();
            return bookGenre1.compareTo(bookGenre2);
        }
    };
    
    /** method implements a Comparator to sort book genres in ascending order */
    public static Comparator<Book> descendingBookGenreComparator = new Comparator<Book>() {
        
        @Override
        public int compare(Book b1, Book b2) {
            String bookGenre1 = b1.getGenre();
            String bookGenre2 = b2.getGenre();
            return bookGenre2.compareTo(bookGenre1);
        }
    };
    
    /** method implements a Comparator to sort book prices in descending order */
    public static Comparator<Book> ascendingBookPriceComparator = new Comparator<Book>() {
        @Override
        public int compare(Book b1, Book b2) {
            double book1Price = b1.getPrice();
            double book2Price = b2.getPrice();
            
            if (book1Price < book2Price)
                return -1;
            if (book1Price > book2Price)
                return 1;
            else
                return 0;
        }
    };
    
    /** method implements a Comparator to sort book prices in descending order */
    public static Comparator<Book> descendingbBookPriceComparator = new Comparator<Book>() {
        @Override
        public int compare(Book b1, Book b2) {
            double book1Price = b1.getPrice();
            double book2Price = b2.getPrice();
            
            if (book1Price > book2Price)
                return -1;
            if (book1Price < book2Price)
                return 1;
            else
                return 0;
        }
    };
    
    @Override
    public int compareTo(Book o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
} // end class Book
