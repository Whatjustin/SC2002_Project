/**
 * The GuestCreditCardView simply prints the all the details
 * of the credit card provided by the guest
 * If the guest pay by cash then the details will be "-"
 */
public class GuestCreditCardView {
	/**
	 * This method prints all the credit card details in an orderly manner
	 * @param creditCard get all the credit details from GuestCreditCard class
	 */
	public static void printGuestCreditCard(GuestCreditCard creditCard){
		System.out.println("--------------------------------------------");
		System.out.println("Credit Card Details:             ");
		System.out.println();
		System.out.println("Name              || " + creditCard.getName());
		System.out.println("Billing Address   || " + creditCard.getBillingAdd());
		System.out.println("Credit Card No.	  || xxxx-xxxx-xxxx-" + creditCard.getLast4Digits());
		System.out.println("Expiry Date       || " + creditCard.getExpiryDate());
		System.out.println("Issuing Bank	  || " + creditCard.getBankName());
	}
}
