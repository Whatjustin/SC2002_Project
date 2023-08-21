import java.util.Scanner;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;
/**
 * To calculate price based on room, holiday and weekend rates
 * @author Anson
 *
 */
public class PriceChange{
	/**
	 * Value of base room rate.
	 */
	private double roomRate;
	/**
	 * Value of weekend rate.It is initialised to 0.1 in file.
	 */
	private static double weekendRate;
	//total roomRatePrice;
	/**
	 * Value of the final cost of the room, including holiday promotion deductions
	 */
	private double roomRatePrice;
	/**
	 * Value of the cost of the room calculated only from the base room rate.
	 */
	private double baseRoomPrice;
	/**
	 * Value of base holiday rate. Set at 0 at first.
	 */
	private static double holidayRate = 0;
	/**
	 * Holiday period year; e.g., 2002
	 */
	private static int holidayYear;
	/**
	 * Holiday period month; e.g., 12 for december.
	 */
	private static int holidayMonth;
	/**
	 * Holiday period start date; e.g., 1 for 1st of the month.
	 */
	private static int holidayStartDate;
	/**
	 * Holiday period end date; e.g., 30 for 30th of the month.
	 */
	public static int holidayEndDate;
	/**
	 * True means check in date is in holiday period
	 */
	boolean holiday;
	/**
	 * Constructs a PriceChange object with base room rate, holiday rate, weekend rate, and specified holiday duration.
	 * @param guestName Name of guest
	 */
	public PriceChange(String guestName) {
		guestName = guestName.toUpperCase();
		Room ro = Database.roomHM.get(guestName);
		roomRate = ro.getRoomRate();
		roomRatePrice = 0;
		this.holiday = false;
	}


	/**
	 * Calculates the total room costs in a given time frame from user inputs (includes checking of weekends.
	 * @param calIn	check in date
	 * @param calOut check out date
	 * @return double price based on weekend, room and holiday rate
	 */
	public double calculatePriceChange(Calendar calIn, Calendar calOut) {
		//check if check out and check in same month
		Calendar cal = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal.set(calIn.get(Calendar.YEAR), calIn.get(Calendar.MONTH), calIn.get(Calendar.DATE), calIn.get(Calendar.HOUR_OF_DAY), 0);
		cal2.set(calOut.get(Calendar.YEAR), calOut.get(Calendar.MONTH), calOut.get(Calendar.DATE), calOut.get(Calendar.HOUR_OF_DAY), 0);
		do {
			int day = cal.get(Calendar.DAY_OF_WEEK);
			//check if Saturday or Sunday
			if(day == Calendar.SATURDAY || day == Calendar.SUNDAY) {
				roomRatePrice = roomRatePrice + roomRate * (1+weekendRate);
				baseRoomPrice = baseRoomPrice + roomRate * (1+weekendRate);
			}
			else {
				roomRatePrice = roomRatePrice + roomRate;
				baseRoomPrice = baseRoomPrice + roomRate;
			}
			cal.add(Calendar.DAY_OF_YEAR, 1);
		} while(cal.before(cal2));
		//check if holiday
		if (cal.get(Calendar.YEAR) == holidayYear && cal.get(Calendar.MONTH) == holidayMonth && 
				cal.get(Calendar.DATE) >= holidayStartDate && cal.get(Calendar.DATE) <= holidayEndDate) {
			holiday = true;
			roomRatePrice = roomRatePrice * (1-holidayRate);
		}
		return roomRatePrice;
	}
	
