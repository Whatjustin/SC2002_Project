import java.util.Scanner;

/**
 * Returns a room object based on the room type requested
 * @author Oo Yifei
 *
 */
public class AssignRoom {
	
	/**
	 * Static method to return the room object
	 * @return room object
	 */
	public static Room assignRoom() {
		int choice;
		Scanner sc = new Scanner(System.in);
		Room room;
		String roomID;
		
		System.out.println("Choose Room Type: ");
		System.out.println("1. Single Room");
		System.out.println("2. Double Room");
		System.out.println("3. Deluxe Room");
		System.out.println("4. VIP Room");
		do {
			System.out.println("Please enter number of your selection: ");
			choice = sc.nextInt();
		}while(choice<1 || choice>4);
		
		switch(choice) {
			case 1:
				roomID = SingleRooms.giveRoom();
				room = SingleRooms.getRoomObject(roomID); //returns a single room
				return room;
			case 2:
				roomID = DoubleRooms.giveRoom();
				room = DoubleRooms.getRoomObject(roomID); //returns a double room
				return room;
			case 3:
				roomID = DeluxeRooms.giveRoom();
				room = DeluxeRooms.getRoomObject(roomID); //returns a deluxe room
				return room;
			case 4:
				roomID = VIPRooms.giveRoom();
				room = VIPRooms.getRoomObject(roomID); //returns a vip room
				return room;
		}
		return null;
	}
}
