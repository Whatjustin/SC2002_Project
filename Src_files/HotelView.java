import java.io.FileNotFoundException;

/**
 * Viewer class for Hotel
 */
public class HotelView{

	/**
	 * Constructor for HotelView
	 * @throws FileNotFoundException
	 */
	public HotelView() throws FileNotFoundException {
	}

	/**
	 * Prints the room details: room type, room number, bed type, is wifi enabled, is there a view and status
	 * @param room Room
	 * @param roomType String
	 */
	public static void getRoomDetails(Room room, String roomType){
    	System.out.println("Room type: "+roomType);
        System.out.println("Room number: "+room.getRoomNumber());
        System.out.println("Bed type: "+room.getBedType());
        System.out.println("Is room wifi enabled?: "+room.isWifienabled());
        System.out.println("Is there a view? :"+room.isView());
        System.out.println("Status: "+room.getStatus());
    }

	/**
	 * Prints the occupancy rate of all room types
	 */
    public static void getOccupancyRate(){
        int empty=0, all=0, k=0;


		for (Single i : SingleRooms.roomsD ){
			if (i.getStatus()== Room.statuses.VACANT){
				empty++;
			}

		}
		all = SingleRooms.roomsD.size();
		System.out.println("Single:");
		System.out.println("Number: " + empty + " empty rooms out of " + all);
		for (Single i : SingleRooms.roomsD ){
			k++;
			if (k == all) 
				System.out.println(i.getRoomNumber());
			else
				System.out.print(i.getRoomNumber()+", ");
    	}

		empty = all = k = 0;


		for (DoubleR i : DoubleRooms.roomsD ){
			if (i.getStatus()== Room.statuses.VACANT){
				empty++;
			}

		}
		all = DoubleRooms.roomsD.size();
		System.out.println("Double:");
		System.out.println("Number: " + empty + " empty rooms out of " + all);
		for (DoubleR i : DoubleRooms.roomsD ){
			k++;
			if (k == all)
				System.out.println(i.getRoomNumber());
			else
				if(k==13)
					System.out.println();
				System.out.print(i.getRoomNumber()+", ");
    	}
		
		empty = all = k = 0;


		for (Deluxe i : DeluxeRooms.roomsD ){
			if (i.getStatus()== Room.statuses.VACANT){
				empty++;
			}

		}
		all = DeluxeRooms.roomsD.size();
		System.out.println("Deluxe:");
		System.out.println("Number: " + empty + " empty rooms out of " + all);
		for (Deluxe i : DeluxeRooms.roomsD ){
			k++;
			if (k == all)
				System.out.println(i.getRoomNumber());
			else
				System.out.print(i.getRoomNumber()+", ");
    	}
		
		empty = all = k = 0;

		for (VIP i : VIPRooms.roomsD ){
			if (i.getStatus()== Room.statuses.VACANT){
				empty++;
			}

		}
		all = VIPRooms.roomsD.size();
		System.out.println("VIP:");
        System.out.println("Number: " + empty + " empty rooms out of " + all);
        for (VIP i : VIPRooms.roomsD ){
			k++;
			if (k == all)
				System.out.println(i.getRoomNumber());
			else
				System.out.print(i.getRoomNumber()+", ");
    	}
        
    }

