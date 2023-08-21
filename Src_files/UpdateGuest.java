import java.util.Scanner;
/**
 * This UpdateGuest class is to update the guest particulars 
 */
public class UpdateGuest {
	/**
	 * The method is to update the guest particulars 
	 * and store it back to the database
	 * @param g (Guest)
	 * @return guest name
	 */
	public static String updateGuest(Guest g){
		Scanner scan = new Scanner(System.in);
		int update; 
		int gender;
		int identity;
		String country;
		String contact;
		String ccName;
		String bankName;
		String nationality;
		
		do {
			System.out.println("==================================================");
			System.out.println("Enter the choice of details you want to update: ");
			System.out.println("1. Name\n"
									 	+ "2. Address\n"
									 	+ "3. Country\n"
									 	+ "4. Gender\n"
									 	+ "5. Identity\n"
									 	+ "6. Identity No.\n"
									 	+ "7. Nationality\n"
									 	+ "8. Contact\n"
									 	+ "9. Credit Card\n"
									 	+ "10. Back\n");
			update = scan.nextInt();
			scan.nextLine();
			switch (update) {
				case 1: 
					System.out.println("Current Name: " + g.getName());
					do{
						System.out.println("Enter the name you want to update:");
						String nameUpdate = scan.nextLine();
						nameUpdate = nameUpdate.toUpperCase();
						if (nameUpdate.matches("[^0-9]+")){
							Database.guestHM.remove(g.getName());
							g.setName(nameUpdate);
							System.out.println("Name changed successfully");
							break;
						}
					}while (true);
					break;
				case 2:
					System.out.println("Current Address: " + g.getAddress());
					System.out.println("Enter your new address:");
					g.setAddress(scan.nextLine());
					System.out.println("Adress changed successfully");
					break;
				case 3:
					do{
						System.out.println("Current Country: " + g.getCountry());
						System.out.println("Enter your new country:");
						country = scan.nextLine();
						if (country.matches("[^0-9]+")){
							g.setCountry(country);
							break;
						}
					}while(true);
					System.out.println("Country updated successfully");
					break;
				case 4:
					System.out.println("Current Gender: " + g.getGender());
					do{
						System.out.println("Choose guest gender you want to update: \n"
							 + "1. Male \n"
							 + "2. Female \n"
							 + "3. Others \n");
						gender = scan.nextInt();
					}while(gender<1 || gender>3);
		
					switch(gender) {
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
					System.out.println("Gender updated");
					break;
				case 5:
					System.out.println("Current identity type: " + g.getIdentity());
					do {
						System.out.println("Enter number of selection for guest identity to update: \n"
								+ "1. NRIC\n"
								+ "2. Driving License\n"
								+ "3. Passport");
						identity = scan.nextInt();
					}while(identity<1 || identity>3);
					switch(identity) {
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
					System.out.println("Identity type updated");
					break;
				case 6:
					System.out.println("Current identity number: " + g.getIdentityNo());
					System.out.println("Enter your new identity number: ");
					g.setIdentityNo(scan.nextLine());
					System.out.println("Identity number updated");
					break;
				case 7:
					System.out.println("Current nationality: " + g.getNationality());
					do{
						System.out.println("Enter your new nationality:");
						nationality = scan.nextLine();
						if (nationality.matches("[^0-9]+")){
							g.setNationality(nationality);
							break;
						}
					}while (true);
					System.out.println("Nationality updated");
					break;
				case 8:
					System.out.println("Current contact: " + g.getContact());
					do{
						System.out.println("Enter your update contact: ");
						contact = scan.nextLine();
						if(contact.matches("[^a-zA-Z ]+")){
							g.setContact(contact);
							break;
						}
					} while(true);
					System.out.println("Contact updated");
					break;
				case 9:
					GuestCreditCard creditCard = g.getCreditCard();
					System.out.println("Current Credit Card: " );
					GuestCreditCardView.printGuestCreditCard(creditCard);
					System.out.println();
					int option;
					do {
						System.out.println("Select details to be changed: \n"
										 + "1. Name\n"
										 + "2. Billing Address\n"
										 + "3. Card Number\n"
										 + "4. Expiry Date\n"
										 + "5. Bank Name\n"
										 + "6. Back\n");
						option = scan.nextInt();
						scan.nextLine();
						switch(option){
							case 1:
								do{
									System.out.println("Enter New Name: ");
									ccName = scan.nextLine();
									ccName = ccName.toUpperCase();
									if (ccName.matches("[^0-9]+")){
										creditCard.setName(ccName);
										break;
									}
								}while (true);
								System.out.println("Name on Credit Card updated");
								break;
							case 2:
								System.out.println("Enter New Billing Address: ");
								creditCard.setBillingAdd(scan.nextLine());
								System.out.println("Billing address updated");
								break;
							case 3:
								do{
									System.out.println("Enter new 16-digits credit card number (without - or spaces): ");
									String number = scan.nextLine(); // 16 digits of credit card number
									if (number.length() == 16){
										number = number.substring(12,16); // extract only last four digits
										creditCard.setLast4Digits(number);
										break;
									}
								}while(true);
								System.out.println("Credit Card Number updated");
								break;
							case 4:
								System.out.println("Enter new expiry date (MM/YY): ");
								creditCard.setExpiryDate(scan.nextLine());
								System.out.println("Credit Card Expiry Date updated");
								break;
							case 5:
								do{
									System.out.println("Enter New Bank Name: ");
									bankName = scan.nextLine();
									if (bankName.matches("[^0-9]+")){				
										creditCard.setBankName(bankName);
										break;
									}
								} while (true);
								System.out.println("Bank Name updated");
								break;
							case 6:
								System.out.println("Exiting...");
								break;
							default:
								System.out.println("Please enter 1,2,3,4,5 or 6");
								break;
						}
					}while(option != 6);
					g.setCreditCard(creditCard);
					break;
				case 10:
					System.out.println("Exiting...");
					break;
				default:
					System.out.println("Please enter number 1 to 10");
					break;
			}
		}while (update!=10);
		Database.guestHM.put(g.getName(), g); 		//store the updated guest into database
		return g.getName();
	}
}
