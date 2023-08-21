/**
 * The GuestView simply prints the all the details
 * of the guest
 */
public class GuestView{
	/**
	 * This method prints all the guest details in an orderly manner
	 * @param name guest name
	 * @param address guest address
	 * @param creditCard guest credit card details
	 * @param country guest country
	 * @param gender guest gender
	 * @param identity guest identity type
	 * @param identityNo guest identity number
	 * @param nationality guest nationality
	 * @param contact guest contact number
	 * @param adult determines whether the guest is an adult or a children
	 */
	public void printGuestDetails(String name, String address, GuestCreditCard creditCard, String country, 
			String gender, Guest.GuestIdentity identity, String identityNo, String nationality, String contact, boolean adult){
		System.out.println("--------------------------------------------");
		System.out.println("Details of "+name);
		System.out.println("--------------------------------------------");
		System.out.println("Gender            ||"+gender);
		System.out.println("Contact No.       ||"+contact);
		System.out.println("Adress            ||"+address);
		System.out.println("Country           ||"+country);
		System.out.println("Identity Type     ||"+identity);
		System.out.println("Identity No.      ||"+identityNo);
		System.out.println("Nationality       ||"+nationality);
		if(adult == true)
		System.out.println("Adult or Child	  ||"+"Adult");
		else
		System.out.println("Adult or Child    ||"+"Child");
		if(creditCard.getName().equals("-")){
			System.out.println("Credit Card       ||"+ "-");
			}
			else {
			GuestCreditCardView.printGuestCreditCard(creditCard);
			}
		System.out.println("--------------------------------------------");
	}
}
