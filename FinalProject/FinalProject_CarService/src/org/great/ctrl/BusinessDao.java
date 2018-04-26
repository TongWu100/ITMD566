package org.great.ctrl;

import java.util.List;
import java.util.Map;

import org.great.entity.Buy;
import org.great.entity.Car;
import org.great.entity.CarPart;
import org.great.entity.CustomerAccount;
import org.great.entity.Files;
import org.great.entity.Power;
import org.great.entity.User;


public interface BusinessDao {

	//Login
	Map<String, String> login(String userName,String password);
	
	//access permission
	Power select_power(String rightid);
	
	//get user list
	List<User> getUserList();
	//get user list
	List<User> getUserLists(String email);
	
	//add user
	boolean addUser(User user);
	
	//delete user
	void delUser(String email);
	
	//delete users' access permission
	boolean delRights(String username);
	
	//get user account list
	List<User> getUserList2();
	
	//modify user account
	boolean updateUser2(User user);
	
	//modify user account
	boolean updateUser3(User user);
	
	//get user information
	User getUser(String email,String username);
	
	//get user information
	User getUser(String email);
	
	//get user account balance
	String getUserAccount(String email);
	
	//add user access
	boolean addRights(String username);
	
	//check customer account
	CustomerAccount getCustomerAccount(String email);
	
	//update cutomer account
	boolean updateCustomerAccount(User user);
	
	//get car list
	List<Car> getCarList();
	
	//add new car
	boolean addCar(Car car);
	
	//delete car
	boolean delCar(String id);

	//get carpart list
	List<CarPart> getCarPartList();
	
	//add new carpart
	boolean addCarPart(CarPart carpart);
	
	//delete carpart
	boolean delCarPart(String id);
	
	//get buying count
	String getBuyCount(String email,String name);
	
	//get buying number
	String getBuyNum(String email);
	
	//add buying record
	boolean addBuyRecord(Buy buy);
	
	//get buying list
	List<Buy> getBuyList();
	
	//add new car
	boolean addFiles(Files files);
	
}
