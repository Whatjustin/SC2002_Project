public class VIP extends Room{

    /**
     * VIP room object constructor
     * @param roomnumber String
     * @param wifienabled int
     * @param bedType bedtypes
     * @param view int
     * @param canSmoke int
     * @param status Room.statuses
     */

    public VIP(String roomnumber, int wifienabled, bedtypes bedType, int view, int canSmoke, Room.statuses status) {
        this.setRoomNumber(roomnumber);
        this.setWifienabled(wifienabled);
        this.setBedType(bedType);
        this.setView(view);
        this.setFlatRate(600);
        this.setRoomRate();
        this.setSmokingAllowed(canSmoke);
        this.setStatus(status);
    }

}
