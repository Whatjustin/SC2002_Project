import java.util.Random;
import java.util.Scanner;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.ArrayList;

/**
 * Class to create Reservation objects 
 * @author Oo Yifei
 *
 */
public class CreateReservation {
	/**
	 * Accepts a Reservation object and assign details to it
	 * @param r Reservation object
	 */
	public static boolean createReservation(Reservation r) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter the number of guests checking-in: ");
		int numOfGuest;
		try{
	        numOfGuest = sc.nextInt();
		}
		catch (InputMismatchException ex) {
			System.out.println("numbers only");
			sc.nextLine();
	        numOfGuest = sc.nextInt();		 
		}
		ArrayList <Guest> g = new ArrayList<Guest> (); //initialise an ArrayList of Guest object
		int i;
		System.out.println("Please first enter the guest paying: "); //main guest that will be displayed in invoice when payment is made
		for(i=0; i<numOfGuest; i++){
			System.out.println("Please enter details for guest "+(i+1));
			Guest g1 = new Guest(); 
			g1 = CreateGuest.createGuest(g1); //calls static method createGuest of class CreateGuest that returns a new guest
			if(Database.reservationHM.containsKey(g1.getName())) { //check if this guest to see if he or she currently has a reservation
				return false;										//return false if this guest already has a reservation now
			}
			if(i==0)
				r.setMainGuestName(g1.getName());
			g.add(g1);						  //adds the Guest object into the Array List
			Database.guestHM.put(g1.getName(), g1); //add Guest object into database
		}
		r.setGuest(g);
		
		int year, month, day, time;
		Calendar calIn, calOut;
		Calendar check = Calendar.getInstance();
		while(true) {
			System.out.println("Enter check-in year, month and day with white space in between: "); //gets check in date, month and date from user
			year = sc.nextInt();
			month = sc.nextInt();
			day = sc.nextInt();
			do {
				System.out.println("Enter check-in time in 24 hours format: \n"
								 + "(Options are in hours from 1300 to 1800)"); //check in time is from 1pm to 6pm	
				time = sc.nextInt();
			}while(time<1300 || time>1800);
			time = time/100;
			calIn = Calendar.getInstance();
			calIn.set(year, month-1, day, time, 0);
			check.set(year, month-1, day, time+1, 0);
			if(check.after(Calendar.getInstance())) {
				break;
			}
			else
				System.out.println("Check-in time not available because it has already expired");
		}
		r.setCheckInDate(calIn);
		
		int year1,month1,day1;
		while(true) {
			System.out.println("Enter check-out year, month and day with white space in between: "); //gets check out date, month and date from user
			year1 = sc.nextInt();
			month1 = sc.nextInt();
			day1 = sc.nextInt();
			calOut = Calendar.getInstance();
			calOut.set(year1, month1-1, day1, 12, 0);	//check out time is fixed at 12 noon
			if(calOut.after(calIn))
				break;
			else
				System.out.println("Check-Out Date must be after Check-In Date");
		}
		r.setCheckOutDate(calOut);
		Room room = AssignRoom.assignRoom();
		String roomNumber;
		roomNumber = room.getRoomNumber();
		
