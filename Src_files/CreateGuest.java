import java.util.InputMismatchException;
import java.util.Scanner;

public class CreateGuest {

	public static Guest createGuest(Guest g) {
		
		Scanner scan = new Scanner(System.in);
		String name;
		String country;
		String nationality;
		String adult;
		String contact;
		String yesNo; //check whether the guest is paying with credit card
		int gender; // allow user to select gender
		int identity; // choose type of identity
		String identityID; //get input of identity number

		do{
			System.out.println("Enter guest name: ");
			name = scan.nextLine();
			name = name.toUpperCase();
			if(name.matches("[^0-9]+")){
				if(Database.guestHM.containsKey(name)) {								
					System.out.println("This guest is already in our database."); 
					System.out.println("Details retrieved");
					g = Database.guestHM.get(name);
					return g;							//return guest if found in database
				}
				g.setName(name);
				break;
			}
		} while(true);
		
		do {
			System.out.println("Enter number of selection for guest identity: \n"
					 + "1. NRIC\n"
					 + "2. Driving License\n"
					 + "3. Passport");
			try{
		        identity = scan.nextInt();
			}
			catch (InputMismatchException ex) {
				System.out.println("numbers only");
				scan.nextLine();
		        identity = scan.nextInt();		 
			}
		}while(identity<1 || identity>3);
		
		switch(identity){
			case 1:
				g.setIdentity(Guest.GuestIdentity.NRIC);
				break;
			case 2:
				g.setIdentity(Guest.GuestIdentity.Driving_License);
				break;
			case 3:
				g.setIdentity(Guest.GuestIdentity.Passport);
				break;
		}
		
		scan.nextLine();
		System.out.println("Enter selected identity number: ");
		identityID = scan.nextLine();
		g.setIdentityNo(identityID);
		
		do{
			System.out.println("Enter guest country: ");
			country = scan.nextLine();
			if (country.matches("[^0-9]+")){
				g.setCountry(country);
				break;
			}
		} while(true);
		System.out.println("Enter guest address: ");
		g.setAddress(scan.nextLine());
		do{
			System.out.println("Choose guest gender: \n" + "1. Male\n2. Female\n3. Others");
			try{
		        gender = scan.nextInt();
			}
			catch (InputMismatchException ex) {
				System.out.println("numbers only");
				scan.nextLine();
		        gender = scan.nextInt();		 
			}
		}while (gender<1 || gender>3);
		
		switch (gender){
			case 1: 
				g.setGender("Male");
				break;
			case 2: 
				g.setGender("Female");
				break;
			case 3: 
				g.setGender("Others");
				break;
		}
		scan.nextLine();
		do{
			System.out.println("Enter guest nationality: ");
			nationality = scan.nextLine();
			if (nationality.matches("[^0-9]+")){
				g.setNationality(nationality);
				break;
			}
		} while (true);
		do{
			System.out.println("Enter guest contact: ");
			contact = scan.nextLine();
			if(contact.matches("[^a-zA-Z,]+")){
				g.setContact(contact);
				break;
			}
		} while(true);
		do{
			System.out.println("Is this an adult? (Y/N) "); //check whether the guest is an adult
			adult = scan.nextLine();
			if (adult.matches("[yY ]")) {
				g.setAdult(true);
				break;
			}
			else if(adult.matches("[nN ]")) {
				g.setAdult(false);
				break;
			}
		} while(true);
		do{
			System.out.println("Is guest paying with credit card? (Y/N)");
			yesNo = scan.nextLine();
			yesNo = yesNo.toUpperCase();
			if (yesNo.matches("[nNyY ]")) {
				GuestCreditCard creditCard = new GuestCreditCard();
				new CreateCreditCard(creditCard, yesNo);
				g.setCreditCard(creditCard);
				break;
			}
		} while(true);
		return g;
	}
}
