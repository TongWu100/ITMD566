package Control;

import java.util.ArrayList;

import Model.Admin;
import Model.CarRental;
import Model.Customer;
import Model.PartService;
import Model.User;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 ArrayList<User> user= new ArrayList<User>(); 
		 ArrayList<Customer> customer=new ArrayList<Customer>();
		 ArrayList<Admin> admin=new ArrayList<Admin>();
		 ArrayList<CarRental> cr=new ArrayList<CarRental>();
		 ArrayList<PartService> ps=new ArrayList<PartService>();
	        user = ParseUser.getParseUser(); 
	        customer= ParseCustomer.getParseCustomer();
	        admin= ParseAdmin.getParseAdmin();
	        cr=ParseCar.getParseCarRental();
	        ps=ParsePart.getParseService();
	        
	        for(int i=0;i<user.size();i++)  
	        {             
	            if (user.get(i)!=null) {  
	            Connector.writeToMysql(user.get(i));  
	        } 
	   }
	        for(int i=0;i<customer.size();i++) {
	        	if(customer.get(i)!=null) {
		        	Connector.writeToMysql(customer.get(i));
		        }
	   }
	        for(int i=0;i<admin.size();i++) {
	        	if(admin.get(i)!=null) {
	        		Connector.writeToMysql(admin.get(i));
	        	}
	        
	       
	        }
	        for(int i=0;i<cr.size();i++) {
	        	if(cr.get(i)!=null) {
	        		Connector.writeToMysql(cr.get(i));
        	}	       
	}
	        for(int i=0;i<ps.size();i++) {
	        	if(ps.get(i)!=null) {
	        		Connector.writeToMysql(ps.get(i));
	        	}
	        
	       
	}
	}

}
