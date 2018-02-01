package Assignment1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;
/**
 * 
 * @author TongWu A20410395 Group11
 *
 */
public class Client1 {
	 public static void main(String[] args) {
     	try {
				Socket s = new Socket("localhost", 8888);
				BufferedWriter request = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
				BufferedReader respond = new BufferedReader(new InputStreamReader(s.getInputStream()));
				String back = null;
				StringBuffer sb = new StringBuffer ();
				Scanner sc = new Scanner(System.in);
				String info=null;
				int index;
				while((info=sc.nextLine())!=null) {
					request.write(info);
					request.write("end");
					request.newLine();
					request.flush();
					if(info.equals("exit")) {
						break;
					}
					while((back=respond.readLine())!=null) {
						if((index=back.indexOf("end"))!=-1){
							sb.append(back.substring(0, index));
							back=sb.toString();
							sb.delete(0, index);
							System.out.println("i am client£¬server says:"+back);
							break;
							
						}
						}
					}
				s.shutdownOutput();
                s.shutdownInput();
				request.close();
				respond.close();
				s.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}