		//check if this room has been reserved before or has a scheduled maintenance
		if(Database.dateRoomHM.containsKey(roomNumber) || Database.roomMaintenanceHM.containsKey(roomNumber)){		
			Calendar cal1 = Calendar.getInstance();				//initialise two Calendar objects to compare check in and check out dates
			Calendar cal2 = Calendar.getInstance();				//This is to check if the assigned room is vacant
																// between the given check-in and check-out date
			int count; 
			ArrayList<Calendar[]> calendarAL = new ArrayList <Calendar[]>(); 		//initialise an ArrayList of type Calendar Array
			ArrayList <Room> roomAL = new ArrayList<Room>();				 		//initialise an ArrayList of type Room Object
			ArrayList<Room.statuses> roomStatus = new ArrayList<Room.statuses>();	//initialise an ArrayList of type Room Status
			boolean hasMaintenance = false;
			while(true){
				count=0;
				roomAL.add(room);
				roomStatus.add(room.getStatus());
				room.setStatus(Room.statuses.CHECKING); 					//set room status to CHECKING (note that this is not CHECKED_IN)
				if(!Database.dateRoomHM.containsKey(roomNumber) && !Database.roomMaintenanceHM.containsKey(roomNumber))
					break;
				if(Database.dateRoomHM.containsKey(roomNumber))
					calendarAL = Database.dateRoomHM.get(roomNumber);		//returns Calendar Array from Database that store the room number and
				if(Database.roomMaintenanceHM.containsKey(roomNumber)) {
					hasMaintenance = true;
					calendarAL.add(Database.roomMaintenanceHM.get(roomNumber));
				}
				for(Calendar[] k: calendarAL) {							//the respective check in and check out date for this room
					cal1 = k[0];										//index 0 is check in date
					cal2 = k[1];										//index 1 is check out date
					//check if the check in date and check out date clashes with maintenance or previous reservation(if no clashes then count++)
					if((calIn.before(cal1) && calOut.before(cal1)) || calIn.after(cal2) && calOut.after(cal2))  
						count++;																				
				}																								
				if (count == calendarAL.size())						//the check in and check out date does not clash with all previous reservation
					break;												//room is available at that time, break out of while loop
				
				//room reserved date clashes with previous reservation made
				if (room instanceof Single) { 				//check room type to return same room type		
					roomNumber = SingleRooms.giveRoom();
					if (roomNumber.equals("All Checked")) { //all rooms are checked means that hotel is out of room at that time
						room = null;						//set room to null
						break;								//break out of while loop
					}
					room = SingleRooms.getRoomObject(roomNumber);
				}
				else if(room instanceof DoubleR) {					
					roomNumber = DoubleRooms.giveRoom();
					if (roomNumber.equals("All Checked")) {			
						room = null;								
						break;										
					}
					room = DoubleRooms.getRoomObject(roomNumber);
				}
				else if(room instanceof Deluxe) {
					roomNumber = DeluxeRooms.giveRoom();
					if (roomNumber.equals("All Checked")) {
						room = null;
						break;
					}
					room = DeluxeRooms.getRoomObject(roomNumber);
				}
				else {
					roomNumber = VIPRooms.giveRoom();
					if (roomNumber.equals("All Checked")) {
						room = null;
						break;
					}
					room = VIPRooms.getRoomObject(roomNumber);
				}
			}
			if(hasMaintenance)
				calendarAL.remove(calendarAL.size()-1);			//remove the maintenance period
			ArrayList <Calendar[]> calendarAL1 = new ArrayList<Calendar[]>();
			if(Database.dateRoomHM.containsKey(roomNumber)) {
				calendarAL1 = Database.dateRoomHM.get(roomNumber);
			}
			if(room != null) {								//ensure room is assigned
				Calendar[] calendar = new Calendar[2];
				calendar[0] = calIn;
				calendar[1] = calOut;
				room.setStatus(Room.statuses.RESERVED);		//set room status to RESERVED
				r.setRoom(room);							//reserve assigned room to current guest
				roomAL.remove(roomAL.size()-1);				//remove the last added room, which is also the assigned room
				roomStatus.remove(roomStatus.size()-1);		//remove the last room status
				calendarAL1.add(calendar);
				Database.dateRoomHM.put(roomNumber, calendarAL1); //store into database
			}
			else {
				Calendar[] calendar = new Calendar[2];
				calendar[0] = calIn;
				calendar[1] = calOut;
				r.setRoom(room);							//reserve assigned room to current guest
				roomAL.remove(roomAL.size()-1);				//remove the last added room, which is also the assigned room (null in this case)
				roomStatus.remove(roomStatus.size()-1);		//remove the last room status
				calendarAL1.add(calendar);
				Database.dateRoomHM.put(roomNumber, calendarAL1); //store into database
			}
			Room room1;
			while(roomAL.size() != 0) { 
				room1 = roomAL.get(0); 					
				room1.setStatus(roomStatus.get(0));	//set room status from CHECKING to previous status
				roomAL.remove(0);
				roomStatus.remove(0);
			}
		}
		else{
			r.setRoom(room);
			Calendar[] calendar = new Calendar[2];
			calendar[0] = calIn;
			calendar[1] = calOut;
			room.setStatus(Room.statuses.RESERVED);
			ArrayList<Calendar[]> calendarAL = new ArrayList<Calendar[]>();
			calendarAL.add(calendar);
			Database.dateRoomHM.put(roomNumber, calendarAL);
		}
			
		
		Random random; int reservationCode;
		random = new Random(System. nanoTime());
		reservationCode = random.nextInt(1000000000); 	//create a ReservationCode
		while (reservationCode>=1000000) {
			reservationCode = reservationCode/10;
		}
		r.setReservationCode(reservationCode);
		
		Calendar cal = Calendar.getInstance();	
		r.setReservationDate(cal);						//set reservation date
		
		Guest g1 = r.getGuest().get(0);
		if (g1.getCreditCard().getName() != "-") 		//if guest has credit card
			r.setBillingInfo("Credit Card");
		else
			r.setBillingInfo("Cash");					//else guest no credit card
		
		int numOfAdults = 0;
		for(i=0; i<g.size(); i++) {
			if(g.get(i).isAdult() == true) //calculate number of adults
				numOfAdults++;
		}
		r.setNumOfAdults(numOfAdults);
		
		int numOfChildren = g.size() - numOfAdults; //calculate number of children
		r.setNumOfChildren(numOfChildren);
		
		if (room == null) {																//no room assigned
			r.setReservationStatus(Reservation.ReservationStatus.In_Waitlist);			//set reservation status to In_Waitlist 
			System.out.println("Reservation is in waitlist because all rooms are occupied");
		}
		else {
			r.setReservationStatus(Reservation.ReservationStatus.Confirmed);			//room assigned, set reservation status to Confirmed
			System.out.println("Reservation has been booked sucessfully");
		}
		return true;
	}
}
