/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cmsc335_final_project;

import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Max
 */
public class LibraryClient {
 
    public static void main(String[] args) {
        new LibraryClient();
    }
    
    public LibraryClient() {
        try {
            Socket socket = new Socket("localhost", 8000);
            
        } catch (IOException e) {
            System.err.println(e);
        }
    }

}
