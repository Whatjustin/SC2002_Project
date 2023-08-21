import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
/**
 * To make payment and check out guest
 * @author Justin
 *
 */
public class Payment {

	/**
	 * Field for checking if charges has been paid.
	 */
	 private boolean paid = false;
	/**
	 * Value of service charge set at 10%.
	 */
	static final private double svcCharge = 1.1;
	/**
	 * Value of goods and service tax set to 7%.
	 */
	 static final private double gst = 1.07;
	/**
	 * String value of guest name in.
	 */
	private String guestName;
	/**
	 * Reservation object
	 */
	private Reservation r;
	/**
	 * RoomService class
	 */
	private RoomService roomSvc;
	/**
	 * ArrayList of FoodItem object
	 */
	private ArrayList<FoodItem> foodOrder = new ArrayList<FoodItem>();
	/**
	 * PriceChange class
	 */
	private PriceChange price;
	/**
	 * Guest object
	 */
	private Guest g;
	/**
	 * Constructs a payment class using given guest name.
	 * @param name String
	 */
	 public Payment (String name) {
		 this.guestName = name;
		 this.r = Database.reservationHM.get(guestName);
		 price = new PriceChange(guestName);
		 this.foodOrder = Database.roomServiceHM.get(r.getMainGuestName());
		 this.g = Database.guestHM.get(guestName);
		 this.roomSvc = new OrderFood(foodOrder);
	 }
	 
	 /**
	 * Returns the total cost of room service.
	 * @return double
	 */
	private double getRoomSvcBill() {
		if(foodOrder == null)
			return 0;
		else
			return roomSvc.getTotalBill(foodOrder);
	}

	/**
	 * Returns the base cost of the room without any added charges
	 * @return double
	 */
	private double getRoomCharges() {
	  return price.calculatePriceChange(r.getCheckInDate(),r.getCheckOutDate());
	 }

	/**
	 * Prints the items that was ordered in room service.
	 */
	private void printRoomSvc() {
		if(foodOrder != null) {
			for (FoodItem i : this.foodOrder) {
				System.out.println(i.getItemName());
			}
		}
	 }

	/**
	 * Returns true or false depending on whether payment has been made.
	 * @return boolean
	 */
	 public boolean getPaidStatus() {
		 return this.paid;
	 }

	/**
	 * Updates the room availability after payment has been made.
	 */
	public void paid() {
	 // change availability of room //
		if (paid == true) {
		   //change room avail
			ArrayList<Guest> guestAL = r.getGuest();
			for(Guest i:guestAL) {
				Database.reservationHM.remove(i.getName());
				Database.roomHM.remove(i.getName());
			}
			String g = r.getMainGuestName(); 
			Database.roomServiceHM.remove(g); 
			Database.reservationCodeHM.remove(r.getReservationCode());
			Calendar checkInDate = r.getCheckInDate();
			String roomNumber = r.getRoom().getRoomNumber();
			ArrayList<Calendar[]> calendarAL = new ArrayList<Calendar[]>();
			calendarAL = Database.dateRoomHM.get(roomNumber);
			for (int j=0; j<calendarAL.size(); j++) {
				if (checkInDate.equals(calendarAL.get(j)[0])) {
					calendarAL.remove(j);							//make this room available again at that time
				}
			}
			Room room1 = SingleRooms.getRoomObject(roomNumber);		//Gets Single Room Object
			if(room1 == null) {										//If room number does not belong to Single Room, 
				room1 = DoubleRooms.getRoomObject(roomNumber);		//tries to get from Double Room
			}
			if(room1 == null) {
				room1 = DeluxeRooms.getRoomObject(roomNumber);
			}
			if(room1 == null) {
				room1 = VIPRooms.getRoomObject(roomNumber);			//Tries all room type to get Room Object
			}
			Database.dateRoomHM.put(roomNumber, calendarAL);
			if(calendarAL.size()>0) {
				r.getRoom().setStatus(Room.statuses.RESERVED);
				room1.setStatus(Room.statuses.RESERVED);
			}
			else {
				r.getRoom().setStatus(Room.statuses.VACANT);
				room1.setStatus(Room.statuses.VACANT);
			}
		}
	 }

	/**
	 * Prints the payment invoice.
	 */
	public void printInvoice() {
	  System.out.println("Impel Down Hotel                             INVOICE");
	  System.out.println("----------------------------------------------------");
	  System.out.println("BILL TO");
	  System.out.println(r.getMainGuestName());
	  System.out.println(g.getAddress());
	  System.out.println("Room Number: "+r.getRoom().getRoomNumber());
	  double roomCharges = getRoomCharges();
	  double holidayDisc = price.getHolidayDiscount();
	  double roomServiceBill = getRoomSvcBill();
	  double subtotal = roomCharges + roomServiceBill;
	  double serviceCharge = subtotal*0.1;
	  double gst = subtotal*svcCharge*0.07;
	  double total = subtotal + serviceCharge + gst;
	  DecimalFormat df = new DecimalFormat("0.00");
	  System.out.println("---------------------------------------------------------");
	  System.out.println("ROOM SERVICE					" + "$" + roomServiceBill);
	  
	  printRoomSvc();		
	  				
	  System.out.println("---------------------------------------------------------");
	  System.out.println("ROOM CHARGES					" + "$" + df.format(roomCharges));  
	  System.out.println("Room Charge             " + "$" + df.format(price.getRoomPrice()));
	  System.out.println("Holiday Promotion     " + "- $" + df.format(holidayDisc));
	  System.out.println("\t\tSubTotal                 $" + df.format(subtotal));
	  System.out.println("\t\tService Charge (10%)     $" + df.format(serviceCharge));
	  System.out.println("\t\tGST (7%)                 $" + df.format(gst));
	  System.out.println("\t\tTOTAL      	         $" + df.format(total));
	  System.out.println();
	  this.paid=true;
	 } 
}


/* TO_DO LIST
 * room charges
 * tax
 * room services
 * payment details (payment method: credit card etc...)
 * billing address (yl/yc)
 * no.of days of stay 
 * check in check out date
 * room occupancy report
 * name (yl/yc)
 */
