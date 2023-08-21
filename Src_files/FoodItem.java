import java.io.Serializable;

/**
 * FoodItem class, stores information of one food item
 */
public class FoodItem implements Serializable {
	
	/**
	 * food item name
	 */
	private String itemName;
	/**
	 * food item description
	 */
	private String description;
	/**
	 * food item price
	 */
	private double price;
	
	/**
	 * food item constructor
	 * @param itemName Food item name
	 * @param description	Food item description
	 * @param price	Food item price
	 */
	public FoodItem(String itemName, String description, double price) {
		this.itemName = itemName;
		this.description = description;
		this.price = price;
	}
	
	/**
	 * gets food item price
	 * @return double
	 */
	public double getItemPrice() {
		return this.price;
	}
	
	/**
	 * sets food item price
	 * @param price food item price
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	
	/**
	 * gets food item name
	 * @return String
	 */
	public String getItemName() {
		return this.itemName;
	}
	
	/**
	 * sets food item name
	 * @param itemName food item name
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	/**
	 * set food item description
	 * @param description Food Description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * gets food item description
	 * @return String description of food
	 */
	public String getDescription() {
		return description;
	}
}
