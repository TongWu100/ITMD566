package Model;

public class PartService {
    private String id;
    private String type;
    private double amount;
    private double cost;
	public PartService() {

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
    public double getAmount() {
        return amount;
  }
    public void setAmount(double amount) {
        this.amount = amount;
  }
    public double getCost() {
        return cost;
  }
    public void setCost(double cost) {
        this.cost = cost;
  }

}
