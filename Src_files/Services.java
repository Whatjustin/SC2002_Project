import java.util.ArrayList;

/**
 * Services class, stores arrays of all services (in future when we add more services)
 */
public class Services {
	
	/**
	 * Array storing all food orders, used in Payment class
	 */
	public ArrayList<FoodItem> foodOrder = new ArrayList<FoodItem>();	
	//static ArrayList<Cleaning> cleaningOrder = new ArrayList<Cleaning>(); // for future if we want to create new services
}
