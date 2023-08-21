import java.util.ArrayList;

/**
 * Static method for walk-in guests
 * @author Oo Yifei
 *
 */
public class WalkIn {
	/**
	 * new ReservationView to display reservation details if guest choose to walk in
	 */
	static ReservationView reservationView = new ReservationView();
	/**
	 * static method for guest to walk in
	 */
	public static void walkIn(){
		System.out.println("Creating a new reservation:");
		Reservation reservation = new Reservation();	
		CreateReservation.createReservation(reservation); 						//calls static method createReservation 
		ArrayList <String> guestNameAL = new ArrayList<String>();
		int numOfGuest = reservation.getNumOfAdults() + reservation.getNumOfChildren();			//that returns a reservation
		Guest g = new Guest();
		for (int i = 0; i<numOfGuest; i++) {
			g = reservation.getGuest().get(i);
			guestNameAL.add(g.getName());
			Database.reservationHM.put(g.getName(), reservation);   	//storing the reservation into database
			Database.roomHM.put(g.getName(), reservation.getRoom());
		}
		Database.reservationCodeHM.put(reservation.getReservationCode(), reservation);
		Room room = reservation.getRoom();
		if(room != null)			//prints the reservation details
			reservationView.printReservationDetails(guestNameAL, room.getRoomNumber(), reservation.getReservationCode(), reservation.getReservationDate(),
				reservation.getBillingInfo(), reservation.getCheckInDate(), reservation.getCheckOutDate(), reservation.getNumOfAdults(), reservation.getNumOfChildren(), 
				reservation.getReservationStatus());
		else
			reservationView.printReservationDetails(guestNameAL, "No Room Assigned", reservation.getReservationCode(), reservation.getReservationDate(),
					reservation.getBillingInfo(), reservation.getCheckInDate(), reservation.getCheckOutDate(), reservation.getNumOfAdults(), reservation.getNumOfChildren(), 
					reservation.getReservationStatus());
	}
}
