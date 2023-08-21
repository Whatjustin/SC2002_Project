import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
/**
 * Interface for Guest
 * @author Oo Yifei
 *
 */
public class GuestCtrl {
	/**
	 * guestView object to display details of guest
	 */
	GuestView guestView = new GuestView();
	/**
	 * Guest object
	 */
	Guest g;
	/**
	 * Guest's name
	 */
	private String guestName;
	/**
	 * Constructor that connects to Admin class
	 * @param guestName Name of Guest
	 */
	public GuestCtrl(String guestName) {
		this.guestName = guestName.toUpperCase();
		g = Database.guestHM.get(guestName);
	}
	/**
	 * main method for user interaction
	 */
	public void guestCtrl() {
		Scanner sc = new Scanner(System.in);
		int choice;
		do{
			System.out.println(
				"===========================\n"+
			    "           Guest           \n"+
			    "===========================\n"+
				"1. Display guest details\n"+
				"2. Update guest details\n"+
				"3. Check-in\n"+
				"4. Check-out\n"+
				"5. Room Service\n"+
				"6. Back\n");
			System.out.println("Please enter number of your selection: ");
			choice = sc.nextInt();
			switch(choice) {
				case 1:
					//calls printGuestDetails method to print guest details
					guestView.printGuestDetails(guestName, g.getAddress(),g.getCreditCard(), g.getCountry(), g.getGender(), 
							g.getIdentity(), g.getIdentityNo(), g.getNationality(), g.getContact(), g.isAdult());
					break;
				case 2:
					Reservation r = Database.reservationHM.get(guestName);		//gets all related info before deleting
					Room room = Database.roomHM.get(guestName);						
					ArrayList<FoodItem> rs = new ArrayList<FoodItem>();
					boolean hasRS = false;
					if(Database.roomServiceHM.containsKey(guestName)) {
						hasRS = true;
						rs =Database.roomServiceHM.get(guestName);
						Database.roomServiceHM.remove(guestName);
					}
					Database.reservationHM.remove(guestName);
					Database.roomHM.remove(guestName);
					this.guestName = UpdateGuest.updateGuest(g);				//calls static method updateGuest of class UpdateGuest 
																				//and returns guest's name
					if(g.getCreditCard().getName() != "-") {	
						if(r != null)
							r.setBillingInfo("Credit Card");
					}
					else {
						if (r != null)
							r.setBillingInfo("Cash");
					}
					Database.reservationHM.put(guestName, r);					//stores all related info back to database
					Database.roomHM.put(guestName, room);
					if(hasRS)
						Database.roomServiceHM.put(guestName, rs);
					break;
				case 3:
					CheckIn checkIn = new CheckIn(guestName); //construct new CheckIn object by passing in guest name
					checkIn.guestCheckIn();					  //checks in guest
					break;
				case 4:
					Reservation r1 = Database.reservationHM.get(guestName);
					if(r1 == null) {
						System.out.println(guestName+" has no reservation.");
						break;
					}
					if(r1.getReservationStatus() == Reservation.ReservationStatus.In_Waitlist) {
						System.out.println("Guest has no room yet");
						break;
					}
					else if(r1.getReservationStatus() != Reservation.ReservationStatus.Checked_in) {
						System.out.println("Guest has not checked in");
						break;
					}
					Calendar cal = Calendar.getInstance(); 
					cal = r1.getCheckOutDate();						//get scheduled check-out date and time
					int year = cal.get(Calendar.YEAR);
					int month = cal.get(Calendar.MONTH);
					int day = cal.get(Calendar.DATE);
					Calendar cal2 = Calendar.getInstance();			//get current date and time
					int year1 = cal2.get(Calendar.YEAR);
					int month1 = cal2.get(Calendar.MONTH);
					int day1 = cal2.get(Calendar.DATE);
					if(year == year1 && month == month1 && day == day1) { //compare the two date and time
						Payment payment = new Payment(guestName); //construct new Payment object by passing in guest name
						payment.printInvoice();					  //print payment invoice
						payment.paid();						 //calls paid method to remove related date from Database and set room status to VACANT
					}
					else {
						System.out.println("Today is not scheduled check out date.");
						System.out.println("Please check-out on "+day+"-"+(month+1)+"-"+year+" or change check-out date in reservation");
						System.out.println();
					}
						
					break;
				case 5:
					Reservation r2 = Database.reservationHM.get(guestName); //get reservation of current guest
					if(r2 == null) {
						System.out.println(guestName+" has no room or reservation");
						break;
					}
					if(r2.getReservationStatus() != Reservation.ReservationStatus.Checked_in) {
						System.out.println("Guest has not checked in");
						break;
					}
					Services services = new Services();								//construct new Services
					if(Database.roomServiceHM.get(r2.getMainGuestName()) != null)
						services.foodOrder = Database.roomServiceHM.get(r2.getMainGuestName()); //check if this guest has previous order
					RoomService orderFood = new OrderFood(services.foodOrder); 		//construct new RoomService object, OrderFood inherits RoomService (Upcasting)
					orderFood.viewOptions();				  		 				//display menu 
					orderFood.placeOrder();					  			   			//Make an order for guest
					Database.roomServiceHM.put(r2.getMainGuestName(),services.foodOrder); //store the food ordered into database
					break;
				case 6:
					System.out.println("Exiting back to Admin");
					break;
				default:
					System.out.println("Please Enter 1,2 or 3");
					break;
			}
		} while (choice != 6);
	}
}
