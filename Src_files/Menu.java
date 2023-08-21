import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.List;

/**
 * Menu class, which stores an array of food items
 */
public class Menu {
	

	/**
	 * food item menu
	 */
	static ArrayList<FoodItem> menu = new ArrayList<FoodItem>();  // need to be package access so that orderFood class can access

	/**
	 * adds a food item into menu
	 * @param food
	 */
	public static void addItem(FoodItem food) {
		menu.add(food);
	}
	
	/**
	 * removes food item from menu
	 */
	public static void removeItem(){
		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();
		if (choice < 1 || choice > menu.size()) {
			System.out.println("Food Item not found!");
			return;
		}
		menu.remove(choice-1);
		System.out.println("Food Item successfully removed!");
	}
	
	/**
	 * prints menu
	 */
	public static void printMenu() {
		for (int i=0; i<menu.size(); i++) {
			FoodItem food = menu.get(i);
			System.out.println((i+1) + ")");
			System.out.println("Item Name     | " + food.getItemName());
			System.out.println("Price         | " + "$" + food.getItemPrice());
			System.out.println("Description   | " + food.getDescription() + "\n");
		}
	}

	/**
	 * updates menu item
	 */
	public static void updateItem() {
		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();
		if (choice < 1 || choice > menu.size()) {
			System.out.println("Food Item not found!");
			return;
		}
		FoodItem food = menu.get(choice-1);
		System.out.println("Item Details: \n"+
						   "Item Name         | "+food.getItemName()+"\n"+
						   "Description       | "+food.getDescription()+"\n"+
						   "Price             | "+food.getItemPrice()+"\n");
		int update;
		do {
			System.out.println("Select which to update: \n"+
					   "1. Item Name \n"+
					   "2. Item Description\n"+
					   "3. Item Price\n"+
					   "4. Back\n");
			update = sc.nextInt();
			switch(update) {
				case 1:
					System.out.println("Enter New Item Name: ");
					sc.nextLine();
					String foodName; 
					foodName = sc.nextLine();
					food.setItemName(foodName);
					break;
				case 2:
					System.out.println("Enter New Item Description: ");
					sc.next();
					String description = sc.nextLine();
					food.setDescription(description);
					break;
				case 3:
					System.out.println("Enter New Item Price: ");
					double price = sc.nextDouble();
					food.setPrice(price);
					break;
				case 4:
					System.out.println("Exiting...");
					break;
				default:
					System.out.println("Please enter 1,2,3 or 4");
					break;
			}
		}while(update != 4);
	}
	
	/**
	 * static method to read menu
	 * @throws IOException
	 */
	public static void readMenu() throws IOException {
		// read String from text file
		final String SEPARATOR = "|";
		ArrayList stringArray = (ArrayList)read();
		ArrayList alr = new ArrayList() ;// to store Professors data

	    for (int i = 0 ; i < stringArray.size() ; i++) {
			String st = (String)stringArray.get(i);
			// get individual 'fields' of the string separated by SEPARATOR
			StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","

			String  name = star.nextToken().trim();	// first token
			String  description = star.nextToken().trim();	// second token
			double  price = Double.parseDouble(star.nextToken().trim()); // third token
			// create Professor object from file data
			FoodItem prof = new FoodItem(name, description, price);
			// add to Professors list
			alr.add(prof) ;
			}
		menu = alr;
	}
	
	/**
	 * read menu.txt file into List object
	 * @return List
	 * @throws IOException
	 */
	private static List read() throws IOException {
		List data = new ArrayList() ;
	    Scanner scanner = new Scanner(new FileInputStream("menu.txt"));
	    try {
	      while (scanner.hasNextLine()){
	        data.add(scanner.nextLine());
	      }
	    }
	    finally{
	      scanner.close();
	    }
	    return data;
	  }
	
	/**
	 * static method to save menu into file
	 * @param List of FoodItem object
	 * @throws IOException
	 */
	public static void saveMenu(List al) throws IOException {
		final String SEPARATOR = "|";
		List alw = new ArrayList() ;// to store Professors data

        for (int i = 0 ; i < al.size() ; i++) {
				FoodItem food = (FoodItem)al.get(i);
				StringBuilder st =  new StringBuilder() ;
				st.append(food.getItemName().trim());
				st.append(SEPARATOR);
				st.append(food.getDescription().trim());
				st.append(SEPARATOR);
				st.append(food.getItemPrice());
				alw.add(st.toString()) ;
			}
			write("menu.txt",alw);
	}

	/**
	 * write fixed content to the given file
	 * @param fileName
	 * @param data
	 * @throws IOException
	 */
	public static void write(String fileName, List data) throws IOException  {
		PrintWriter out = new PrintWriter(new FileWriter(fileName));

	    try {
			for (int i =0; i < data.size() ; i++) {
	      		out.println((String)data.get(i));
			}
	    }
	    finally {
	      out.close();
	    }
    }
	
	
}
