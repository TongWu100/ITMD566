package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.Admin;

public class AdminDao {
	public Admin selectAdmin(String inputemail) {
        Connection connection = null;  
        PreparedStatement psmt = null;
        Admin a= new Admin();
        try {  
             connection = DBDao.getConnection();  
             String sql  ="select * from admin where email="+"'"+inputemail+"'"; 
             psmt = (PreparedStatement) connection.prepareStatement(sql);  
             ResultSet rs = psmt.executeQuery();
             while(rs.next()){
            	 a.setEmail(rs.getString(1));
            	 a.setPassword(rs.getString(2)); 
             }
             
        } catch (SQLException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }finally {  
             DBDao.closeConnection(connection);
}  return a;
    }
}
