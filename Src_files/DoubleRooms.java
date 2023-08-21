import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * DoubleRooms class. Contains ArrayList of DoubleR objects.
 */
public class DoubleRooms implements Rooms{

    /**
     * Arraylist of DoubleR objects
     */

    protected static ArrayList<DoubleR> roomsD = new ArrayList<DoubleR>();

    /**
     * filename of txt file that stores information
     */
    static String filename = "DoubleR.txt";

    /**
     * Constructor for DoubleRooms object
     * @throws FileNotFoundException
     */
    public DoubleRooms() throws FileNotFoundException {

        Room.bedtypes bed = Room.bedtypes.STANDARD;
        Room.statuses status = Room.statuses.VACANT;

        File fileNames = new File(filename);
        if (fileNames.canRead()) {

            Scanner scFileNames = new Scanner(fileNames);
            while (scFileNames.hasNext()){
                String rmnum = scFileNames.next();
                String wifi = scFileNames.next();
                String bedsize = scFileNames.next();
                String view = scFileNames.next();
                String smokingAllowed = scFileNames.next();
                String ohstatus = scFileNames.next();

                switch(bedsize){
                    case "0" -> bed = Room.bedtypes.STANDARD;
                    case "1" -> bed = Room.bedtypes.KING;
                    case "2" -> bed = Room.bedtypes.QUEEN;
                }

                switch (ohstatus){
                    case "0" -> status = Room.statuses.VACANT;
                    case "1" -> status = Room.statuses.OCCUPIED;
                    case "2" -> status = Room.statuses.CHECKING;
                    case "3" -> status = Room.statuses.RESERVED;
                    case "4" -> status = Room.statuses.MAINTENANCE;
                }

                roomsD.add(new DoubleR(rmnum, Integer.valueOf(wifi), bed, Integer.valueOf(view), Integer.valueOf(smokingAllowed), status));
            }

        }
    }
    /**
     * Returns string of an empty Double room number
     * @return String
     */
    public static String giveEmptyRoom(){
        for (DoubleR i : roomsD){
            if (i.getStatus() == Room.statuses.VACANT){
                return i.getRoomNumber();
            }
        }
        return "No Vacant Rooms";
    }

    /**
     * Check and get room and return room number. Returns "All Checked" string if all checked.
     * @return String
     */
    public static String giveRoom(){
        for (DoubleR i : roomsD){
            if (i.getStatus() != Room.statuses.CHECKING){
                return i.getRoomNumber();
            }
        }
        return "All Checked";
    }

    /**
     * Gets a Double room object by accepting room number string as parameter
     * @param RoomNumber Room number of String type
     * @return DoubleR or null
     */
    public static DoubleR getRoomObject(String RoomNumber) {
        for (DoubleR i : roomsD) {
            if (i.getRoomNumber().equals(RoomNumber)) return i;
        }
        return null;
    }

    /**
     * Prints list of empty room numbers
     */

    public static void printempty(){
        System.out.println("Rooms: ");
        for (DoubleR i : roomsD){
            if (i.getStatus()== Room.statuses.VACANT){
                System.out.print(i.getRoomNumber()+", ");
            }
        }
    }

    /**
     *saves the room information to txt file so that we do not lose the information everytime we restart the app
     * @throws IOException
     */
    public static void saveroom() throws IOException {
        List alw = new ArrayList() ;// to store rooms data

        for (int i = 0 ; i < roomsD.size() ; i++) {
            DoubleR room = (DoubleR) roomsD.get(i);
            StringBuilder st =  new StringBuilder() ;
            st.append(room.getRoomNumber().trim());
            st.append(" ");
            st.append(room.isWifienabled());
            st.append(" ");
            st.append(room.getBedTypeInt());
            st.append(" ");
            st.append(room.isView());
            st.append(" ");
            st.append(room.isSmokingAllowed());
            st.append(" ");
            st.append(room.getStatusInt());
            alw.add(st.toString()) ;

        }
        write(DoubleRooms.filename,alw);
    }

    /**
     * writes to file the rooms information so that we do not lose it
     * @param fileName filename
     * @param data List of data to be written
     * @throws IOException
     */
    public static void write(String fileName, List data) throws IOException  {
        PrintWriter out = new PrintWriter(new FileWriter(fileName));

        try {
            for (int i =0; i < data.size() ; i++) {
                out.println((String)data.get(i));
            }
        }
        finally {
            out.close();
        }
    }

}
