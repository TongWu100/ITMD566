package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBDao {
	static String sqlStr = "jdbc:mysql://localhost/CarService";  
    static String rootName = "root";
    static String rootPwd = "Tongky100%"; 
    static Connection connection;
	    public static Connection getConnection(){
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            connection = DriverManager.getConnection(sqlStr,rootName,rootPwd);
	        } catch (Exception e) {
	            System.out.println("connection error");
	            e.printStackTrace();
	        }
	        return connection;
	    }
	    public  static void closeConnection(Connection connection){

	                    if(connection != null){
	                        try {
	                            connection.close(); // 关闭数据库连接
	                        } catch (SQLException e) {
	                            e.printStackTrace();
	                        }
	                    }
	                }
	    }



