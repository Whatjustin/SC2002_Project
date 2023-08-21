import java.util.ArrayList;
import java.util.Calendar;
/**
 * Display Reservation Details
 * @author Oo Yifei
 *
 */
public class ReservationView {
	public void printReservationDetails(ArrayList <String> guestName, String roomNumber, int reservationCode, Calendar reservationDate, 
			String billingInfo, Calendar checkInDate, Calendar checkOutDate, int numOfAdults, int numOfChildren, 
			Reservation.ReservationStatus reservationStatus) {
		int checkInYear = checkInDate.get(Calendar.YEAR);
		int checkInMonth = checkInDate.get(Calendar.MONTH);
		int checkInDay = checkInDate.get(Calendar.DATE);
		int checkOutYear = checkOutDate.get(Calendar.YEAR);
		int checkOutMonth = checkOutDate.get(Calendar.MONTH);
		int checkOutDay = checkOutDate.get(Calendar.DATE);
		int reservationYear = reservationDate.get(Calendar.YEAR);
		int reservationMonth = reservationDate.get(Calendar.MONTH);
		int reservationDay = reservationDate.get(Calendar.DATE);
		System.out.println("===============================================");
		System.out.println("              Reservation Details");
		System.out.println("===============================================");
		System.out.println("Reservation Code\t\t| "+reservationCode);
		System.out.println("Date of reservation made\t| "+reservationYear+"-"+(reservationMonth+1)+"-"+reservationDay);
		System.out.println("Name of guest(s)\t\t| "+guestName.get(0));
		if(guestName.size()>0) {
		for(int i=1; i<guestName.size(); i++) {
		System.out.println("                   		| "+guestName.get(i));
		}
		}
		System.out.println("Number of Adults   		| "+numOfAdults);
		System.out.println("Number of Children 		| "+numOfChildren);
		System.out.println("Room Number        		| "+roomNumber);
		System.out.println("Billing Info      		| "+billingInfo);
		System.out.println("Check-in Date      		| "+checkInDay+"-"+(checkInMonth+1)+"-"+checkInYear);
		System.out.println("Check-out Date     		| "+checkOutDay+"-"+(checkOutMonth+1)+"-"+checkOutYear);
		System.out.println("Reservation Status 		| "+reservationStatus);
		System.out.println();
	}
}
