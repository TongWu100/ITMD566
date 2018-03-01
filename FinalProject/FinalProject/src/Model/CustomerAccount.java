package Model;

public class CustomerAccount {
   private String accountid;
   private String userid;
   private int rentCarNumber;
   private int buyCarNumber;
   private double balance;
   public CustomerAccount() {

}
public String getAccountid() {
	return accountid;
}
public void setAccountid(String accountid) {
	this.accountid = accountid;
}
public String getUserid() {
	return userid;
}
public void setUserid(String userid) {
	this.userid = userid;
}
public int getRentCarNumber() {
	return rentCarNumber;
}
public void setRentCarNumber(int rentCarNumber) {
	this.rentCarNumber = rentCarNumber;
}
public int getBuyCarNumber() {
	return buyCarNumber;
}
public void setBuyCarNumber(int buyCarNumber) {
	this.buyCarNumber = buyCarNumber;
}
public double getBalance() {
	return balance;
}
public void setBalance(double balance) {
	this.balance = balance;
}  
   
   
}
