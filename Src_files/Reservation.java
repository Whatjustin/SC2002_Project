import java.util.Calendar; 
import java.util.ArrayList;
import java.io.Serializable;

/**
 * Reservation object
 * @author Oo Yifei
 *
 */
public class Reservation implements Serializable {
	/**
	 * ArrayList of guest
	 */
	private ArrayList <Guest> guest = new ArrayList<Guest>();
	/**
	 * Name of guest who is paying
	 */
	private String mainGuestName;
	/**
	 * Room object
	 */
	private Room room;
	/**
	 * Reservation Code
	 */
	private int reservationCode;
	/**
	 * Date Reservation was made
	 */
	private Calendar reservationDate;
	/**
	 * Billing Info (Credit Card or Cash)
	 */
	private String billingInfo;
	/**
	 * Check-in date
	 */
	private Calendar checkInDate;
	/**
	 * Check-out Date
	 */
	private Calendar checkOutDate;
	/**
	 * Number of Adults
	 */
	private int numOfAdults;
	/**
	 * Number of Children
	 */
	private int numOfChildren;
	/**
	 * Reservation Status
	 */
	private ReservationStatus reservationStatus;
	/**
	 * Enumeration of Reservation Status
	 * @author Oo Yifei
	 *
	 */
	static enum ReservationStatus {
		Confirmed,
		In_Waitlist,
		Checked_in,
		Expired;
	}
	/**
	 * Get ArrayList of Guest object
	 * @return Guest object
	 */
	public ArrayList<Guest> getGuest() {
		return guest;
	}
	/**
	 * Set ArrayList of Guest object
	 * @param guest
	 */
	public void setGuest(ArrayList<Guest> guest) {
		this.guest = guest;
	}
	/**
	 * Set Main Guest Name (guest name to be displayed on invoice
	 * @param mainGuestName
	 */
	public void setMainGuestName(String mainGuestName) {
		this.mainGuestName = mainGuestName;
	}
	/**
	 * Get Main Guest Name (guest name to be displayed on invoice)
	 * @return
	 */
	public String getMainGuestName() {
		return mainGuestName;
	}
	/**
	 * Get Room object
	 * @return Room object
	 */
	public Room getRoom() {
		return room;
	}
	
	/**
	 * Set Room object
	 * @param room
	 */
	public void setRoom(Room room) {
		this.room = room;
	}
	
	/**
	 * Get Reservation Code
	 * @return int reservation code
	 */
	public int getReservationCode() {
		return reservationCode;
	}
	
	/**
	 * Set Reservation Code
	 * @param reservationCode
	 */
	public void setReservationCode(int reservationCode) {
		this.reservationCode = reservationCode;
	}
	/**
	 * Set Date of Reservation Date
	 * @param reservationDate
	 */
	public void setReservationDate(Calendar reservationDate) {
		this.reservationDate = reservationDate;
	}
	/**
	 * Get Date of Reservation Made
	 * @return
	 */
	public Calendar getReservationDate() {
		return reservationDate;
	}
	/**
	 * Get Billing Info (Credit Card or Cash)
	 * @return String Billing Info
	 */
	public String getBillingInfo() {
		return billingInfo;
	}
	
	/**
	 * Set Billing Info (Credit Card or Cash)
	 * @param billingInfo
	 */
	public void setBillingInfo(String billingInfo) {
		this.billingInfo = billingInfo;
	}
	
	/**
	 * Get Check-in Date
	 * @return Calendar Check-in Date
	 */
	public Calendar getCheckInDate() {
		return checkInDate;
	}
	
	/**
	 * Set Check-in Date
	 * @param checkInDate
	 */
	public void setCheckInDate(Calendar checkInDate) {
		this.checkInDate = checkInDate;
	}
	
	/**
	 * Get Check-out Date
	 * @return Calendar Check-out Date
	 */
	public Calendar getCheckOutDate() {
		return checkOutDate;
	}
	
	/**
	 * Set Check-out Date
	 * @param checkOutDate
	 */
	public void setCheckOutDate(Calendar checkOutDate) {
		this.checkOutDate = checkOutDate;
	}
	
	/**
	 * Get Number of Adults
	 * @return int Number of Adults
	 */
	public int getNumOfAdults() {
		return numOfAdults;
	}
	
	/**
	 * Set Number of Adults
	 * @param numOfAdults
	 */
	public void setNumOfAdults(int numOfAdults) {
		this.numOfAdults = numOfAdults;
	}
	
	/**
	 * Get Number of Children
	 * @return int Number of Children
	 */
	public int getNumOfChildren() {
		return numOfChildren;
	}
	
	/**
	 * Set Number of Children
	 * @param numOfChildren
	 */
	public void setNumOfChildren(int numOfChildren) {
		this.numOfChildren = numOfChildren;
	}
	
	/**
	 * Get Reservation Status
	 * @return enum ReservationStatus
	 */
	public ReservationStatus getReservationStatus() {
		return reservationStatus;
	}
	
	/**
	 * Set Reservation Status
	 * @param reservationStatus
	 */
	public void setReservationStatus(ReservationStatus reservationStatus) {
		this.reservationStatus = reservationStatus;
	}
}
