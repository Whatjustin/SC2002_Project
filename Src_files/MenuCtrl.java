import java.util.Scanner;

/**
 * Interface for changing hotel menu
 * @author Oo Yifei
 *
 */
public class MenuCtrl {

	/**
	 * Static method to take input of user's request
	 */
	public static void menuCtrl() {
		Scanner sc = new Scanner(System.in);
		int choice;
		do {	
			System.out.println("Current Menu: ");
			Menu.printMenu();
			System.out.println("Menu Option: \n"+
					   "1. Add Item\n"+
					   "2. Remove Item\n"+
					   "3. Change Item\n"+
					   "4. Back\n");
			choice = sc.nextInt();
			switch(choice) {
				case 1:
					sc.nextLine();
					System.out.println("Enter Item Name:");
					String name = sc.nextLine();
					System.out.println("Enter Description:");
					String description = sc.nextLine();
					System.out.println("Enter Item Price:");
					double price = sc.nextDouble();
					FoodItem food = new FoodItem(name, description, price);
					Menu.addItem(food); //calls method to add item
					break;
				case 2:
					System.out.println("Enter item's number to be removed: ");
					Menu.removeItem(); //calls method to remove item
					break;
				case 3:
					System.out.println("Enter item's number to be changed: ");
					Menu.updateItem(); //calls method to update item
					break;
				case 4:
					System.out.println("Exiting... ");
					break;
				default:
					System.out.println("Please Enter 1,2 or 3");
					break;
			}
		}while (choice != 4);
	}
}
