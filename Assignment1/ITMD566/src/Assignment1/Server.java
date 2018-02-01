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
      public static void main(String[] args) throws IOException {
    		//1 create serversocket object
		   ServerSocket ss = new ServerSocket(8888);
			/*2 invoke accept method and wait for client connection
			 * in order to count quantities so i use thread
			*/
			System.out.println("*****The server is about to start waiting for client connections*******");
			while(true) {
			Socket s=ss.accept();
			i++;
			new Thread(new Task(s)).start();
			}
			
    }
}
