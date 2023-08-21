import java.util.Scanner;
import java.util.ArrayList;
import java.util.Calendar;
/**
 * To Check-in Guest
 * @author Oo Yifei
 *
 */
public class CheckIn {
	
	Scanner sc = new Scanner(System.in);
	/**
	 * Guest's name
	 */
	private String guestName;
	/**
	 * Constructor that connects to GuestCtrl
	 * @param guestName
	 */
	public CheckIn(String guestName) {
		this.guestName = guestName;
	}
	/**
	 * main method to check-in guest
	 */
	public void guestCheckIn() {
		
		Reservation reservation;
		Room room;
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		Calendar cal3 = Calendar.getInstance();
		
		if(Database.reservationHM.containsKey(guestName)) { 	//ensure the person has made a reservation
			room = Database.roomHM.get(guestName);				//get room from database
			if(room == null) {
				System.out.println("Guest has no room yet");
				return;
			}
			String roomNumber = room.getRoomNumber();
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
			reservation = Database.reservationHM.get(guestName);//get reservation from database
			if(reservation.getReservationStatus() == Reservation.ReservationStatus.Checked_in) { //return if guest has already checked-in
				System.out.println("This guest has already checked in");
				return;
			}
			Calendar cals = reservation.getCheckInDate();		//get check in date and time
			int years = cals.get(Calendar.YEAR);				//get check in year
			int months = cals.get(Calendar.MONTH);				//get check in month
			int days = cals.get(Calendar.DATE);					//get check in date
			int hrs = cals.get(Calendar.HOUR_OF_DAY);			//get check in time
			cal1.set(years, months, days, hrs+1, 0);			//add 1 hour to check in date to check if reservation has expired
			cal3.set(years, months, days, hrs-1, 0);			//minus 1 hour to check in date to check if check in time too early
			cal2 = Calendar.getInstance();						//get current time
			if(cal2.before(cal1) && cal2.after(cal3)) {				//ensure the reservation has not expired
				room.setStatus(Room.statuses.OCCUPIED);			//set room status to occupied
				room1.setStatus(Room.statuses.OCCUPIED);
				reservation.setReservationStatus(Reservation.ReservationStatus.Checked_in); //set reservation status to checked-in
				System.out.println("Guest succesfully checked-in");
				System.out.println();
			}
			else if(cal2.after(cal1)) {
				reservation.setReservationStatus(Reservation.ReservationStatus.Expired); //reservation has expired, set status to expired
				ArrayList<Calendar[]> calendarAL = new ArrayList<Calendar[]>();
				calendarAL = Database.dateRoomHM.get(reservation.getRoom().getRoomNumber());
				for (int j=0; j<calendarAL.size(); j++) {
					 if (cals == calendarAL.get(j)[0])
						calendarAL.remove(j);									//make this room available again at that time
					}
				Database.dateRoomHM.put(reservation.getRoom().getRoomNumber(), calendarAL);
				boolean isInMaintenance =false;
				if(Database.roomMaintenanceHM.containsKey(reservation.getRoom().getRoomNumber())) {
					Calendar[] cal = new Calendar[2];
					cal[0] = Calendar.getInstance();
					cal[1] = Calendar.getInstance();
					cal[0] = Database.roomMaintenanceHM.get(reservation.getRoom().getRoomNumber())[0];
					cal[1] = Database.roomMaintenanceHM.get(reservation.getRoom().getRoomNumber())[1];
					Calendar calNow = Calendar.getInstance();
					if (calNow.after(cal[0]) && calNow.before(cal[1])) { 			//check if the room is under maintenance
						room.setStatus(Room.statuses.MAINTENANCE);
						room1.setStatus(Room.statuses.MAINTENANCE);
						isInMaintenance = true;
					}
				}
				if(!isInMaintenance) {
					if(calendarAL.size()>0) {
						room.setStatus(Room.statuses.RESERVED);
						room1.setStatus(Room.statuses.RESERVED);
					}
					else {
						room.setStatus(Room.statuses.VACANT);
						room1.setStatus(Room.statuses.VACANT);
					}
				}
				System.out.println("Your reservation has already expired");
				System.out.println("Do you want to still want to check-in now? (Y/N)");	 //Provide an option for guest to walk in
				String input = sc.nextLine();
				input = input.toUpperCase();
				if (input.equals("Y")) {
					WalkIn.walkIn(); //calls walkIn method for guest to walk in
				}	
			}
			else {
				Calendar cal = reservation.getCheckInDate();				//guest checking in too early 
				int year = cal.get(Calendar.YEAR);							//check in time is 1 hour before and after scheduled time
				int month = cal.get(Calendar.MONTH);
				int day = cal.get(Calendar.DATE);
				int time = cal.get(Calendar.HOUR_OF_DAY);
				System.out.println("Check in only allowed 1 hour before scheduled time. \n"+
								   "Scheduled Date and Time: \n"+
									year+"-"+(month+1)+"-"+day+"   "+time+"00hrs");
				System.out.println();
			}
			int numOfGuest = reservation.getNumOfAdults() + reservation.getNumOfChildren(); //that returns reservation
			Guest g = new Guest();
			ArrayList <String> guestNameAL = new ArrayList<String>();
			for (int i = 0; i<numOfGuest; i++) {
				g = reservation.getGuest().get(i);
				guestNameAL.add(g.getName());
				Database.reservationHM.put(g.getName(), reservation); 			//Stores data into the database
				Database.roomHM.put(g.getName(), reservation.getRoom());		
			}
			Database.reservationCodeHM.put(reservation.getReservationCode(), reservation);
		}
		else {
			System.out.println("Name not found. "+guestName+" did not make a reservation."); //guest has not made a reservation
			System.out.println("Do you want to still want to check-in now? (Y/N)");			//Provide an option for guest to check in
			String input = sc.nextLine();
			input = input.toUpperCase();
			if (input.equals("Y"))
				WalkIn.walkIn(); //calls walkIn method for guest to walk in
		}
	}
}
