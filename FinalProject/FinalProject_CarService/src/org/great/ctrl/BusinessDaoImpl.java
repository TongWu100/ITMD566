package org.great.ctrl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.great.entity.Buy;
import org.great.entity.Car;
import org.great.entity.CarPart;
import org.great.entity.CustomerAccount;
import org.great.entity.Files;
import org.great.entity.Power;
import org.great.entity.User;
import org.great.util.Util;

public class BusinessDaoImpl implements BusinessDao {

	public BusinessDaoImpl() {
		
	}

	@Override
	public CustomerAccount getCustomerAccount(String email) {
		return null;
	}

	@Override
	public boolean updateCustomerAccount(User user) {
		return false;
	}

	@Override
	public List<Car> getCarList() {
		Connection conn = Util.getInstance().getConnetionMirror();
		PreparedStatement statement = null;
		ResultSet result = null;
		List<Car> list =new ArrayList<Car>();
		
		try {
			statement=conn.prepareStatement("select * from car");
			result=statement.executeQuery();
			
			while(result.next()){
				Car car =new Car();
				car.setId(result.getString("id"));
				car.setName(result.getString("name"));
				car.setType(result.getString("type"));
				car.setBuyPrice(result.getDouble("buyPrice"));
				car.setRentPrice(result.getDouble("rentPrice"));
				car.setFilecode(result.getString("filecode"));
				car.setFilepath(result.getString("filepath"));
				car.setImgpath(result.getString("imgpath"));
				
				list.add(car);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(conn!=null){
					conn.close();
				}
				if(statement!=null){
					statement.close();
				}
				if(result!=null){
					result.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public boolean addCar(Car car) {
		String sql="insert into car values(nextval('seq_car'),?,?,?,?,?,?,?)";
		Object[] params={car.getName(),car.getType(),car.getBuyPrice(),car.getRentPrice(),car.getFilecode(),car.getFilepath(),car.getImgpath()};
		update(sql, params);
		return true;
	}

	@Override
	public boolean delCar(String id) {
		Connection conn = Util.getInstance().getConnetionMirror();
		PreparedStatement statement = null;
		ResultSet result = null;
		
		try {
			statement=conn.prepareStatement("delete from car where id =?");
			statement.setString(1, id);
			statement.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(conn!=null){
					conn.close();
				}
				if(statement!=null){
					statement.close();
				}
				if(result!=null){
					result.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public List<CarPart> getCarPartList() {
		Connection conn = Util.getInstance().getConnetionMirror();
		PreparedStatement statement = null;
		ResultSet result = null;
		List<CarPart> list =new ArrayList<CarPart>();
		
		try {
			statement=conn.prepareStatement("select * from carpart");
			result=statement.executeQuery();
			
			while(result.next()){
				CarPart carpart =new CarPart();
				carpart.setId(result.getString("id"));
				carpart.setName(result.getString("name"));
				carpart.setBuyprice(result.getDouble("buyprice"));
				carpart.setFilecode(result.getString("filecode"));
				carpart.setFilepath(result.getString("filepath"));
				carpart.setImgpath(result.getString("imgpath"));
				
				list.add(carpart);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(conn!=null){
					conn.close();
				}
				if(statement!=null){
					statement.close();
				}
				if(result!=null){
					result.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public boolean addCarPart(CarPart carpart) {
		String sql="insert into carpart values(nextval('seq_carpart'),?,?,?,?,?)";
		Object[] params={carpart.getName(),carpart.getBuyprice(),carpart.getFilecode(),carpart.getFilepath(),carpart.getImgpath()};
		update(sql, params);
		return true;
	}

	@Override
	public boolean delCarPart(String id) {
		Connection conn = Util.getInstance().getConnetionMirror();
		PreparedStatement statement = null;
		ResultSet result = null;
		
		try {
			statement=conn.prepareStatement("delete from carpart where id =?");
			statement.setString(1, id);
			statement.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(conn!=null){
					conn.close();
				}
				if(statement!=null){
					statement.close();
				}
				if(result!=null){
					result.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public String getBuyNum(String email) {
		// TODO Auto-generated method stub
		Connection conn = Util.getInstance().getConnetionMirror();
		PreparedStatement statement = null;
		ResultSet result = null;
		String buynum = "0";
		try {
			statement=conn.prepareStatement("select buynum from buy where buyuseremail =?");
			statement.setString(1, email);
			result=statement.executeQuery();
			
			if(result.next()){
				buynum = result.getString("buynum");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(conn!=null){
					conn.close();
				}
				if(statement!=null){
					statement.close();
				}
				if(result!=null){
					result.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return buynum;
	}

	@Override
	public boolean addBuyRecord(Buy buy) {
		String sql="insert into buy values(nextval('seq_buy'),?,?,?,?,?,?)";
		//String sql="insert into buy values(?,?,?,?,?,?)";
		Object[] params={buy.getName(),buy.getBuynum(),buy.getBuytime(),buy.getBuyuser(),buy.getBuyuseremail(),buy.getBuytype()};
		update(sql, params);
		return true;
	}

	@Override
	public Map<String, String> login(String userName, String password) {
		Connection conn = Util.getInstance().getConnetionMirror();
		PreparedStatement statement = null;
		ResultSet result = null;
		Map<String, String> map = new HashMap<String,String>();
		map.put("isLogin", "flase");
		map.put("role", "-1");
		try {
			statement = conn.prepareStatement("select * from user where userName =? and password =?");
			statement.setString(1, userName);
			statement.setString(2, password);
			result = statement.executeQuery();
			if (result.next()) {
				map.put("isLogin", "true");
				map.put("role", result.getString("role"));
				map.put("email", result.getString("email"));
				map.put("city", result.getString("city"));
				map.put("streetaddress", result.getString("streetaddress"));
				map.put("account", result.getString("account"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn!=null){
					conn.close();
				}
				if(statement!=null){
					statement.close();
				}
				if(result!=null){
					result.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return map;
	}

	@Override
	public Power select_power(String rightName) {
		Connection conn = Util.getInstance().getConnetionMirror();
		PreparedStatement statement = null;
		ResultSet result = null;
		Power power=null;
		try {
			statement=conn.prepareStatement("select * from rights where rights_name = ?");
			statement.setString(1, rightName);
			result=statement.executeQuery();
			
			if(result.next()){
				power=new Power();
				power.setRights_name(result.getString("rights_name"));
				power.setUser_power(result.getString("user_power"));
				power.setCar_power(result.getString("car_power"));
				power.setCar_part_power(result.getString("car_part_power"));
				power.setUser_account_power(result.getString("user_account_power"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(conn!=null){
					conn.close();
				}
				if(statement!=null){
					statement.close();
				}
				if(result!=null){
					result.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return power;
	}

	@Override
	public List<User> getUserList() {

		Connection conn = Util.getInstance().getConnetionMirror();
		PreparedStatement statement = null;
		ResultSet result = null;
		List<User> list =new ArrayList<User>();
		
		try {
			statement=conn.prepareStatement("select * from user where username !='admin'");
			result=statement.executeQuery();
			
			while(result.next()){
				User user =new User();
				user.setUsername(result.getString("username"));
				user.setEmail(result.getString("email"));
				user.setRole(result.getString("role").equals("0")?"admin":"customer");
				user.setState(result.getString("state"));
				user.setCity(result.getString("city"));
				user.setStreetaddress(result.getString("streetaddress"));
				
				list.add(user);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(conn!=null){
					conn.close();
				}
				if(statement!=null){
					statement.close();
				}
				if(result!=null){
					result.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	
	}
	
	@Override
	public List<User> getUserLists(String email) {

		Connection conn = Util.getInstance().getConnetionMirror();
		PreparedStatement statement = null;
		ResultSet result = null;
		List<User> list =new ArrayList<User>();
		
		try {
			statement=conn.prepareStatement("select * from user where email =?");
			statement.setString(1, email);
			result=statement.executeQuery();
			
			while(result.next()){
				User user =new User();
				user.setUsername(result.getString("username"));
				user.setEmail(result.getString("email"));
				user.setRole(result.getString("role").equals("0")?"admin":"customer");
				user.setState(result.getString("state"));
				user.setCity(result.getString("city"));
				user.setStreetaddress(result.getString("streetaddress"));
				
				list.add(user);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(conn!=null){
					conn.close();
				}
				if(statement!=null){
					statement.close();
				}
				if(result!=null){
					result.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	
	}
	@Override
	public void delUser(String email) {
		Connection conn = Util.getInstance().getConnetionMirror();
		PreparedStatement statement = null;
		ResultSet result = null;
		
		try {
			statement=conn.prepareStatement("delete from user where email =?");
			statement.setString(1, email);
			statement.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(conn!=null){
					conn.close();
				}
				if(statement!=null){
					statement.close();
				}
				if(result!=null){
					result.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean addUser(User user) {
		String sql="insert into user values(?,?,?,?,?,?,?,?)";
		Object[] params={user.getEmail(),user.getUsername(),user.getPassword(),user.getState(),user.getCity(),user.getStreetaddress(),user.getRole(),user.getAccount()};
		update(sql, params);
		return true;
	}
	
	@Override
	public List<User> getUserList2() {
		Connection conn = Util.getInstance().getConnetionMirror();
		PreparedStatement statement = null;
		ResultSet result = null;
		List<User> list =new ArrayList<User>();
		
		try {
			statement=conn.prepareStatement("select * from user");
			result=statement.executeQuery();
			
			while(result.next()){
				User user =new User();
				user.setUsername(result.getString("username"));
				user.setEmail(result.getString("email"));
				user.setRole(result.getString("role").equals("0")?"管理员":"普通用户");
				user.setState(result.getString("state"));
				user.setCity(result.getString("city"));
				user.setStreetaddress(result.getString("streetaddress"));
				user.setAccount(result.getString("account"));
				
				list.add(user);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(conn!=null){
					conn.close();
				}
				if(statement!=null){
					statement.close();
				}
				if(result!=null){
					result.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	
	}
	
	@Override
	public boolean updateUser2(User user) {
		String sql="update user set account=?,state=?,city=?,streetAddress=? where email=?";
		Object[] params={user.getAccount(),user.getState(),user.getCity(),user.getStreetaddress(),user.getEmail()};
		update(sql, params);
		return true;
	}
	
	@Override
	public boolean updateUser3(User user) {
		String sql="update user set account=? where email=?";
		Object[] params={user.getAccount(),user.getEmail()};
		update(sql, params);
		return true;
	}
	
	private void update(String sql, Object[] params){
		Connection conn = Util.getInstance().getConnetionMirror();
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				statement.setObject(i + 1, params[i]);
			}
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public User getUser(String email,String username) {
		
		Connection conn = Util.getInstance().getConnetionMirror();
		PreparedStatement statement = null;
		ResultSet result = null;
		User user = null;
		try {
			statement=conn.prepareStatement("select * from user where email =? or username = ?");
			statement.setString(1, email);
			statement.setString(2, username);
			result=statement.executeQuery();
			
			if(result.next()){
				user =new User();
				user.setUsername(result.getString("username"));
				user.setEmail(result.getString("email"));
				user.setPassword(result.getString("password"));
				user.setAccount(result.getString("account"));
				user.setRole(result.getString("role").equals("0")?"管理员":"普通用户");
				user.setState(result.getString("state"));
				user.setCity(result.getString("city"));
				user.setStreetaddress(result.getString("streetaddress"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(conn!=null){
					conn.close();
				}
				if(statement!=null){
					statement.close();
				}
				if(result!=null){
					result.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}
	
	@Override
	public User getUser(String email) {
		
		Connection conn = Util.getInstance().getConnetionMirror();
		PreparedStatement statement = null;
		ResultSet result = null;
		User user = null;
		try {
			statement=conn.prepareStatement("select * from user where email =?");
			statement.setString(1, email);
			result=statement.executeQuery();
			
			if(result.next()){
				user =new User();
				user.setUsername(result.getString("username"));
				user.setEmail(result.getString("email"));
				user.setPassword(result.getString("password"));
				user.setAccount(result.getString("account"));
				user.setRole(result.getString("role").equals("0")?"管理员":"普通用户");
				user.setState(result.getString("state"));
				user.setCity(result.getString("city"));
				user.setStreetaddress(result.getString("streetaddress"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(conn!=null){
					conn.close();
				}
				if(statement!=null){
					statement.close();
				}
				if(result!=null){
					result.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}

	@Override
	public boolean addRights(String username) {

		Connection connection = Util.getInstance().getConnetionMirror();
		PreparedStatement statement = null;
		try {
			statement = connection
					.prepareStatement("insert into rights values(nextval('seq_rights'),?,2,2,2,2)");
			statement.setString(1, username);
			statement.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public String getUserAccount(String email) {
		// TODO Auto-generated method stub
		Connection conn = Util.getInstance().getConnetionMirror();
		PreparedStatement statement = null;
		ResultSet result = null;
		String account = "0";
		try {
			statement=conn.prepareStatement("select account from user where email =?");
			statement.setString(1, email);
			result=statement.executeQuery();
			
			if(result.next()){
				account = result.getString("account");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(conn!=null){
					conn.close();
				}
				if(statement!=null){
					statement.close();
				}
				if(result!=null){
					result.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return account;
	}

	@Override
	public String getBuyCount(String email, String name) {
		// TODO Auto-generated method stub
		Connection conn = Util.getInstance().getConnetionMirror();
		PreparedStatement statement = null;
		ResultSet result = null;
		String counts = "0";
		try {
			statement=conn.prepareStatement("select count(*) as counts from buy where buyuseremail =? and name = ?");
			statement.setString(1, email);
			statement.setString(1, name);
			result=statement.executeQuery();
			
			if(result.next()){
				counts = result.getString("counts");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(conn!=null){
					conn.close();
				}
				if(statement!=null){
					statement.close();
				}
				if(result!=null){
					result.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return counts;
	}

	@Override
	public List<Buy> getBuyList() {
		// TODO Auto-generated method stub
		Connection conn = Util.getInstance().getConnetionMirror();
		PreparedStatement statement = null;
		ResultSet result = null;
		List<Buy> list =new ArrayList<Buy>();
		
		try {
			statement=conn.prepareStatement("select * from buy");
			result=statement.executeQuery();
			
			while(result.next()){
				Buy buy =new Buy();
				buy.setName(result.getString("name"));
				buy.setBuynum(result.getString("buynum"));
				buy.setBuytime(result.getString("buytime"));
				buy.setBuytype(result.getString("buytype"));
				buy.setBuyuser(result.getString("buyuser"));
				buy.setBuyuseremail(result.getString("buyuseremail"));
				
				list.add(buy);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(conn!=null){
					conn.close();
				}
				if(statement!=null){
					statement.close();
				}
				if(result!=null){
					result.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public boolean delRights(String username) {
		// TODO Auto-generated method stub
		Connection conn = Util.getInstance().getConnetionMirror();
		PreparedStatement statement = null;
		ResultSet result = null;
		
		try {
			statement=conn.prepareStatement("delete from rights where rights_name =?");
			statement.setString(1, username);
			statement.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(conn!=null){
					conn.close();
				}
				if(statement!=null){
					statement.close();
				}
				if(result!=null){
					result.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean addFiles(Files files) {
		// TODO Auto-generated method stub
		String sql="insert into files values(nextval('seq_files'),?,?)";
		Object[] params={files.getFilecode(),files.getFilepath()};
		update(sql, params);
		return true;
	}


}
