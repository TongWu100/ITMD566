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
            String FullName = user.getFullName();
            String FirstName = user.getFirstName();
            String LastName = user.getLastName();
            String Password = user.getPassword();
            String IsAdmin = user.isAdmin();
            String sql = "insert into user(UserID,FullName,FirstName,LastName,Password,IsAdmin) values(\""+UserID+"\",\""+FullName+"\",\""+FirstName+"\",\""+LastName+"\",\""+Password+"\",\""+IsAdmin+"\")";  
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
             String FullName = customer.getFullName();
             String FirstName = customer.getFirstName();
             String LastName = customer.getLastName();
             String Password = customer.getPassword();
             String IsAdmin = customer.isAdmin();
             String Email = customer.getEmail();
             String Sex = customer.getSex();
             double Balance = customer.getBalance();
             String sql = "insert into Customer(UserID,FullName,FirstName,LastName,Password,IsAdmin,Email,Sex,Balance) values(\""+UserID+"\",\""+FullName+"\",\""+FirstName+"\",\""+LastName+"\",\""+Password+"\",\""+IsAdmin+"\",\""+Email+"\",\""+Sex+"\",\""+Balance+"\")";  
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
