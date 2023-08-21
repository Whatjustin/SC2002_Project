public class Single extends Room{
    /**
     * Single room object constructor
     * @param roomnumber String
     * @param wifienabled int
     * @param bedType bedtypes
     * @param view int
     * @param smokingAllowed int
     * @param status Room.statuses
     */
    public Single(String roomnumber, int wifienabled, bedtypes bedType, int view, int smokingAllowed, Room.statuses status) {
        this.setRoomNumber(roomnumber);
        this.setWifienabled(wifienabled);
        this.setBedType(bedType);
        this.setView(view);
        this.setFlatRate(400);
        this.setRoomRate();
        this.setSmokingAllowed(smokingAllowed);
        this.setStatus(status);
    }

}
