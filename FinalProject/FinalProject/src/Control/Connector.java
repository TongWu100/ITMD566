package Control;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Connection;

import Model.Car;
import Model.Customer;
import Model.CustomerAccount;
import Model.User;

public class Connector {
	static String sqlStr = "jdbc:mysql://localhost/CarService";  
    static String rootName = "root";
    static String rootPwd = "Tongky100%"; 
  
    public static void writeToMysql(User user) {  
        try {  
            Class.forName("com.mysql.jdbc.Driver");  
        } catch (ClassNotFoundException e) {  
            System.out.println("Unable to find driver class, load driver failed.");  
            e.printStackTrace();  
        }  
        Statement st = null;  
        Connection con  =null;  
        try {  
            con = (Connection)DriverManager.getConnection(sqlStr,rootName,rootPwd);  
            String UserID= user.getId();
            String Password = user.getPassword();
            String FullName = user.getFullname();
            String Email = user.getEmail();
            String StreetAddress = user.getStreetaddress();
            String City = user.getCity();
            String State = user.getState();
            String sql = "insert into user(UserID,FullName,PassWord,Email,State,City,StreetAddress) values(\""+UserID+"\",\""+FullName+"\",\""+Password+"\",\""+Email+"\",\""+State+"\",\""+City+"\",\""+StreetAddress+"\")";  
            System.out.println(sql);  
            st =  con.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException e) {  
            e.printStackTrace();  
        }finally{  
            try {  
                st.close();  
                con.close();  
            } catch (SQLException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
    public static void writeToMysql(Customer customer) {
    	 try {  
             Class.forName("com.mysql.jdbc.Driver");  
         } catch (ClassNotFoundException e) {  
             System.out.println("Unable to find driver class, load driver failed.");  
             e.printStackTrace();  
         }    
         Statement st = null;  
         Connection con  =null;  
         try {  
             con = (Connection)DriverManager.getConnection(sqlStr,rootName,rootPwd);   
             String UserID= customer.getId();
             String Password = customer.getPassword();
             String FullName = customer.getFullname();
             String IsAdmin = customer.getIsadmin();
             String Email = customer.getEmail();
             String StreetAddress = customer.getStreetaddress();
             String City = customer.getCity();
             String State = customer.getState();
             double Balance = customer.getBalance();
             String sql = "insert into Customer(UserID,FullName,FirstName,LastName,Password,IsAdmin,Email,Sex,Balance) values(\""+UserID+"\",\""+Password+"\",\""+FullName+"\",\""+IsAdmin+"\",\""+Email+"\",\""+StreetAddress+"\",\""+City+"\",\""+State+"\",\""+Balance+"\")";  
             System.out.println(sql);  
             st =  con.createStatement(); 
             st.executeUpdate(sql); 
         } catch (SQLException e) {  
             e.printStackTrace();  
         }finally{  
             try {  
                 st.close();  
                 con.close();  
             } catch (SQLException e) {  
                 e.printStackTrace();  
             }  
         }  
    }
    public static void writeToMysql(Car car) {
   	 try {  
            Class.forName("com.mysql.jdbc.Driver");  
        } catch (ClassNotFoundException e) {  
            System.out.println("Unable to find driver class, load driver failed.");  
            e.printStackTrace();  
        }    
        Statement st = null;  
        Connection con  =null;  
        try {  
            con = (Connection)DriverManager.getConnection(sqlStr,rootName,rootPwd);   
            String CarID= car.getId();  
            String CarName = car.getName();
            String CarType = car.getType();
            double BuyPrice = car.getBuyPrice();
            double RentPrice = car.getRentPrice();
            String sql = "insert into Car(CarID,CarName,CarType,BuyPrice,RentPrice) values(\""+CarID+"\",\""+CarName+"\",\""+CarType+"\",\""+BuyPrice+"\",\""+RentPrice+"\")";  
            System.out.println(sql);  
            st =  con.createStatement(); 
            st.executeUpdate(sql); 
        } catch (SQLException e) {  
            e.printStackTrace();  
        }finally{  
            try {  
                st.close();  
                con.close();  
            } catch (SQLException e) {  
                e.printStackTrace();  
            }  
        }  
   }
    public static void writeToMysql(CustomerAccount cusa) {
      	 try {  
               Class.forName("com.mysql.jdbc.Driver");  
           } catch (ClassNotFoundException e) {  
               System.out.println("Unable to find driver class, load driver failed.");  
               e.printStackTrace();  
           }    
           Statement st = null;  
           Connection con  =null;  
           try {  
               con = (Connection)DriverManager.getConnection(sqlStr,rootName,rootPwd);   
               String CustomerAccountID= cusa.getAccountid();  
               String UserID = cusa.getUserid();
               int RentCarNumber = cusa.getBuyCarNumber();
               int BuyCarNumber = cusa.getRentCarNumber();
               double Balance = cusa.getBalance();
               String sql = "insert into CustomerAccount(CustomerAccountID,UserID,RentCarNumber,BuyCarNumber,Balance) values(\""+CustomerAccountID+"\",\""+UserID+"\",\""+RentCarNumber+"\",\""+BuyCarNumber+"\",\""+Balance+"\")";  
               System.out.println(sql);  
               st =  con.createStatement(); 
               st.executeUpdate(sql); 
           } catch (SQLException e) {  
               e.printStackTrace();  
           }finally{  
               try {  
                   st.close();  
                   con.close();  
               } catch (SQLException e) {  
                   e.printStackTrace();  
               }  
           }  
      }
}