	/**
	 * Gets the room status of all room types
	 */
	public static void getRoomStatus() {
    	
    	int vacant = numberOfRooms(Room.statuses.VACANT);
    	int j = 0;
    	System.out.println("Vacant Rooms :");
    	if(vacant == 0) {
    		System.out.println("No Room Vacant.");
    	}
    	for(Single i : SingleRooms.roomsD) {
    		if(i.getStatus() == Room.statuses.VACANT) {
    			j++;
    			if(j==vacant){
    				System.out.println(i.getRoomNumber());
    				break;
    			}
    			if(j == 11 || j == 21 || j == 31 || j == 41)
    				System.out.println();
    			System.out.print(i.getRoomNumber()+", ");
    		}
    	}
    	for(DoubleR i : DoubleRooms.roomsD) {
    		if(i.getStatus() == Room.statuses.VACANT) {
    			j++;
    			if(j==vacant){
    				System.out.println(i.getRoomNumber());
    				break;
    			}
    			if(j == 11 || j == 21 || j == 31 || j == 41)
    				System.out.println();
    			System.out.print(i.getRoomNumber()+", ");
    		}
    	}
    	for(Deluxe i : DeluxeRooms.roomsD) {
    		if(i.getStatus() == Room.statuses.VACANT) {
    			j++;
    			if(j==vacant){
    				System.out.println(i.getRoomNumber());
    				break;
    			}
    			if(j == 11 || j == 21 || j == 31 || j == 41)
    				System.out.println();
    			System.out.print(i.getRoomNumber()+", ");
    		}
    	}
    	for(VIP i : VIPRooms.roomsD) {
    		if(i.getStatus() == Room.statuses.VACANT) {
    			j++;
    			if(j==vacant){
    				System.out.println(i.getRoomNumber());
    				break;
    			}
    			if(j == 11 || j == 21 || j == 31 || j == 41)
    				System.out.println();
    			System.out.print(i.getRoomNumber()+", ");
    		}
    	}
    	
    	int occupied = numberOfRooms(Room.statuses.OCCUPIED);
    	j = 0;
    	System.out.println();
    	System.out.println("Occupied Rooms:");
    	if(occupied == 0) {
    		System.out.println("No Room Occupied.");
    	}
    	for(Single i : SingleRooms.roomsD) {
    		if(i.getStatus() == Room.statuses.OCCUPIED) {
    			j++;
    			if(j==occupied){
    				System.out.println(i.getRoomNumber());
    				break;
    			}
    			if(j == 11 || j == 21 || j == 31 || j == 41)
    				System.out.println();
    			System.out.print(i.getRoomNumber()+", ");
    		}
    	}
    	for(DoubleR i : DoubleRooms.roomsD) {
    		if(i.getStatus() == Room.statuses.OCCUPIED) {
    			j++;
    			if(j==occupied){
    				System.out.println(i.getRoomNumber());
    				break;
    			}
    			if(j == 11 || j == 21 || j == 31 || j == 41)
    				System.out.println();
    			System.out.print(i.getRoomNumber()+", ");
    		}
    	}
    	for(Deluxe i : DeluxeRooms.roomsD) {
    		if(i.getStatus() == Room.statuses.OCCUPIED) {
    			j++;
    			if(j==occupied){
    				System.out.println(i.getRoomNumber());
    				break;
    			}
    			if(j == 11 || j == 21 || j == 31 || j == 41)
    				System.out.println();
    			System.out.print(i.getRoomNumber()+", ");
    		}	
    	}
    	for(VIP i : VIPRooms.roomsD) {
    		if(i.getStatus() == Room.statuses.OCCUPIED) {
    			j++;
    			if(j==occupied){
    				System.out.println(i.getRoomNumber());
    				break;
    			}
    			if(j == 11 || j == 21 || j == 31 || j == 41)
    				System.out.println();
    			System.out.print(i.getRoomNumber()+", ");
    		}
    	}
    	
    	int reserved = numberOfRooms(Room.statuses.RESERVED);
    	j = 0;
    	System.out.println();
    	System.out.println("Reserved Rooms:");
       	if(reserved == 0) {
 	   		System.out.println("No Room Reserved.");
       	}
 	   	for(Single i : SingleRooms.roomsD) {
       		if(i.getStatus() == Room.statuses.RESERVED) {
      			j++;
    		if(j==reserved){
    	  		System.out.println(i.getRoomNumber());
    	 			break;
    		}
    	    if(j == 11 || j == 21 || j == 31 || j == 41)
    	   		System.out.println();
    	   	System.out.print(i.getRoomNumber()+", ");
    	  	}
 	   	}
    	for(DoubleR i : DoubleRooms.roomsD) {
    	    if(i.getStatus() == Room.statuses.RESERVED) {
    	    	j++;
    	    	if(j==reserved){
    	    		System.out.println(i.getRoomNumber());
    	    		break;
    	    	}
    	  	if(j == 11 || j == 21 || j == 31 || j == 41)
    	    	System.out.println();
    	    	System.out.print(i.getRoomNumber()+", ");
    	    }
    	}
    	for(Deluxe i : DeluxeRooms.roomsD) {
    	    if(i.getStatus() == Room.statuses.RESERVED) {
    	    	j++;
    	    	if(j==reserved){
    	    		System.out.println(i.getRoomNumber());
    	    		break;
    	    	}
    	    	if(j == 11 || j == 21 || j == 31 || j == 41)
    	    		System.out.println();
    	    	System.out.print(i.getRoomNumber()+", ");
    	    }	
    	    	}
    	for(VIP i : VIPRooms.roomsD) {
    	    if(i.getStatus() == Room.statuses.RESERVED) {
    	    	j++;
    	    	if(j==reserved){
    	    		System.out.println(i.getRoomNumber());
    	    		break;
    	    	}
    	    	if(j == 11 || j == 21 || j == 31 || j == 41)
    	    		System.out.println();
    	    	System.out.print(i.getRoomNumber()+", ");
    		}
    	}
    	
    	int maintenance = numberOfRooms(Room.statuses.MAINTENANCE);
    	j = 0;
    	System.out.println();
    	System.out.println("Maintenance Rooms:");
       	if(maintenance == 0) {
 	   		System.out.println("No Room in Maintenance.");
       	}
 	   	for(Single i : SingleRooms.roomsD) {
       		if(i.getStatus() == Room.statuses.MAINTENANCE) {
      			j++;
    		if(j==maintenance){
    	  		System.out.println(i.getRoomNumber());
    	 			break;
    		}
    	    if(j == 11 || j == 21 || j == 31 || j == 41)
    	   		System.out.println();
    	   	System.out.print(i.getRoomNumber()+", ");
    	  	}
 	   	}
    	for(DoubleR i : DoubleRooms.roomsD) {
    	    if(i.getStatus() == Room.statuses.MAINTENANCE) {
    	    	j++;
    	    	if(j==maintenance){
    	    		System.out.println(i.getRoomNumber());
    	    		break;
    	    	}
    	  	if(j == 11 || j == 21 || j == 31 || j == 41)
    	    	System.out.println();
    	    	System.out.print(i.getRoomNumber()+", ");
    	    }
    	}
    	for(Deluxe i : DeluxeRooms.roomsD) {
    	    if(i.getStatus() == Room.statuses.MAINTENANCE) {
    	    	j++;
    	    	if(j==maintenance){
    	    		System.out.println(i.getRoomNumber());
    	    		break;
    	    	}
    	    	if(j == 11 || j == 21 || j == 31 || j == 41)
    	    		System.out.println();
    	    	System.out.print(i.getRoomNumber()+", ");
    	    }	
    	    	}
    	for(VIP i : VIPRooms.roomsD) {
    	    if(i.getStatus() == Room.statuses.MAINTENANCE) {
    	    	j++;
    	    	if(j==maintenance){
    	    		System.out.println(i.getRoomNumber());
    	    		break;
    	    	}
    	    	if(j == 11 || j == 21 || j == 31 || j == 41)
    	    		System.out.println();
    	    	System.out.print(i.getRoomNumber()+", ");
    		}
    	}
    }

	/**
	 * Gets total number of rooms of a particular status
	 * @param status Room.status
	 * @return int
	 */
    private static int numberOfRooms(Room.statuses status) {
		int number = 0;
		for(Single i : SingleRooms.roomsD) {
    		if(i.getStatus() == status)
    		number++;
    	}
		for(DoubleR i : DoubleRooms.roomsD) {
    		if(i.getStatus() == status)
    		number++;
    	}
		for(Deluxe i : DeluxeRooms.roomsD) {
    		if(i.getStatus() == status)
    		number++;
    	}
		for(VIP i : VIPRooms.roomsD) {
    		if(i.getStatus() == status)
    		number++;
    	}
    	return number;
    }
}
