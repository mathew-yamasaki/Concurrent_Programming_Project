/******************************************************************************
 *    Student: Mathew Yamasaki
 *       Date: Tuesday, May 15, 2012
 *      Class: Object Oriented and Concurrent Programming (CMSC 335)
 * Instructor: Dr. Mihaela Dinsoreanu
 *        JVM: 1.6
 * 
 * Class Description: This class creates a library server and displays the date
 * and time the server was started.  The number of clients accessing the server
 * is displayed as new threads are created.
 ******************************************************************************/
package cmsc335_final_project;

import java.awt.BorderLayout;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class LibraryServer extends JFrame {
    
    private JTextArea jta = new JTextArea();
    
    public static void main(String[] args) {
        new LibraryServer();
    }
    
    public LibraryServer() {
        setLayout(new BorderLayout());
        add(new JScrollPane(jta), BorderLayout.CENTER);
        
        setTitle("Library Server");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
        // read library database file
        DataScanner.readFile("test2.txt");
        
        try {
            ServerSocket serverSocket = new ServerSocket(8000);
            jta.append("Library server started at " + new Date() + '\n');

            int clientNum = 1;
            
            while (true) {
                Socket socket = serverSocket.accept();
                jta.append("Starting thread for client " + clientNum +
                        " at " + new Date() + '\n');
                HandleClient task = new HandleClient(socket);
                new Thread(task).start();
                clientNum++;
            }
            
        } catch (IOException e) {
            System.err.println(e);
        }
    }
    
    class HandleClient implements Runnable {
        private Socket socket;
        
        public HandleClient(Socket socket) {
            this.socket = socket;
        }
        
        public void run() {
            
            LibraryGUI frame = new LibraryGUI();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            
        }
    }
}
