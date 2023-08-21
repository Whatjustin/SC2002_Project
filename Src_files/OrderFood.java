import java.util.ArrayList;
import java.util.Scanner;

/**
 * Order Food class, used mainly to order food (1 object = 1 food item order)
 */
public class OrderFood extends RoomService{					
	
	Scanner sc = new Scanner(System.in);
	
	/**
	 * food item that is ordered by customers
	 */
	private FoodItem order;
	/**
	 * ArrayList of FoodItem object
	 */
	private ArrayList<FoodItem> foodOrder;
	
	/**
	 * constructor for OrderFood class
	 * @param foodOrder ArrayList of FoodItem Object
	 */
	public OrderFood(ArrayList<FoodItem> foodOrder) {
		this.foodOrder = foodOrder;
	}
		
	/**
	 * prints menu
	 */
	public void viewOptions() {
		Menu.printMenu();
	}
	
	/**
	 * place order (sets the attributes of the object)
	 */
	public void placeOrder() {
		System.out.println("Please select item from menu to order:");
		int choice = sc.nextInt();
		this.order = Menu.menu.get(choice-1);					// set order item
		this.serviceBill = this.order.getItemPrice();		
		this.serviceName = this.order.getItemName();
		//this.addTotalBill(this.serviceBill);				// add price to bill
		//this.setServiceBill();
		this.setRemarks();									// get remarks
		this.setOrderStatus(orderStatus.CONFIRMED);
		this.setTime();										// set order date and time
		//services.add(this.serviceName);						// add into record of services
		this.foodOrder.add(this.order);
		System.out.println("Order successful!\n");
	}
	/**
	 * display order made
	 */
	public void showOrder() {
		System.out.println(this.getTime());
		System.out.println(this.order.getItemName() + "\t$" + this.order.getItemPrice());
		System.out.println(this.getRemarks());
		System.out.println(this.getOrderStatus());
	}
	
	/**
	 * gets food item order
	 * @return FoodItem
	 */
	public FoodItem getFoodOrder() {
		return this.order;
	}
	
	
	
//	public void setOrderStatus(orderStatus status) {
//		this.s = status;
//	}
//	
//	public orderStatus getOrderStatus() {
//		return this.s;
//	}
//	
//	public double getBill() {
//		return this.bill;
//	}


}
