package Control;

import java.util.ArrayList;

import Model.Car;
import Model.Customer;
import Model.CustomerAccount;
import Model.User;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 ArrayList<User> user= new ArrayList<User>(); 
		 ArrayList<Customer> customer=new ArrayList<Customer>();
		 ArrayList<Car> car=new ArrayList<Car>();
		 ArrayList<CustomerAccount> cusa=new ArrayList<CustomerAccount>();
	        user = ParseUser.getParseUser(); 
	        customer= ParseCustomer.getParseCustomer();
	        car=ParseCar.getParseCar();
	        cusa=ParseCustomerAccount.getParseCustomerAccount();
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
	        for(int i=0;i<car.size();i++) {
	        	if(car.get(i)!=null) {
	        		Connector.writeToMysql(car.get(i));
	        	}
	        
	       
	}
	        for(int i=0;i<car.size();i++) {
	        	if(cusa.get(i)!=null) {
	        		Connector.writeToMysql(cusa.get(i));
	        	}
	        
	       
	}

}
}
