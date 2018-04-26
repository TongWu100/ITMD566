package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import Model.User;

public class UserDao {
	public void addUser(User user) {  
        Connection connection = null;  
        PreparedStatement psmt = null;  
        try {  
             connection = DBDao.getConnection();  
               
             String sql  ="insert into user(UserName,PassWord,Email,State,City,StreetAddress,Role)values(?,?,?,?,?,?,?);";  
               
             psmt = (PreparedStatement) connection.prepareStatement(sql);  
 
             psmt.setString(1,user.getUsername());   
             psmt.setString(2,user.getPassword());  
             psmt.setString(3,user.getEmail());
             psmt.setString(4,user.getState());
             psmt.setString(5,user.getCity());
             psmt.setString(6,user.getStreetaddress());
             psmt.setString(7,"customer");
             psmt.executeUpdate();         
        } catch (SQLException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }finally {  
             DBDao.closeConnection(connection);
}
	}

    public User selectUser(String inputemail) {
        Connection connection = null;  
        PreparedStatement psmt = null;
        User u = new User();
        try {  
             connection = DBDao.getConnection();  
             String sql  ="select * from user where email="+"'"+inputemail+"'"; 
             psmt = (PreparedStatement) connection.prepareStatement(sql);  
             ResultSet rs = psmt.executeQuery();
             while(rs.next()){
            	 u.setUsername(rs.getString(1));
            	 u.setPassword(rs.getString(2));
            	 u.setEmail(rs.getString(3));
            	 u.setState(rs.getString(4));
            	 u.setCity(rs.getString(5));
            	 u.setStreetaddress(rs.getString(6));
            	 u.setRole(rs.getString(7));
             }
             
        } catch (SQLException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }finally {  
             DBDao.closeConnection(connection);
}  return u;
    }
}
