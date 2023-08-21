import java.util.Calendar;
import java.util.Scanner;

/**
 * Interface to Manage Hotel
 * @author Oo Yifei
 *
 */
public class Staff {
	
	int choice;
	Scanner sc = new Scanner(System.in);
	
	/**
	 * constructor that connects to HotelApp
	 */
	public Staff() {}
	
	/**
	 * Method to interact with user
	 */
	public void staff() {
		
		do {
			System.out.println("===========Staff===========\n"+
							   "1. Update Weekend Rate\n"+
							   "2. Update Holiday Period\n"+
							   "3. Update Holiday Rate\n"+
							   "4. Change Food Menu\n"+
							   "5. Update Room Status\n"+
							   "6. Room Status Statistic Report\n"+
							   "7. Back\n"+
							   "===========================");
			choice = sc.nextInt();
			switch(choice) {
				case 1:
					System.out.println("Current Weekend Rate: "+PriceChange.getWeekendRate()); //Calls static method to display current Weekend Rate
					System.out.println("Enter New Weekend Rate:");
					Double weekendRate = sc.nextDouble();										
					PriceChange.setWeekendRate(weekendRate);								   //Calls static method to change Weekend Rate
					System.out.println("Weekend Rate Updated Successfully to "+ weekendRate);
					System.out.println();
					break;
				case 2:
					System.out.println("Current Holiday Period: \n"+
									   "Year       |"+ PriceChange.getHolidayYear()+"\n"+		//Calls static methods to display current 
									   "Month      |"+ (PriceChange.getHolidayMonth()+1)+"\n"+	//Holiday Promotion Period
									   "Start Date |"+ PriceChange.getHolidayStartDate()+"\n"+
									   "End Date   |"+ PriceChange.getHolidayEndDate());
					System.out.println("Enter New Holiday Year, Month, Start Date, End Date with space in between: ");
					int year = sc.nextInt();
					int month = sc.nextInt();
					int startDate = sc.nextInt();
					int endDate = sc.nextInt();
					PriceChange.setHolidayPeriod(year, month-1, startDate, endDate);			//Calls static method to change Holiday Promotion Period
					System.out.println("Holiday Period Updated Successfully!");
					System.out.println();
					break;
				case 3:
					System.out.println("Current Holiday Rate: "+ PriceChange.getHolidayRate());//Calls static method to display current
					System.out.println("Enter New Holiday Rate: ");							   //Holiday Rate
					Double holidayRate = sc.nextDouble();
					PriceChange.setHolidayRate(holidayRate);								    //Calls static method to change Holiday Rate
					System.out.println("Holiday Rate Updated Successfully to " + holidayRate);
					System.out.println();
					break;
				case 4:
					MenuCtrl.menuCtrl();				//Calls static method menuCtrl to edit Hotel Menu
					System.out.println();
					break;
				case 5:
					System.out.println("Enter Room Number for Status to be changed: ");
					sc.nextLine();
					String roomNumber = sc.nextLine();
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
				int update;
				do {
						System.out.println("Current Room Status: "+room.getStatus());
						System.out.println("Chooce a status to update: \n"+
								           "1. Vacant\n"+
								           "2. Reserved\n"+
										   "3. Maintenance\n"+
								           "4. Cancel");
						update = sc.nextInt();
						switch(update) {
							case 1:
								room.setStatus(Room.statuses.VACANT);							//Set Reservation Status to VACANT
								System.out.println("Room Status Updated to VACANT");
								break;
							case 2:
								room.setStatus(Room.statuses.RESERVED);							//Set Reservation Status to RESERVED
								System.out.println("Room Status Updated to RESERVED");
								break;
							case 3:
								Calendar cal[] = new Calendar[2];
								cal[0] = Calendar.getInstance();
								cal[1] = Calendar.getInstance();
								System.out.println("Enter start date of room maintenance");
								System.out.println("Format: YEAR MONTH DAY");
								int yearM = sc.nextInt();
								int monthM = sc.nextInt();
								int dayM = sc.nextInt();
								cal[0].set(yearM, monthM-1, dayM, 0, 0);
								System.out.println("Enter end date of room maintenance");
								System.out.println("Format: YEAR MONTH DAY");
								yearM = sc.nextInt();
								monthM = sc.nextInt();
								dayM = sc.nextInt();
								cal[1].set(yearM, monthM-1, dayM, 23, 59);
								Database.roomMaintenanceHM.put(roomNumber, cal);			//stores maintenance period into database
								Calendar calNow = Calendar.getInstance();
								if(calNow.after(cal[0]) && calNow.before(cal[1])) {			//check to see if the period is now
									room.setStatus(Room.statuses.MAINTENANCE);
								}
								System.out.println("Room Status will be updated at time period");
								break;
							case 4:
								System.out.println("Request cancelled");
								break;
							default:
								System.out.println("Enter 1,2 or 3");
								break;
						}
					}while(update<1 || update>4);
					System.out.println();
					break;
				case 6:
					System.out.println("Select Display Option: \n"+
							   "1. Room Type Occupancy Rate: \n"+
							   "2. Room Status");
					int option = sc.nextInt();
					switch(option) {
						case 1:
							HotelView.getOccupancyRate(); //calls static method to print report
							break;
						case 2:
							HotelView.getRoomStatus(); //calls static method to print report
							break;
						default:
							break;
					}
					break;
				case 7:
					System.out.println("Exiting...");
					System.out.println();
					break;
				default:
					System.out.println("Please enter 1,2,3,4,5,6 or 7");
					break;
			}
		}while(choice != 7);
	}
}