	/**
	 * Sets holiday period
	 * @param holidayYear	Holiday period year
	 * @param holidayMonth	Holiday period month
	 * @param holidayStartDate	Holiday start day
	 * @param holidayEndDate	Holiday end day
	 */
	public static void setHolidayPeriod(int holidayYear, int holidayMonth, int holidayStartDate, int holidayEndDate) {
		PriceChange.holidayYear = holidayYear;
		PriceChange.holidayMonth = holidayMonth;
		PriceChange.holidayStartDate = holidayStartDate;
		PriceChange.holidayEndDate = holidayEndDate;
	}
	/**
	 * Gets Holiday Period Year
	 * @return int
	 */
	public static int getHolidayYear() {
		return holidayYear;
	}
	/**
	 * Gets Holiday Period Month
	 * @return int
	 */
	public static int getHolidayMonth() {
		return holidayMonth;
	}
	/**
	 * Gets Holiday Period Start Date
	 * @return int
	 */
	public static int getHolidayStartDate() {
		return holidayStartDate;
	}
	/**
	 * Gets Holiday Period End Date
	 * @return int
	 */
	public static int getHolidayEndDate() {
		return holidayEndDate;
	}
	
	/**
	 * Sets this holiday rate with the given holiday rate.
	 * @param holidayRate (double)
	 */
	public static void setHolidayRate(Double holidayRate) {
		PriceChange.holidayRate = holidayRate;
	}
	/**
	 * Gets holiday period
	 * @return double
	 */
	public static double getHolidayRate() {
		return holidayRate;
	}
	
	/**
	 * Returns the base room price, without the holiday promotions deducted.
	 * @return double
	 */
	public double getRoomPrice(){
		return baseRoomPrice;
	}

	/**
	 * Returns the exact cost of the holiday discount; e.g., $500
	 * @return double
	 */
	public double getHolidayDiscount(){
		if(holiday)
			return baseRoomPrice *holidayRate;
		else
			return 0;
	}

	/**
	 * Returns the base weekend rate.
	 * @return double
	 */
	public static double getWeekendRate() {
		return weekendRate;
	}

	/**
	 * Sets this weekend rate with the given weekend rate.
	 * @param weekendRate (double)
	 */
	public static void setWeekendRate(double weekendRate) {
		PriceChange.weekendRate = weekendRate;
	}
	/**
	 * static method readPrice to read the data from the file 
	 */
	public static void readPrice(){
	    String fileName = "RateHoliday.dat" ;
	      try {
	    	  FileInputStream     fiStream
	            = new FileInputStream(     fileName );
	    	  BufferedInputStream biStream
	            = new BufferedInputStream( fiStream );
	    	  ObjectInputStream   diStream
	            = new ObjectInputStream(   biStream );

	    	  PriceChange.weekendRate = diStream.readDouble();
	    	  PriceChange.holidayRate = diStream.readDouble();
	    	  PriceChange.holidayYear = diStream.readInt();
	    	  PriceChange.holidayMonth = diStream.readInt();
	    	  PriceChange.holidayStartDate = diStream.readInt();
	    	  PriceChange.holidayEndDate = diStream.readInt();
	      
	      diStream.close();
	    }
	    catch ( FileNotFoundException e ) {
	      System.out.println( "IOError: File not found!" + fileName );
	      System.exit( 0 );
	    }
	    catch ( IOException e ) {
	      System.out.println( "File IO Error!" + e.getMessage() );
	      System.exit( 0 );
	    }
	  }
	/**
	 * static method writePrice to save the details into a file
	 */
	public static void writePrice(){
	    String fileName = "RateHoliday.dat" ;
	    try {
	      FileOutputStream     foStream
	            = new FileOutputStream(     fileName );
	      BufferedOutputStream boStream
	            = new BufferedOutputStream( foStream );
	      ObjectOutputStream   doStream
	            = new ObjectOutputStream(   boStream );

	      doStream.writeDouble(PriceChange.weekendRate);  
	      doStream.writeDouble(PriceChange.holidayRate); 
	      doStream.writeInt(PriceChange.holidayYear);
	      doStream.writeInt(PriceChange.holidayMonth);
	      doStream.writeInt(PriceChange.holidayStartDate);
	      doStream.writeInt(PriceChange.holidayEndDate);
	      
	      doStream.close();
	      }
	      catch ( FileNotFoundException e ) {
	        System.out.println( "IOError: File not found!" + fileName );
	        System.exit( 0 );
	      }
	      catch ( IOException e ) {
	        System.out.println( "File IO Error!" + e.getMessage() );
	        System.exit( 0 );
	      }
	    }
}
