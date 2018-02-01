package Assignment1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.regex.Pattern;
/**
 * 
 * @author TongWu A20410395 Group11
 *
 */
  public class Task implements Runnable {
	     private Socket s;
         Pattern p = Pattern.compile("\\d\\*\\d");
         public Task(Socket socket) {  
         this.s = socket;  
     }    
     public void run() {  
        try {  
           handleSocket();  
        } catch (Exception e) {  
           e.printStackTrace();  
        }  
     }  
    public void handleSocket() throws Exception {
	         BufferedReader request = new BufferedReader(new InputStreamReader(s.getInputStream()));
	         BufferedWriter respond = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
	         String info = null;
	         StringBuilder sb = new StringBuilder();
	         int index;
	/*i can imitate intercom and just print the info's content before "end" 
	  by using StringBuffer and String's substring method such as the model like : "copy that" */
	        while((info=request.readLine())!=null) {
		      if((index=info.indexOf("end"))!=-1) {
			        sb.append(info.substring(0, index));
			        info = sb.toString();
			        sb.delete(0, index);
			System.out.println("i am server£¬client says:"+info);			
		}
		      if(info.equals("HelloServer")) {
			     respond.write("515OK");
			     respond.write("end");
			     respond.newLine();
		 	     respond.flush();
		       }
		      // I can calculate the multiplication of any two tens by using regex 
		      else if(p.matcher(info).matches()){
			     String [] s1 = info.split("\\*");
			     int result = 0;
			     result= (Integer.parseInt(s1[0]))*(Integer.parseInt(s1[1]));
			     respond.write(info+"="+String.valueOf(result));
			     respond.write("end");
			     respond.newLine();
			     respond.flush();
		       }else if(info.equals("count")) {
			     respond.write(String.valueOf(Server.i));
			     respond.write("end");
			     respond.newLine();
			     respond.flush();
		      }else if(info.equals("exit")){
			    respond.write("exit");
			    respond.write("end");
			    respond.newLine();
			    respond.flush();
		}

	}
	             s.shutdownInput();
	             s.shutdownOutput();
	             respond.close();
	             request.close();
	             s.close();
   } 
}

  	

