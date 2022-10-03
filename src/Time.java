/**
 * This enum class defines the time of each fitness class.
 * @author Ramitha Ravishankar, Gloria Liu
 */
public enum Time {
    MORNING (9, 30),
    AFTERNOON (14, 0);
    private final int hour;
    private final int minute;

    /**
     * Parameterized constructor to define the time in hh:mm format.
     * @param hour hour of the time.
     * @param minute minute of the time.
     */
    Time (int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    /**
     * Convert an appropriate string into a Time object.
     * @param time a time in string form, of format "hh:mm".
     * @return the time object that corresponds to the string.
     */
    public static Time convertTime(String time) {
        if (time == "9:30"){
            return MORNING;
        }
        else if (time == "14:00"){
            return AFTERNOON;
        }
        else {
            return null;
        }
    }

    //TODO: add method comment describing purpose of this method
    @Override
    public String toString(){
        switch(this) {
            case MORNING: return "9:30";
            case AFTERNOON: return "14:00";
            default: return null;
        }
    }
}