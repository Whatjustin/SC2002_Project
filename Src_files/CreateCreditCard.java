import java.util.Scanner;
/**
 * This CreateCreditCard class is to create a new credit card details of a guest and store into 
 * the database
 */
public class CreateCreditCard {
	/**
	 * Store the 16-digits credit card number
	 */
	private String number; //allow the guest enter 16-digits credit card number
	Scanner scan = new Scanner(System.in);
	/**
	 * This is the method to fill in all the details of the credit card
	 * and store it into the database
	 * If yesNo = Yes then the guest is paying with credit card
	 * If yesNo = No then the guest is paying cash
	 * @param creditCard the credit card blank details
	 * @param yesNo check whether the guest is paying with credit card (if no then he is paying by cash)
	 */
	public CreateCreditCard(GuestCreditCard creditCard, String yesNo) {
		
		// the guest is paying by cash
		String name;
		if (yesNo.equals("N")) { 
			creditCard.setName("-");
			creditCard.setBillingAdd("-");
			creditCard.setLast4Digits("-");
			creditCard.setExpiryDate("-");
			creditCard.setBankName("-");
		}
		else { // the guest is paying by credit card
			do{
				System.out.println("Enter the name on the credit card: ");
				name = scan.nextLine();
				if(name.matches("[^0-9]+")) {
					creditCard.setName(name);
					break;
				}
			} while(true);
			System.out.println("Enter your billing address: ");
			creditCard.setBillingAdd(scan.nextLine());
			do{
				System.out.println("Enter your 16-digits credit card number (without - or spaces): ");
				number = scan.nextLine(); // 16 digits of credit card number
				if(number.length() == 16){
					number = number.substring(12,16); // extract until last four digits left
					break;
				}
			} while(true);
			creditCard.setLast4Digits(number);
			System.out.println("Enter the expiry date of the card (MM/YY): ");
			creditCard.setExpiryDate(scan.nextLine());
			do{
				System.out.println("Enter the issuing bank of the card: ");
				String bankName = scan.nextLine();
				if (bankName.matches("[^0-9]+")){				
					creditCard.setBankName(bankName);
					break;
				}
			} while (true);
		}
	}
}
