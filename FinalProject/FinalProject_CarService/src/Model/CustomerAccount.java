package Model;

public class CustomerAccount {
   private String email;
   private int rentCarNumber;
   private int buyCarNumber;
   private double balance;
   public CustomerAccount() {

}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
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
