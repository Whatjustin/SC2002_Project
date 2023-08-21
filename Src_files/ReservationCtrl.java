import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

/**
 * Interface for managing Reservation
 * @author Oo Yifei
 *
 */
public class ReservationCtrl {

	Scanner sc = new Scanner(System.in);
	
	/**
	 * constructor that connects to Admin
	 */
	public ReservationCtrl() {}
	/**
	 * Main method to interact with user
	 */
	public void reservation() {	
		int choice;
		int numOfGuest;
		do {
			System.out.println(
			"======Reservation=======\n"+
			"1. Create Reservation\n"+
			"2. Update Reservation\n"+
			"3. Remove Reservation\n"+
			"4. Print Reservation\n"+
			"5. Back\n"+
			"========================\n");
			choice = sc.nextInt();
			switch(choice) {
				case 1:													//creates a new reservation
					Guest g;
					ReservationView reservationView = new ReservationView();
					ArrayList <String> guestNameAL = new ArrayList<String>();
					Reservation r = new Reservation();
					boolean rSuccess = CreateReservation.createReservation(r);          //calls static method createReservation of CreateReservation class
					if(rSuccess) {
						numOfGuest = r.getNumOfAdults() + r.getNumOfChildren(); 			//that returns reservation
						g = new Guest();
						for (int i = 0; i<numOfGuest; i++) {
							g = r.getGuest().get(i);
							guestNameAL.add(g.getName());
							Database.reservationHM.put(g.getName(), r); 		//Stores data into the database
							Database.roomHM.put(g.getName(), r.getRoom());
						}
						Database.reservationCodeHM.put(r.getReservationCode(), r);
						Room room = r.getRoom();
						if(room != null) {
							reservationView.printReservationDetails(guestNameAL, room.getRoomNumber(), r.getReservationCode(), r.getReservationDate(),
								r.getBillingInfo(), r.getCheckInDate(), r.getCheckOutDate(), r.getNumOfAdults(), r.getNumOfChildren(), 
								r.getReservationStatus());						//Calls method printReservationDetails to display Reservation details
						}
						else
							reservationView.printReservationDetails(guestNameAL, "No Room Assigned", r.getReservationCode(), r.getReservationDate(),
									r.getBillingInfo(), r.getCheckInDate(), r.getCheckOutDate(), r.getNumOfAdults(), r.getNumOfChildren(), 
									r.getReservationStatus());
					}
					else {
						System.out.println("This guest already has a reservation");
					}
					break;
				case 2:
					String guestName;											//update a reservation
					sc.nextLine();
					System.out.println("Enter guest name to update reservation: ");
					guestName = sc.nextLine();
					guestName = guestName.toUpperCase();
					while(!Database.reservationHM.containsKey(guestName)){ 		//Check if guest is stored database
						System.out.println("Guest name not found.");
						System.out.println("Please enter guest name again or type -1 to exit: ");
						guestName = sc.nextLine();
						if (guestName.equals("-1")) break;
					}
					if(!guestName.equals("-1")) {
						Reservation r1 = new Reservation();
						r1 = Database.reservationHM.get(guestName); 			//Get Reservation object from Database
						UpdateReservation.updateReservation(r1);				//Calls static method updateReservation to update Reservation Status
						Database.reservationCodeHM.put(r1.getReservationCode(), r1);//updates database
						numOfGuest = r1.getNumOfAdults() + r1.getNumOfChildren(); 
						g = new Guest();
						for (int i = 0; i<numOfGuest; i++) {
							g = r1.getGuest().get(i);
							Database.reservationHM.put(g.getName(), r1); 		//updates database
							Database.roomHM.put(g.getName(), r1.getRoom());
						}
					}
					break;
				case 3:
					sc.nextLine();
					System.out.println("Enter guest name for reservation to be removed: "); //removing reservation
					guestName = sc.nextLine();
					guestName = guestName.toUpperCase();
					while(!Database.reservationHM.containsKey(guestName)){		//Check if guest is stored in database
						System.out.println("Guest name not found.");
						System.out.println("Please enter guest name again or type -1 to exit: ");
						guestName = sc.nextLine();
						if (guestName.equals("-1")) break;
					}
					if(!guestName.equals("-1")) {
						r = Database.reservationHM.get(guestName);					//Get Reservation object from Database
						Database.reservationCodeHM.remove(r.getReservationCode()); //Remove Reservation from all related Database
						Calendar checkInDate = r.getCheckInDate();
						numOfGuest = r.getNumOfAdults() + r.getNumOfChildren(); 
						g = new Guest();
						for (int i = 0; i<numOfGuest; i++) {
							g = r.getGuest().get(i);
							Database.reservationHM.remove(g.getName()); 		//remove all related data
							Database.roomHM.remove(g.getName());
						}
						if(r.getRoom() != null) {
							String roomNumber = r.getRoom().getRoomNumber();
							ArrayList<Calendar[]> calendarAL = new ArrayList<Calendar[]> ();
							calendarAL = Database.dateRoomHM.get(roomNumber);
							for (int j=0; j<calendarAL.size(); j++) {
								if (checkInDate.equals(calendarAL.get(j)[0])) {
									calendarAL.remove(j);						//make this room available again at that time
									break;
								}
							}
							Database.dateRoomHM.put(roomNumber, calendarAL);
							boolean isInMaintenance =false;
							if(Database.roomMaintenanceHM.containsKey(roomNumber)){
								Calendar[] cal1 = new Calendar[2];
								cal1[0] = Calendar.getInstance();
								cal1[1] = Calendar.getInstance();
								cal1[0] = Database.roomMaintenanceHM.get(roomNumber)[0];
								cal1[1] = Database.roomMaintenanceHM.get(roomNumber)[1];
								Calendar calNow = Calendar.getInstance();
								if (calNow.after(cal1[0]) && calNow.before(cal1[1])) {
									r.getRoom().setStatus(Room.statuses.MAINTENANCE);	//setting back the room status
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
									room1.setStatus(Room.statuses.MAINTENANCE);
									isInMaintenance = true;
								}
							}
							if(!isInMaintenance) {
								if(calendarAL.size()>0) {
									r.getRoom().setStatus(Room.statuses.RESERVED);
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
									room1.setStatus(Room.statuses.RESERVED);
								}
								else {
									r.getRoom().setStatus(Room.statuses.VACANT);
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
									room1.setStatus(Room.statuses.VACANT);
								}
							}
						}
						String mainGuest = r.getMainGuestName();
						Database.roomServiceHM.remove(mainGuest);
						System.out.println("Reservation removed succesfully");
						System.out.println();
					}
					break;
				case 4:
					ReservationView reservationView1 = new ReservationView();		//Display reservation details
					ArrayList<String> guestNameAL1 = new ArrayList<String> ();
					System.out.println("Select How Would You Like To Print: \n"+
								   "1. Print All Reservations\n"+
								   "2. Print Particular Reservation\n");
					int option = sc.nextInt();
					if (option == 1) {		
						for(Reservation i : Database.reservationCodeHM.values()) {	//Print all reservations made
							Calendar cal = Calendar.getInstance();
							cal = i.getCheckInDate();	
							int year1 = cal.get(Calendar.YEAR);
							int month1 = cal.get(Calendar.MONTH);
							int date1 = cal.get(Calendar.DATE);
							int time1 = cal.get(Calendar.HOUR_OF_DAY);
							cal.set(year1, month1, date1, time1+1, 0);			//add 1 hour to check in time
							if (cal.before(Calendar.getInstance())) {				//Check if reservation has expired
								if(i.getReservationStatus() != Reservation.ReservationStatus.Checked_in)
									i.setReservationStatus(Reservation.ReservationStatus.Expired);
								cal.set(year1, month1, date1, time1, 0);			//set date and time back to original
								ArrayList<Calendar[]> calendarAL = new ArrayList<Calendar[]>();
								if(i.getRoom()!=null) {
									calendarAL = Database.dateRoomHM.get(i.getRoom().getRoomNumber());
									for (int j=0; j<calendarAL.size(); j++) {
										if (cal.equals(calendarAL.get(j)[0])) {
											calendarAL.remove(j);						//make this room available again at that time
										}
									}
									Database.dateRoomHM.put(i.getRoom().getRoomNumber(), calendarAL);
									boolean isInMaintenance =false;
									if(Database.roomMaintenanceHM.containsKey(i.getRoom().getRoomNumber())) {
										Calendar[] cal1 = new Calendar[2];
										cal1[0] = Calendar.getInstance();
										cal1[1] = Calendar.getInstance();
										cal1[0] = Database.roomMaintenanceHM.get(i.getRoom().getRoomNumber())[0];
										cal1[1] = Database.roomMaintenanceHM.get(i.getRoom().getRoomNumber())[1];
										Calendar calNow = Calendar.getInstance();
										if (calNow.after(cal1[0]) && calNow.before(cal1[1])) {
											i.getRoom().setStatus(Room.statuses.MAINTENANCE);
											isInMaintenance = true;
											if(i.getRoom()!=null) {
												String roomNumber = i.getRoom().getRoomNumber();
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
												room1.setStatus(Room.statuses.MAINTENANCE);
											}
										}
									}
									if(!isInMaintenance) {
										if(calendarAL.size()>0) {
											i.getRoom().setStatus(Room.statuses.RESERVED);
											if(i.getRoom()!=null) {
												String roomNumber = i.getRoom().getRoomNumber();
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
												room1.setStatus(Room.statuses.RESERVED);
											}
										}
										else {
											i.getRoom().setStatus(Room.statuses.VACANT);
											if(i.getRoom()!=null) {
												String roomNumber = i.getRoom().getRoomNumber();
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
												room1.setStatus(Room.statuses.VACANT);
											}
										}
									}
								}
								if(i.getReservationStatus() == Reservation.ReservationStatus.Expired)
									continue;											//Expired reservation is not printed
							}
							cal.set(year1, month1, date1, time1, 0);				//set date and time back to original
							numOfGuest = i.getNumOfAdults() + i.getNumOfChildren();
							ArrayList <Guest> guestAL = new ArrayList <Guest>();
							guestAL = i.getGuest();									//add guest name into ArrayList
							for(int j=0; j<numOfGuest; j++) {
								guestNameAL1.add(guestAL.get(j).getName());
							}
							if (i.getRoom() == null) {
								reservationView1.printReservationDetails(guestNameAL1, "No room assigned", i.getReservationCode(), 
										i.getReservationDate(), i.getBillingInfo(), i.getCheckInDate(), i.getCheckOutDate(), i.getNumOfAdults(), 
										i.getNumOfChildren(), i.getReservationStatus());
							}
							else{reservationView1.printReservationDetails(guestNameAL1, i.getRoom().getRoomNumber(), i.getReservationCode(), 
									i.getReservationDate(), i.getBillingInfo(), i.getCheckInDate(), i.getCheckOutDate(), i.getNumOfAdults(), 
									i.getNumOfChildren(), i.getReservationStatus());
							}
							for (String name : guestNameAL1) {
								Database.reservationHM.put(name, i); 		//Stores updated data into the database
								Database.roomHM.put(name, i.getRoom());
							}
							Database.reservationCodeHM.put(i.getReservationCode(), i);
							while(guestNameAL1.size()!=0)
								guestNameAL1.remove(0);
						}
					}
					
					else if(option == 2){
						sc.nextLine();
					
						System.out.println("Please enter guest name for the reservation to be displayed: "); //Displaying only a specific reservation
						guestName = sc.nextLine();
						guestName = guestName.toUpperCase();
						while(!Database.reservationHM.containsKey(guestName)){							//Check if the guest is stored in Database
							System.out.println("Guest name not found.");
							System.out.println("Please enter guest name again or type -1 to exit: ");
							guestName = sc.nextLine();
							if (guestName.equals("-1")) break;
						}
						if(!guestName.equals("-1")) {
							r = Database.reservationHM.get(guestName);
							numOfGuest = r.getNumOfAdults() + r.getNumOfChildren();
							ArrayList <Guest> guestAL = new ArrayList <Guest>(); 
							guestAL = r.getGuest();				  //get array of Guest object
							ArrayList<String> guestNameAL2 = new ArrayList<String>(); //create an ArrayList to store guest name
							for(int i=0; i<numOfGuest; i++) {
								guestNameAL2 .add(guestAL.get(i).getName());
							}
							Calendar cal = Calendar.getInstance(); 
							cal = r.getCheckInDate();
							int year2 = cal.get(Calendar.YEAR);
							int month2 = cal.get(Calendar.MONTH);
							int date2 = cal.get(Calendar.DATE);
							int time2 = cal.get(Calendar.HOUR_OF_DAY);
							cal.set(year2, month2, date2, time2+1, 0); 						//add 1 hour to check in time
							if (cal.before(Calendar.getInstance())) {								//check if the reservation is expired
								cal.set(year2, month2, date2, time2, 0);						//set the date and time back to original
								if(r.getReservationStatus() != Reservation.ReservationStatus.Checked_in)	
									r.setReservationStatus(Reservation.ReservationStatus.Expired);	//Update Reservation Status if needed
								ArrayList<Calendar[]> calendarAL = new ArrayList<Calendar[]>();
								calendarAL = Database.dateRoomHM.get(r.getRoom().getRoomNumber());
								for (int j=0; j<calendarAL.size(); j++) {
									 if (cal.equals(calendarAL.get(j)[0]))
										calendarAL.remove(j);									//make this room available again at that time
									}
								Database.dateRoomHM.put(r.getRoom().getRoomNumber(), calendarAL);
								boolean isInMaintenance =false;
								if(Database.roomMaintenanceHM.containsKey(r.getRoom().getRoomNumber())) {
									Calendar[] cal1 = new Calendar[2];
									cal1[0] = Calendar.getInstance();
									cal1[1] = Calendar.getInstance();
									cal1[0] = Database.roomMaintenanceHM.get(r.getRoom().getRoomNumber())[0];
									cal1[1] = Database.roomMaintenanceHM.get(r.getRoom().getRoomNumber())[1];
									Calendar calNow = Calendar.getInstance();
									if (calNow.after(cal1[0]) && calNow.before(cal1[1])) {
										r.getRoom().setStatus(Room.statuses.MAINTENANCE);
										isInMaintenance = true;
										if(r.getRoom()!=null) {
											String roomNumber = r.getRoom().getRoomNumber();
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
											room1.setStatus(Room.statuses.MAINTENANCE);
										}
									}
								}
								if(!isInMaintenance) {
									if(calendarAL.size()>0) {
										r.getRoom().setStatus(Room.statuses.RESERVED);
										if(r.getRoom()!=null) {
											String roomNumber = r.getRoom().getRoomNumber();
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
											room1.setStatus(Room.statuses.RESERVED);
										}
									}
									else {
										r.getRoom().setStatus(Room.statuses.VACANT);
										if(r.getRoom()!=null) {
											String roomNumber = r.getRoom().getRoomNumber();
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
											room1.setStatus(Room.statuses.VACANT);
										}
									}
								}
							}
							cal.set(year2, month2, date2, time2, 0);							//set the date and time back to original
							if (r.getRoom() == null) {
								reservationView1.printReservationDetails(guestNameAL2, "No room assigned", r.getReservationCode(),
										r.getReservationDate(), r.getBillingInfo(), r.getCheckInDate(), r.getCheckOutDate(), r.getNumOfAdults(), 
										r.getNumOfChildren(), r.getReservationStatus());  //Display Reservation details
							}
							else {
								reservationView1.printReservationDetails(guestNameAL2, r.getRoom().getRoomNumber(), r.getReservationCode(), 
								r.getReservationDate(), r.getBillingInfo(), r.getCheckInDate(), r.getCheckOutDate(), r.getNumOfAdults(), 
								r.getNumOfChildren(), r.getReservationStatus()); //Display Reservation details
							}
							for (String name : guestNameAL2) {
								Database.reservationHM.put(name, r); 		//Stores updated data into the database
								Database.roomHM.put(name, r.getRoom());
							}
							Database.reservationCodeHM.put(r.getReservationCode(), r);
						}
					}
					break;
				case 5:
					break;
				default:
					System.out.println("Please enter 1, 2, 3, 4 or 5");
					break;
			}
		}while(choice != 5);
	}
}
