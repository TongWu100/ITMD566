package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDao {
    public void addUser(User user) {  
        Connection connection = null;  
        PreparedStatement psmt = null;  
        try {  
             connection = DBDao.getConnection();  
               
             String sql  ="insert into user(UserId,FullName,PassWord,Email,State,City,StreetAddress)values(?,?,?,?,?,?,?);";  
               
             psmt = (PreparedStatement) connection.prepareStatement(sql);  
               
             //运用实体对象进行参数赋值  
             psmt.setString(1, user.getId());  
             psmt.setString(2, user.getFullname());  
             psmt.setString(3,user.getPassword());  
             psmt.setString(4, user.getEmail());
             psmt.setString(5,user.getState());
             psmt.setString(6,user.getCity());
             psmt.setString(7,user.getStreetaddress());
             psmt.executeUpdate();         
        } catch (SQLException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }finally {  
             DBDao.closeConnection(connection);
}
}
    public ArrayList<User> selectUser() {
        Connection connection = null;  
        PreparedStatement psmt = null;
        ArrayList<User> list= new ArrayList<User>();
        try {  
             connection = DBDao.getConnection();  
             String sql  ="select * from user;"; 
             psmt = (PreparedStatement) connection.prepareStatement(sql);  
             ResultSet rs = psmt.executeQuery();
             while(rs.next()){
            	 User u = new User();
            	 u.setId(rs.getString(1));
            	 u.setFullname(rs.getString(2));
            	 u.setPassword(rs.getString(3));
            	 u.setEmail(rs.getString(4));
            	 u.setState(rs.getString(5));
            	 u.setCity(rs.getString(6));
            	 u.setStreetaddress(rs.getString(7));
            	 list.add(u);
             }
             
        } catch (SQLException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }finally {  
             DBDao.closeConnection(connection);
}  return list;
    }
}