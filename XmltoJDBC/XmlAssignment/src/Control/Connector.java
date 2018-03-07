package Control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import Model.Admin;
import Model.CarRental;
import Model.Customer;
import Model.PartService;
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
            String Mobile = user.getMobile();
            String Email = user.getEmail();
            String FullName = user.getFullname();
            String Password = user.getPassword();
            String sql = "insert into user(UserID,mobileN,Email,FullName,Password) values(\""+UserID+"\",\""+Mobile+"\",\""+Email+"\",\""+FullName+"\",\""+Password+"\")";
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
             String Email = customer.getEmail();
             String FullName = customer.getFullname();
             String Password = customer.getPassword();
             String StreetAddress = customer.getStreetaddress();
             String City = customer.getCity();
             String State = customer.getState();
             double Balance = customer.getBalance();
             String sql = "insert into customer(UserID,Email,FullName,Password,StreetAddress,City,State,Balance) values(\""+UserID+"\",\""+Email+"\",\""+FullName+"\",\""+Password+"\",\""+StreetAddress+"\",\""+City+"\",\""+State+"\",\""+Balance+"\")";
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
    public static void writeToMysql(CarRental car) {
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
            String CarType = car.getType();
            String DateRented = car.getDateRented();
            String DateReturned = car.getDateReturned();
            double DailyPrice = car.getRentCostDaily();
            String sql = "insert into carrental(carId,carType,dateRented,dateReturned,rentCostDaily) values(\""+CarID+"\",\""+CarType+"\",\""+DateRented+"\",\""+DateReturned+"\",\""+DailyPrice+"\")";
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
    public static void writeToMysql(Admin admin) {
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
               String AdminID= admin.getId();
               String FullName = admin.getFullname();
               String Password = admin.getPassword();
               String sql = "insert into admin(AdminID,FullName,Password) values(\""+AdminID+"\",\""+FullName+"\",\""+Password+"\")";
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
    public static void writeToMysql(PartService part) {
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
               String PartID = part.getId();
               String PartType = part.getType();
               double PartAmount = part.getAmount();
               double PartCost = part.getCost();
               String sql = "insert into partservices(PartId,PartType,PartAmount,Cost) values(\""+PartID+"\",\""+PartType+"\",\""+PartAmount+"\",\""+PartCost+"\")";
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
