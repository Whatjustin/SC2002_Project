import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * Starting interface of the program
 * @author Oo Yifei
 *
 */
public class HotelApp {
	/**
	 * Static main method
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		int choice;

		Database.readFile();
		Menu.readMenu();
		PriceChange.readPrice();
		SingleRooms Sroom = new SingleRooms();
		DeluxeRooms Droom = new DeluxeRooms();
		DoubleRooms DDroom = new DoubleRooms();
		VIPRooms roomvip = new VIPRooms();
		System.out.println("Welcome to IMPEL DOWN");
		System.out.println(" ___  _____ ______   ________  _______   ___               ________  ________  ___       __   ________      \n" +
				"|\\  \\|\\   _ \\  _   \\|\\   __  \\|\\  ___ \\ |\\  \\             |\\   ___ \\|\\   __  \\|\\  \\     |\\  \\|\\   ___  \\    \n" +
				"\\ \\  \\ \\  \\\\\\__\\ \\  \\ \\  \\|\\  \\ \\   __/|\\ \\  \\            \\ \\  \\_|\\ \\ \\  \\|\\  \\ \\  \\    \\ \\  \\ \\  \\\\ \\  \\   \n" +
				" \\ \\  \\ \\  \\\\|__| \\  \\ \\   ____\\ \\  \\_|/_\\ \\  \\            \\ \\  \\ \\\\ \\ \\  \\\\\\  \\ \\  \\  __\\ \\  \\ \\  \\\\ \\  \\  \n" +
				"  \\ \\  \\ \\  \\    \\ \\  \\ \\  \\___|\\ \\  \\_|\\ \\ \\  \\____        \\ \\  \\_\\\\ \\ \\  \\\\\\  \\ \\  \\|\\__\\_\\  \\ \\  \\\\ \\  \\ \n" +
				"   \\ \\__\\ \\__\\    \\ \\__\\ \\__\\    \\ \\_______\\ \\_______\\       \\ \\_______\\ \\_______\\ \\____________\\ \\__\\\\ \\__\\\n" +
				"    \\|__|\\|__|     \\|__|\\|__|     \\|_______|\\|_______|        \\|_______|\\|_______|\\|____________|\\|__| \\|__|");
		System.out.println("");
		do{
			System.out.println(
				"=================Hotel Reservation and Payment System=================\n"+
				"1. Admin (Guest, Reservation, Room Details)\n"+
				"2. Staff (Rates, Holiday Periods, Room Status, Statistic Report, Menu)\n"+
				"3. Shut down\n"+
				"======================================================================\n");
			System.out.println("Please enter number of your selection: ");
			try{
		        choice = sc.nextInt();
			}
			catch (InputMismatchException ex) {
				System.out.println("numbers only");
				sc.nextLine();
		        choice = sc.nextInt();		 
			}
			switch(choice) {
				case 1:
					Admin ad = new Admin(); //construct Admin to manage guest, reservation and rooms
					ad.admin();
					break;
				case 2:
					Staff staff = new Staff(); //construct Staff to manage rates, holidays, Room Status, hotel menu
					staff.staff();
					break;
				case 3:
					System.out.println("Thank you for using Hotel Reservation and Payment System");
					System.out.println("Shutting down now...");
					Database.writeFile();
					Menu.saveMenu(Menu.menu);
					PriceChange.writePrice();
					SingleRooms.saveroom();
					DoubleRooms.saveroom();
					DeluxeRooms.saveroom();
					VIPRooms.saveroom();
					break;
				default:
					System.out.println("Please Enter 1,2 or 3");
					break;
			}
		} while (choice != 3);
		
	}

}
