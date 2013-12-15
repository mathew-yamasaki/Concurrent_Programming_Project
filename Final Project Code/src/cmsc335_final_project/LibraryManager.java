/******************************************************************************
 *    Student: Mathew Yamasaki
 *       Date: Tuesday, May 15, 2012
 *      Class: Object Oriented and Concurrent Programming (CMSC 335)
 * Instructor: Dr. Mihaela Dinsoreanu
 *        JVM: 1.6
 * 
 * Class Description: This class contains inner classes that define methods
 * to create a library queue and add remove Book objects to and from the queue.
 ******************************************************************************/

package cmsc335_final_project;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import javax.swing.JOptionPane;

public class LibraryManager {
    
    // create library queue object
    static LibraryQueue libraryQueue = new LibraryQueue();

    /** return book to library */
    public class ReturnTask implements Runnable {
        
        // varaible declarations
        private String _returnTitle;
        private Thread returnBlinker;
        
        // get return title from GUI input
        public void getReturnTitle(String title) {
            _returnTitle = title;
        }
        
        // thread start method
        public void start() {
            returnBlinker = new Thread(this);
            returnBlinker.start();
        }
        
        // thread stop method
        public synchronized void stop() {
            returnBlinker = null;
            notify();
        }
        
        // thread run method
        public void run() {
            try {
                libraryQueue.returnBook(_returnTitle);
                System.out.println("returning book: " + _returnTitle);
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    } // end class ReturnTask
    
    /** task for borrowing a book from the library */
    public class BorrowTask implements Runnable {

        //  variable declaraitons
        private String _borrowTitle;
        private Thread blinker;
        
        // get book title from GUI input
        public void getBorrowTitle(String title) {
            _borrowTitle = title;
        }
        
        // thread start method
        public void start() {
            blinker = new Thread(this);
            blinker.start();
        }
        
        // thread stop method
        public synchronized void stop() {
            blinker = null;
            notify();
        }
        
        // thread run method
        public void run() {
            try {
                libraryQueue.borrowBook(_borrowTitle);
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    } // end class BorrowTask
    
    /*
     * The following class populates the library book queue with the contents of
     * the text file database.  Methods to borrow books from the queue and return
     * books to the queue are also defined.
     */
    static class LibraryQueue {
        
        // hold library database
        private static LinkedList<Book> bookQueue = new LinkedList<Book>();

        // create a new lock
        private static ReentrantLock lock = new ReentrantLock();
        
        // create conditions
        private static Condition notEmpty = lock.newCondition();
        private static Condition notFull = lock.newCondition();
        
        private int booksInQueue;
        static int _availableToBorrow;
        static Book _borrowBook;
        private static String _title;
        
        /** populate queue */
        public static void LibraryQueue(Book b) {
            for (int i = 0; i < b.getCopiesAvailable(); i++) {
                bookQueue.add(b);
            }
        }
        
        /** return a book */
        public void returnBook(String title) {

            // initialize variables
            Book b = null;
            _title = title;
 
            // get number of titles available in the queue
            for (int i = 0; i < bookQueue.size(); i++) {
                if (_title.equalsIgnoreCase(bookQueue.get(i).getbookTitle()))
                    booksInQueue++;
                    //System.out.println(title);
            }
            
            lock.lock();
            try {
                System.out.println(booksInQueue);
                if (_availableToBorrow > booksInQueue) {
                    System.out.println("queue is full");
                    notFull.await();
                }
                
                for (int i = 0; i < bookQueue.size(); i++) {
                    if (_title.equalsIgnoreCase(bookQueue.get(i).getbookTitle()) &&
                            _availableToBorrow <= bookQueue.get(i).getCopiesAvailable()) {
                        b = bookQueue.get(i);
                        bookQueue.offer(b); // add object to queue
                        notEmpty.signal();
                        break;
                }                        
            } 
            }catch (InterruptedException ex) {
                ex.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
        
        /** get number of titles currently in the queue */
        public void getCopiesInQueue(String title) {  
            
            System.out.println("queue size from copAv: " + bookQueue.size());
            
            for (int i = 0; i < bookQueue.size(); i++) {
                if (title.equalsIgnoreCase(bookQueue.get(i).getbookTitle()))
                    _availableToBorrow++;
            }
        }

        /** borrow a book */
        public Book borrowBook(String title) {

            // initialize title
            _title = title;
            
            // get number of titles in queue
            getCopiesInQueue(title); 

            try {
                lock.lock();

                System.out.println("copies of " + _title + " in queue: " +  _availableToBorrow);
                
                if (_availableToBorrow == 0 || bookQueue.isEmpty()) {
                    System.out.println("not available");
                    JOptionPane.showMessageDialog(null, _title  + 
                                " is not avaiable to borrow", "Not Available", 
                                JOptionPane.INFORMATION_MESSAGE);
                    notEmpty.await();
                }
                
                for (int i = 0; i < bookQueue.size(); i++) {
                    if (_title.equalsIgnoreCase(bookQueue.get(i).getbookTitle()) && 
                            _availableToBorrow >= bookQueue.get(i).getCopiesAvailable()) {
 
                        _borrowBook = bookQueue.remove(i); // remove book from queue
                        _availableToBorrow--;
                        
                        System.out.println("book object removed: " + _borrowBook);
                        System.out.println("AV TO BOR: " + _availableToBorrow);
                        
                        notFull.signal(); // signal not full condition
                        break;
                    }
                   
                }
                if (lock.hasWaiters(notEmpty)) {
                    notEmpty.signal();
                }
            } catch (InterruptedException ex) {
                    ex.printStackTrace();
            } finally {
                lock.unlock();
                return _borrowBook;
            }
        }
    }
} 