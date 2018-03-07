package Model;

public class CarRental {
    private String id;
    private String type;
    private String rented;
    private String returned;
    private double cost;
	public CarRental() {

	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
    public String getDateRented() {
        return rented;
  }
    public void setDateRented(String rented) {
        this.rented = rented;
  }
    public String getDateReturned() {
        return returned;
  }
    public void setDateReturned(String returned) {
        this.returned = returned;
  }
    public double getRentCostDaily() {
        return cost;
  }
    public void setRentCostDaily(double cost) {
        this.cost = cost;
  }

}
