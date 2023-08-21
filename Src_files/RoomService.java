import java.io.Serializable;
import java.time.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Room Service class, parent class of all types of services (extended by OrderFood class)
 */
abstract class RoomService implements Serializable {		// Room Service class is like an interface class
	
	Scanner sc = new Scanner(System.in);
	
	/**
	 * enumeration of 3 order statuses (confirmed, preparing, delivered)
	 */
	enum orderStatus {CONFIRMED, PREPARING, DELIVERED};		
	
	/**
	 * orderStatus attribute (eg status = orderStatus.CONFIRMED)
	 */
	private orderStatus status;
	
	/**
	 * date and time of order
	 */
	private LocalDateTime orderTime;
	
	/**
	 * remarks or additional comments from customers
	 */
	private String remarks;
	
	/**
	 * bill of a particular order
	 */
	double serviceBill;
	
	/**
	 * name of a particular order
	 */
	String serviceName;
	
	/**
	 * total bill of all orders from all services (currently only orderfood service)
	 */
	private double totalBill = 0;
	
	/**
	 * room service constructor
	 */
	public RoomService() {}
	
	/**
	 * view options available for each service, implementation provided in subclass
	 */
	public void viewOptions() {}
	
	/**
	 * place order (by staff), implementation provided in subclass
	 */
	public void placeOrder() {}				// every subclass must implement this method
	
	/**
	 * delete order (by staff), implementation provided in subclass
	 */
	public void deleteOrder() {}				// every subclass must implement this method
	
	/**
	 * gets date and time of order
	 * @return LocalDateTime
	 */
	public LocalDateTime getTime() {
		return this.orderTime;
	}
	
	/**
	 * sets date and time of order
	 */
	public void setTime() {
		this.orderTime = LocalDateTime.now();
	}
	
	/**
	 * sets order status
	 */
	public void setOrderStatus(orderStatus s) {
		this.status = s;
	}
	
	/**
	 * gets order status
	 * @return orderStatus
	 */
	public orderStatus getOrderStatus() {
		return this.status;
	}
	
	/**
	 * gets bill of a particular order
	 * @return double
	 */
	public double getServiceBill() {
		return this.serviceBill;
	}
	
	/**
	 * gets name of a particular order
	 * @return String
	 */
	public String getServiceName() {
		return this.serviceName;
	}
	
	/**
	 * gets total room service bill for a room
	 * @return double
	 */
	public double getTotalBill(ArrayList<FoodItem> roomService) {
		for (FoodItem i : roomService) {
			totalBill += i.getItemPrice();
		}
		return totalBill;
	}
	
	/**
	 * sets remarks from customers
	 */
	public void setRemarks() {
		String remarksFood;
		while(true) {
			System.out.println("Additional remarks:");
			remarksFood = sc.nextLine();
			if(remarksFood.matches("[^0-9]+"))
				break;
		}
		this.remarks = remarksFood;
	}
	
	/**
	 * gets remarks
	 * @return String
	 */
	public String getRemarks() {
		return this.remarks;
	}

}
