/******************************************************************************
 *    Student: Mathew Yamasaki
 *       Date: Sunday, April 29, 2012
 *      Class: Object Oriented and Concurrent Programming (CMSC 335)
 * Instructor: Dr. Mihaela Dinsoreanu
 *        JVM: 1.6
 * 
 * Class Description: This class creates a new Author object from a parsed line 
 * of text.
 ******************************************************************************/

package cmsc335_final_project;

import java.util.*;

public class Author {
    
    // variable declarations
    private int _index;
    private String _authorName;
    private String _address;
    private String _city;
    private String _state;
    private int _zipCode;
    private String _phoneNumber;
    
    /** No-args constructor */
    public Author() {
    }
    
    /** Constructor */
    public Author(int index, String name, String address, String city, 
            String state, int zipCode, String phoneNumber) {
        _index = index;
        _authorName = name;
        _address = address;
        _city = city;
        _state = state;
        _zipCode = zipCode;
        _phoneNumber = phoneNumber;
    }
    
    /** parse string of text and assign to variables */
    public static Author newAuthor(String line) {
        Author a = new Author();
        
        try {
            Scanner sc = new Scanner(line).useDelimiter("\\s*:\\s*");    // create new Scanner 
            sc.next(); // skip first token
            a._index = sc.nextInt();   
            a._authorName = sc.next();
            a._address = sc.next();
            a._city = sc.next();
            a._state = sc.next();
            a._zipCode = sc.nextInt();
            a._phoneNumber = sc.next();
        }
        catch (Exception e) {
            System.out.println("Improperly formatted data file");
            System.exit(0);
        }
        
        // create object
        return a = new Author(a._index, a._authorName, a._address, a._city, 
                a._state, a._zipCode, a._phoneNumber);
    }
    
    /** get contents of ArrayList */
    @Override
    public String toString() {
        return _index + ":" +
                _authorName + ":" + 
                _address + ":" + 
                _city + ":" +
                _state + ":" +
                _zipCode + ":" +
                _phoneNumber;
    }
    
    /** Get author index number */
    public int getIndex() {
        return _index;
    }
     /** Get author name */
    public String getAuthorName() {
        return _authorName;
    }
    
    /** Get author street address */
    public String getAddress() {
        return _address;
    }
    
    /** Get author city */
    public String getCity() {
        return _city;
    }
     /** Get author zip code */
    public int getZipCode() {
        return _zipCode;
    }
    
    /** Get author phone number */
    public String getPhoneNumber() {
        return _phoneNumber;
    }
} // end class Author
