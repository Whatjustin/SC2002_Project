import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Scanner;

/**
 * To Update Reservation Status
 * @author Oo Yifei
 *
 */
public class UpdateReservation {

	static Scanner sc = new Scanner(System.in);
	
	/**
	 * Static method to update Reservation Status
	 * @param r Reservation object to be updated
	 */
	public static void updateReservation(Reservation r) {
		int choice;
		do {
			System.out.println(
				"======ReservationStatus======\n"+ 
				"1. Change check-in date\n"+
				"2. Change check-out date\n"+
				"3. Assign room\n"+					//assigns a room to guest without a room
				"4. Back\n"+
				"=============================");
			choice = sc.nextInt();
			switch(choice) {
				case 1:														//change check-in date
					System.out.println("Current check-in date: ");
					Calendar checkInDate = Calendar.getInstance();
					checkInDate = r.getCheckInDate();
					Calendar checkOutDate = Calendar.getInstance();
					checkOutDate = r.getCheckOutDate();
					int checkInYear = checkInDate.get(Calendar.YEAR);
					int checkInMonth = checkInDate.get(Calendar.MONTH);
					int checkInDay = checkInDate.get(Calendar.DATE);
					int time = checkInDate.get(Calendar.HOUR_OF_DAY);
					System.out.println(checkInDay+"-"+(checkInMonth+1)+"-"+checkInYear);
					System.out.println("Enter new check-in date in year, month and day with white space in between: ");
					int checkInYear1 = sc.nextInt(); int checkInMonth1 = sc.nextInt(); int checkInDay1 = sc.nextInt();
					int time1;
					do {
						System.out.println("Enter check-in time in 24 hours format: \n"
										 + "(Options are in hours from 1300 to 1800)"); //check in time is from 1pm to 6pm	
						time1 = sc.nextInt();
					}while(time1<1300 || time1>1800);
					time1 = time1/100;
					checkInDate.set(checkInYear1, checkInMonth1-1, checkInDay1, time1, 0); //set new check in date
					if(r.getRoom()!=null) {
						//check to see if this room has other reservation or any maintenance
						String roomNumber = r.getRoom().getRoomNumber();
						if(Database.dateRoomHM.containsKey(roomNumber) || Database.roomMaintenanceHM.containsKey(roomNumber)){		
							Calendar cal1 = Calendar.getInstance();				//initialise two Calendar objects to compare check in and check out dates
							Calendar cal2 = Calendar.getInstance();				//This is to check if the date changed does not clashes with other reservation
							int count = 0; 
							ArrayList<Calendar[]> calendarAL = new ArrayList <Calendar[]>(); 		//initialise an ArrayList of type Calendar Array
							boolean hasMaintenance = false;
							if(Database.dateRoomHM.containsKey(roomNumber))
								calendarAL = Database.dateRoomHM.get(roomNumber);		//returns Calendar Array from Database that store the room number and
							if(Database.roomMaintenanceHM.containsKey(roomNumber)) {
								hasMaintenance = true;
								calendarAL.add(Database.roomMaintenanceHM.get(roomNumber));
							}
							for (int j=0; j<calendarAL.size(); j++) {
								if (checkOutDate.equals(calendarAL.get(j)[1])) {
									calendarAL.remove(j);							//delete the original check in and check out date
								}
							}
							for(Calendar[] k: calendarAL) {							//the respective check in and check out date for this room
								cal1 = k[0];										//index 0 is check in date
								cal2 = k[1];										//index 1 is check out date
								//check if the check in date and check out date clashes with previous reservation (if no clashes then count++)
								if((checkInDate.before(cal1) && checkOutDate.before(cal1)) || checkInDate.after(cal2) && checkOutDate.after(cal2))  
									count++;																				
							}
							if (count == calendarAL.size()) {		//the check in and check out date does not clash with all previous reservation
								if(hasMaintenance)
									calendarAL.remove(calendarAL.size()-1);
								r.setCheckInDate(checkInDate);
								Calendar[] calA = new Calendar[2];
								calA[0] = checkInDate;
								calA[1] = checkOutDate;
								calendarAL.add(calA);					//add the new date into the array list
								Database.dateRoomHM.put(roomNumber, calendarAL);	//stores the new date into database
								System.out.println("Check-in date updated");
								System.out.println();												//room is available at that time, break out of while loop
							}
							else {
								if(hasMaintenance)
									calendarAL.remove(calendarAL.size()-1);
								checkInDate.set(checkInYear, checkInMonth, checkInDay, time, 0); //set to the old check in Date
								Calendar[] calA = new Calendar[2];
								calA[0] = checkInDate;
								calA[1] = checkOutDate;
								calendarAL.add(calA);					//add the old date into the array list
								Database.dateRoomHM.put(roomNumber, calendarAL);	//stores the old date into database
								System.out.println("Check-in date clashes with other reservation or maintenance");
								System.out.println("Please try other dates");
							}
						}
						else {
							r.setCheckInDate(checkInDate);				//room number is not used before, any check in date is allowed
							System.out.println("Check-in date updated");
							System.out.println();
						}
					}
					else {
						r.setCheckInDate(checkInDate);					//no room yet, any check in date allowed
						System.out.println("Check-in date updated");
						System.out.println();
					}
					break;
				case 2:
					System.out.println("Current check-out date: ");
					Calendar checkInDate1 = Calendar.getInstance();
					checkInDate1 = r.getCheckInDate();
					Calendar checkOutDate1 = Calendar.getInstance(); 
					checkOutDate1 = r.getCheckOutDate();
					int checkOutYear = checkOutDate1.get(Calendar.YEAR);
					int checkOutMonth = checkOutDate1.get(Calendar.MONTH);
					int checkOutDay = checkOutDate1.get(Calendar.DATE);
					System.out.println(checkOutDay+"-"+(checkOutMonth+1)+"-"+checkOutYear);
					System.out.println("Enter new check-out date in year, month and day with white space in between: ");
					int checkOutYear1 = sc.nextInt(); int checkOutMonth1 = sc.nextInt(); int checkOutDay1 = sc.nextInt();
					checkOutDate1.set(checkOutYear1, checkOutMonth1-1, checkOutDay1, 12, 0); //set to set check-out date
					if(r.getRoom()!=null) {
						//check to see if this room has other reservation or any maintenance
						String roomNumber = r.getRoom().getRoomNumber();
						if(Database.dateRoomHM.containsKey(roomNumber) || Database.roomMaintenanceHM.containsKey(roomNumber)){		
							Calendar cal1 = Calendar.getInstance();				//initialise two Calendar objects to compare check in and check out dates
							Calendar cal2 = Calendar.getInstance();				//This is to check if the date changed does not clashes with other reservation
							int count = 0; 
							ArrayList<Calendar[]> calendarAL = new ArrayList <Calendar[]>(); 		//initialise an ArrayList of type Calendar Array
							boolean hasMaintenance = false;
							if(Database.dateRoomHM.containsKey(roomNumber))
								calendarAL = Database.dateRoomHM.get(roomNumber);		//returns Calendar Array from Database that store the room number and
							if(Database.roomMaintenanceHM.containsKey(roomNumber)) {
								hasMaintenance = true;
								calendarAL.add(Database.roomMaintenanceHM.get(roomNumber));
							}
							for (int j=0; j<calendarAL.size(); j++) {
								if (checkInDate1.equals(calendarAL.get(j)[0])) {
									calendarAL.remove(j);							//delete the original check in and check out date
								}
							}
							for(Calendar[] k: calendarAL) {							//the respective check in and check out date for this room
								cal1 = k[0];										//index 0 is check in date
								cal2 = k[1];										//index 1 is check out date
								//check if the check in date and check out date clashes with previous reservation (if no clashes then count++)
								if((checkInDate1.before(cal1) && checkOutDate1.before(cal1)) || checkInDate1.after(cal2) && checkOutDate1.after(cal2))  
									count++;																				
							}
							if (count == calendarAL.size()) {		//the check in and check out date does not clash with all previous reservation
								if(hasMaintenance)
									calendarAL.remove(calendarAL.size()-1);
								r.setCheckOutDate(checkOutDate1);
								Calendar[] calA = new Calendar[2];
								calA[0] = checkInDate1;
								calA[1] = checkOutDate1;
								calendarAL.add(calA);					//add the new date into the array list
								Database.dateRoomHM.put(roomNumber, calendarAL);	//stores the new date into database
								System.out.println("Check-out date updated");
								System.out.println();												//room is available at that time, break out of while loop
							}
							else {
								if(hasMaintenance)
									calendarAL.remove(calendarAL.size()-1);
								checkOutDate1.set(checkOutYear, checkOutMonth, checkOutDay, 12, 0); //set to the old check out Date
								Calendar[] calA = new Calendar[2];
								calA[0] = checkInDate1;
								calA[1] = checkOutDate1;
								calendarAL.add(calA);					//add the old date into the array list
								Database.dateRoomHM.put(roomNumber, calendarAL);	//stores the old date into database
								System.out.println("Check-out date clashes with other reservation or maintenance");
								System.out.println("Please try other dates");
							}
						}
						else {
							r.setCheckInDate(checkOutDate1);				//this room number is not used before, any check out date allowed
							System.out.println("Check-out date updated");
							System.out.println();
						}
					}
					else {
						r.setCheckInDate(checkOutDate1);				//no room yet, any check out date allowed
						System.out.println("Check-out date updated");
						System.out.println();
					}
					break;
				case 3:													//assigning a room
					if(r.getRoom()!=null) {
						System.out.println("This guest already has a room");	
						break;											//return if the guest already has a room
					}
					Calendar calIn = Calendar.getInstance();
					Calendar calOut = Calendar.getInstance();
					calIn = r.getCheckInDate();
					calOut = r.getCheckOutDate();
					Room room = AssignRoom.assignRoom();				//get room object (single/double/deluxe/VIP
					String roomNumber = room.getRoomNumber();
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
								//check if the check in date and check out date clashes with previous reservation(if no clashes then count++)
								if((calIn.before(cal1) && calOut.before(cal1)) || calIn.after(cal2) && calOut.after(cal2))  
									count++;																				
							}																							
							if (count == calendarAL.size())		//the check in and check out date does not clash with all previous reservation
								break;														//room is available at that time, break out of while loop
							
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
							calendarAL.remove(calendarAL.size()-1);					//remove the maintenance period
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
						r.setRoom(room);							//this number is not used before, this room is surely vacant at that time
						Calendar[] calendar = new Calendar[2];
						calendar[0] = calIn;
						calendar[1] = calOut;
						room.setStatus(Room.statuses.RESERVED);
						ArrayList<Calendar[]> calendarAL = new ArrayList<Calendar[]>();
						calendarAL.add(calendar);
						Database.dateRoomHM.put(roomNumber, calendarAL);
					}
					if (room != null) {						//room found and assigned successfully
						r.setReservationStatus(Reservation.ReservationStatus.Confirmed);
						System.out.println("Room Assigned succesfully");
						System.out.println();
					}
					else									
						System.out.println("No vacant rooms at that time");
						System.out.println();
					break;
				case 4:
					break;
				default:
					System.out.println("Please enter 1,2,3 or 4");
					break;
			}
		}while(choice != 4);
	}
}
