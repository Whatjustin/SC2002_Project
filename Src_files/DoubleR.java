public class DoubleR extends Room{

    /**
     * DoubleR constructor
     * @param roomnumber String
     * @param wifienabled int
     * @param bedType bedTypes
     * @param view int
     * @param smokingAllowed int
     * @param status Room.statuses
     */
    public DoubleR(String roomnumber, int wifienabled, bedtypes bedType, int view, int smokingAllowed, Room.statuses status) {
        this.setRoomNumber(roomnumber);
        this.setRoomNumber(roomnumber);
        this.setWifienabled(wifienabled);
        this.setBedType(bedType);
        this.setView(view);
        this.setFlatRate(450);
        this.setRoomRate();
        this.setSmokingAllowed(smokingAllowed);
        this.setStatus(status);
    }

}
