import java.io.Serializable;

/**
 * Abstract class Room, to be extended by Single, DoubleR, VIP, Deluxe
 */
abstract class Room implements Serializable {
    /**
     * flat rate of a room, to be set later (Single, Double, Deluxe, VIP has different rates).
     * Does not include additional costs such as inclusion of wifi, bedtype etc.
     */
    private double flatRate;

    /**
     * Room number
     */
    private String roomNumber;

    /**
     * if wifi is enabled denoted with true or false
     */
    private int wifienabled;

    /**
     * enumeration of the 3 bedtypes: Standard, Queen or King
     */
    static enum bedtypes{
        STANDARD(0),
        QUEEN(1),
        KING(2);

        private final int value;
        private bedtypes(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * bedtype attribute, assigned to enumeration of bedtypes e.g. bedType = bedtypes.STANDARD
     */
    private bedtypes bedType;

    /**
     * enumeration of 4 statuses: Vacant, Occupied, Reserved or Maintenance (Checking is only for internal use, it will never be displayed)
     */
    static enum statuses{
        VACANT(0),
        OCCUPIED(1),
        CHECKING(2),
        RESERVED(3),
        MAINTENANCE(4);

        private final int value;
        private statuses(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
    
    /**
     * if smoking allowed enabled with true or false
     */
    private int smokingAllowed;

    /**
     * statuses attribute, default value is vacant. Can be assigned to the enumeration of statuses
     */
    private statuses status = statuses.VACANT;

    /**
     * if there is a view 1 for true and 0 for false
     */
    private int view;

    /**
     * Room Rate value
     */
    private double roomRate;

    /**
     * gets the flat rate of the room
     * @return double
     */
    public double getRoomRate() {
        return roomRate;
    }

    /**
     * Sets overall room rate. Base rate + wifi + bedtype.
     * Additional $20 for wifi, additional $30 for Queen bed, additional $40 for King bed.
     */
    public void setRoomRate(){
        this.roomRate = getFlatRate();
        if (wifienabled==1) this.roomRate += 20;
        if (view==1) this.roomRate += 50;
        if (bedType==bedtypes.QUEEN) this.roomRate+=30;
        if (bedType==bedtypes.KING) this.roomRate+=40;
        this.roomRate = roomRate;

    }

    /**
     * Returns room number for current room object
     * @return String
     */
    public String getRoomNumber() {
        return roomNumber;
    }

    /**
     * Sets room number for room object. Accepts String
     * @param num String
     */
    public void setRoomNumber(String num) {
        this.roomNumber = num;
    }

    /**
     * returns if wifi is enabled: true or false
     * @return boolean
     */
    public int isWifienabled() {
        return wifienabled;
    }

    /**
     * Sets if wifi is enabled: 1 or 0
     * @param wifienabled int
     */
    public void setWifienabled(int wifienabled) {
        this.wifienabled = wifienabled;
    }

    /**
     * gets the bedtype. Returns enum bedTypes
     * @return bedtypes
     */
    public bedtypes getBedType() {
        return bedType;
    }

    /**
     * Returns integer representation of bedtype
     * @return
     */
    public int getBedTypeInt(){return bedType.value;}
    /**
     * Sets the bedType
     * @param bedType bedTypes
     */
    public void setBedType(bedtypes bedType) {
        this.bedType = bedType;
    }

    /**
     * Gets the status of the room. Returns enum of statuses
     * @return statuses
     */
    public statuses getStatus() {
        return status;
    }

    /**
     * get integer representation of enum status
     * @return int
     */
    public int getStatusInt(){return status.getValue();}

    /**
     * Sets status of room, accepts enum of statuses
     * @param status statuses
     */
    public void setStatus(statuses status) {
        this.status = status;
    }

    /**
     * if there is a view 1 or 0
     * @return int
     */
    public int isView() {
        return view;
    }



    /**
     * Sets the view 1 or 0
     * @param view int
     */
    public void setView(int view) {
        this.view = view;
    }

    /**
     * gets the flat rate
     * @return double
     */
    public double getFlatRate() {
        return flatRate;
    }

    /**
     * sets the flat rate
     * @param flatRate double
     */
    public void setFlatRate(double flatRate) {
        this.flatRate = flatRate;
    }
    
    /**
     * if smoking is allowed
     * @return int
     */
    public int isSmokingAllowed() {
		return smokingAllowed;
	}
    
    /**
     * set smoking allowed 1 or 0
     * @param smokingAllowed int
     */
    public void setSmokingAllowed(int smokingAllowed) {
		this.smokingAllowed = smokingAllowed;
	}
}
