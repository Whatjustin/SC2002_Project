import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Calendar;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
/**
 * Class of Database that stores data related to Guest, Reservation, Room and RoomService
 * @author User
 *
 */
public class Database {
	/**
	 * static HashMap reservationHM, key is name of Guest, value is Reservation Object
	 */
	public static HashMap <String, Reservation> reservationHM = new HashMap<String, Reservation>();
	/**
	 * static HashMap reservationCodeHM, key is reservationCode, value is Reservation Object
	 */
	public static HashMap <Integer, Reservation> reservationCodeHM = new HashMap<Integer, Reservation>();
	/**
	 * static HashMap guestHM, key is name of Guest, value is Guest Object
	 */
	public static HashMap <String, Guest> guestHM = new HashMap<String, Guest>();
	/**
	 * static HashMap roomServiceHM, key is name of main guest (guest displayed in invoice), value is ArrayList of FoodItem
	 */
	public static HashMap <String, ArrayList<FoodItem>> roomServiceHM = new HashMap <String, ArrayList<FoodItem>>();
	/**
	 * static HashMap dateRoomHM, key is roomNumber, value is a Calendar array with check-in and check-out date
	 */
	public static HashMap <String, ArrayList<Calendar[]>> dateRoomHM = new HashMap <String, ArrayList<Calendar[]>>();
	/**
	 * static HashMap roomHM, key is name of Guest, value is Room Object
	 */
	public static HashMap <String, Room> roomHM = new HashMap <String, Room>();
	/**
	 * static HashMap roomMaintenanceHM, key is room number, value is period of room maintenance
	 */
	public static HashMap <String, Calendar[]> roomMaintenanceHM = new HashMap <String, Calendar[]>();
	/**
	 * static method readFile to read all the data is stored dat files
	 */
	public static void readFile() {
		try {
	        File toRead=new File("reservation.dat");
	        FileInputStream fis=new FileInputStream(toRead);
	        ObjectInputStream ois=new ObjectInputStream(fis);
	        Database.reservationHM = (HashMap<String, Reservation>)ois.readObject();
	        ois.close();
	        fis.close();
	    } catch(Exception e) {}
		
		try {
	        File toRead=new File("reservationCode.dat");
	        FileInputStream fis=new FileInputStream(toRead);
	        ObjectInputStream ois=new ObjectInputStream(fis);
	        Database.reservationCodeHM = (HashMap<Integer, Reservation>)ois.readObject();
	        ois.close();
	        fis.close();
	    } catch(Exception e) {}
		
		try {
	        File toRead=new File("guest.dat");
	        FileInputStream fis=new FileInputStream(toRead);
	        ObjectInputStream ois=new ObjectInputStream(fis);
	        Database.guestHM = (HashMap<String, Guest>)ois.readObject();
	        ois.close();
	        fis.close();
	    } catch(Exception e) {}
		
		try {
	        File toRead=new File("roomService.dat");
	        FileInputStream fis=new FileInputStream(toRead);
	        ObjectInputStream ois=new ObjectInputStream(fis);
	        Database.roomServiceHM = (HashMap<String, ArrayList<FoodItem>>)ois.readObject();
	        ois.close();
	        fis.close();
	    } catch(Exception e) {}
		
		try {
	        File toRead=new File("dateRoom.dat");
	        FileInputStream fis=new FileInputStream(toRead);
	        ObjectInputStream ois=new ObjectInputStream(fis);
	        Database.dateRoomHM = (HashMap<String, ArrayList<Calendar[]>>)ois.readObject();
	        ois.close();
	        fis.close();
	    } catch(Exception e) {}
		
		try {
	        File toRead=new File("room.dat");
	        FileInputStream fis=new FileInputStream(toRead);
	        ObjectInputStream ois=new ObjectInputStream(fis);
	        Database.roomHM = (HashMap<String, Room>)ois.readObject();
	        ois.close();
	        fis.close();
	    } catch(Exception e) {}
		
		try {
	        File toRead=new File("roomMaintenance.dat");
	        FileInputStream fis=new FileInputStream(toRead);
	        ObjectInputStream ois=new ObjectInputStream(fis);
	        Database.roomMaintenanceHM = (HashMap<String, Calendar[]>)ois.readObject();
	        ois.close();
	        fis.close();
	    } catch(Exception e) {}
	}
	/**
	 * static method writeFile to save all the data into dat files
	 */
	public static void writeFile() {
		try {
	        File fileOne=new File("reservation.dat");
	        FileOutputStream fos=new FileOutputStream(fileOne);
	        ObjectOutputStream oos=new ObjectOutputStream(fos);

	        oos.writeObject(Database.reservationHM);
	        oos.flush();
	        oos.close();
	        fos.close();
	    } catch(Exception e) {}
		
		try {
	        File fileOne=new File("reservationCode.dat");
	        FileOutputStream fos=new FileOutputStream(fileOne);
	        ObjectOutputStream oos=new ObjectOutputStream(fos);

	        oos.writeObject(Database.reservationCodeHM);
	        oos.flush();
	        oos.close();
	        fos.close();
	    } catch(Exception e) {}
		
		try {
	        File fileOne=new File("guest.dat");
	        FileOutputStream fos=new FileOutputStream(fileOne);
	        ObjectOutputStream oos=new ObjectOutputStream(fos);

	        oos.writeObject(Database.guestHM);
	        oos.flush();
	        oos.close();
	        fos.close();
	    } catch(Exception e) {}
		
		try {
	        File fileOne=new File("roomService.dat");
	        FileOutputStream fos=new FileOutputStream(fileOne);
	        ObjectOutputStream oos=new ObjectOutputStream(fos);

	        oos.writeObject(Database.roomServiceHM);
	        oos.flush();
	        oos.close();
	        fos.close();
	    } catch(Exception e) {}
		
		try {
	        File fileOne=new File("dateRoom.dat");
	        FileOutputStream fos=new FileOutputStream(fileOne);
	        ObjectOutputStream oos=new ObjectOutputStream(fos);

	        oos.writeObject(Database.dateRoomHM);
	        oos.flush();
	        oos.close();
	        fos.close();
	    } catch(Exception e) {}
		
		try {
	        File fileOne=new File("room.dat");
	        FileOutputStream fos=new FileOutputStream(fileOne);
	        ObjectOutputStream oos=new ObjectOutputStream(fos);

	        oos.writeObject(Database.roomHM);
	        oos.flush();
	        oos.close();
	        fos.close();
	    } catch(Exception e) {}
		Calendar cal = Calendar.getInstance();
		Database.roomMaintenanceHM.forEach((k, v)-> {
			Calendar calNow = Calendar.getInstance();
			if (cal.after(calNow))
				Database.roomMaintenanceHM.remove(k);			//remove room maintenance schedule that are in the past
		});
          
		try {
	        File fileOne=new File("roomMaintenance.dat");
	        FileOutputStream fos=new FileOutputStream(fileOne);
	        ObjectOutputStream oos=new ObjectOutputStream(fos);

	        oos.writeObject(Database.roomMaintenanceHM);
	        oos.flush();
	        oos.close();
	        fos.close();
	    } catch(Exception e) {}
	}
}
