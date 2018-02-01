package Assignment1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * 
 * @author TongWu A20410395 Group11
 *
 */

public class Server {
	        static int i=0;
	        private static ServerSocket ss;
	        public Server(ServerSocket socket) {
	        	ss= socket;
	        }
	        public void connect() throws IOException {
	        	/*invoke accept method and wait for client connection
				  in order to count quantities so i use thread*/
	        	while(true) {
	    			Socket s=ss.accept();
	    			i++;
	    			new Thread(new Task(s)).start();   			
	    			}	
	        }
      public static void main(String[] args) throws IOException {
    		// create serversocket object
    	    Server s1 = new Server(new ServerSocket(8888)); 
			System.out.println("*****The server is about to start waiting for client connections*******");
			s1.connect();
			ss.close();
			
			
    }
}
