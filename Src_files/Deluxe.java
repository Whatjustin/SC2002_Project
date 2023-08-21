public class Deluxe extends Room{
    /**
     * Deluxe room object constructor
     * @param roomnumber String
     * @param wifienabled int
     * @param bedType bedTypes
     * @param view int
     * @param canSmoke int
     * @param status Room.status
     */
    public Deluxe(String roomnumber,int wifienabled, bedtypes bedType, int view, int canSmoke, Room.statuses status) {
        this.setRoomNumber(roomnumber);
        this.setWifienabled(wifienabled);
        this.setBedType(bedType);
        this.setView(view);
        this.setFlatRate(700);
        this.setRoomRate();
        this.setSmokingAllowed(canSmoke);
        this.setStatus(status);
    }

}
