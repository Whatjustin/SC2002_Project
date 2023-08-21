import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.Scanner;

/**
 * Interface for interaction between hotel and guests
 * @author Oo Yifei
 *
 */
public class Admin {
	/**
	 * Constructor which connects to HotelApp
	 */
	public Admin() {}
	/**
	 * Main method for interaction
	 * @throws FileNotFoundException
	 */
	public void admin() throws FileNotFoundException {
		Scanner sc = new Scanner(System.in);
		int choice;
		do{
			System.out.println(
				"======================Admin======================\n"+
				"1. Guest(Check-in/Check-out/Room Service)\n"+
				"2. Reservation(Create/Update/Remove/Display)\n"+
				"3. Room Details \n"+
				"4. Back\n"+
				"=================================================\n");
			System.out.println("Please enter number of your selection: ");
			choice = sc.nextInt(); //gets user's request
			switch(choice) {
				case 1:
					System.out.println("Please enter guest name: ");
					sc.nextLine();
					String guestName = sc.nextLine();
					guestName = guestName.toUpperCase();
					if(!Database.guestHM.containsKey(guestName)) { //check if guest is created in database
						System.out.println("Guest not found!");
						System.out.println("You Can Create Guest by Creating Reservation or");
						System.out.println("Is guest walking-in? (Y/N)");
						String answer = sc.nextLine();
						answer = answer.toUpperCase();
						if(answer.equals("Y"))
							WalkIn.walkIn();
						else
							break;
					}
					GuestCtrl guestC = new GuestCtrl(guestName);
					guestC.guestCtrl(); //calls another interface
					break;
				case 2:
					ReservationCtrl rM= new ReservationCtrl();
					rM.reservation();  //calls another interface
					break;
				case 3:						
					sc.nextLine();
					System.out.println("Please Enter Room Number: ");
					String roomNumber = sc.nextLine();
					boolean inMaintenance = false;
					if(Database.roomMaintenanceHM.containsKey(roomNumber)) {
						Calendar cal = Calendar.getInstance();
						Calendar[] calA = new Calendar[2];
						calA[0] = Database.roomMaintenanceHM.get(roomNumber)[0];
						calA[1] = Database.roomMaintenanceHM.get(roomNumber)[1];
						if(cal.after(calA[0]) && cal.before(calA[1]))
							inMaintenance = true;
					}
					Room room = SingleRooms.getRoomObject(roomNumber);		//Gets Single Room Object
					if(room == null) {										//If room number does not belong to Single Room, 
						room = DoubleRooms.getRoomObject(roomNumber);		//tries to get from Double Room
					}
					if(room == null) {
						room = DeluxeRooms.getRoomObject(roomNumber);
					}
					if(room == null) {
						room = VIPRooms.getRoomObject(roomNumber);			//Tries all room type to get Room Object
					}
					if(room == null) {
						System.out.println("room not found");
						break;
					}
					if(inMaintenance)
						room.setStatus(Room.statuses.MAINTENANCE);
					if(room instanceof Single)
						HotelView.getRoomDetails(room, "Single Room"); //pass in room and room type for details to be displayed
					else if (room instanceof DoubleR)
						HotelView.getRoomDetails(room, "Double Room"); //pass in room and room type for details to be displayed
					else if(room instanceof Deluxe)
						HotelView.getRoomDetails(room, "Deluxe Room"); //pass in room and room type for details to be displayed
					else
						HotelView.getRoomDetails(room, "VIP room"); //pass in room and room type for details to be displayed
					break;
				case 4:
					System.out.println("Exiting...");
					System.out.println();
					break;
				default:
					System.out.println("Please Enter 1,2,3 or 4");
					break;
			}
		} while (choice != 4);
	}
}
