/**
 * The class that defines a fitness class the members can check in.
 * @author Ramitha Ravishankar, Gloria Liu
 */

public class FitnessClass {
    private String className;
    private String instructor;
    private Time classTime;
    private Member[] checkedIn;

    /**
     * Parameterized constructor used to instantiate a FitnessClass object.
     * @param cName name of the fitness class.
     */
    public FitnessClass(String cName) {
        this.checkedIn = new Member[4];
        this.className = cName;
    }

    /**
     * Gets the class name of the fitness class object.
     * @return the class name.
     */
    public String getClassName(){
        return this.className;
    }

    public enum Time {
        MORNING (9, 30),
        AFTERNOON (14, 0);
        private final int hour;
        private final int minute;
        Time (int hour, int minute) {
            this.hour = hour;
            this.minute = minute;
        }
        public static Time convertTime(String time) {
            //TODO: implement
            return AFTERNOON;
        }
    }

    /**
     * Check if the member has previously checked in today.
     * @param member to check if is enrolled in class.
     * @return boolean true if member checked in already, false if member did not check in.
     */
    public boolean checkMemberStatus(Member member) {
        for(int i=0; i<this.checkedIn.length; i++) {
            if(checkedIn[i] != null) {
                if (member.equals(checkedIn[i])) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Check a member into a class.
     * @param member member to check in.
     */
    public void checkIn(Member member) {
        for(int i=0; i<this.checkedIn.length; i++) {
            if(this.checkedIn[i] == null) {
                this.checkedIn[i] = member;
                if(i+1 >= this.checkedIn.length) {
                    this.grow();
                }
                break;
            }
        }
    }
    /**
     * Drop a member from a class.
     * @param member member to drop.
     */
    public void dropClass(Member member) {
        for (int i = 0; i < this.checkedIn.length; i++){
            //recopy list without member to delete, put all null to the end
            if(checkedIn[i].equals(member)){
                for (int j = i; this.checkedIn[j] != null && j < this.checkedIn.length; j++){
                    this.checkedIn[j] = this.checkedIn[j + 1];
                }
                break;
            }
        }
    }

    /**
     * Print the members who are participating in the fitness class.
     * Members are printed from the checkedIn array.
     * @return String
     */
    public String printSchedule() {
        String str = "";
        for(int i=0; i<this.checkedIn.length; i++) {
            if(this.checkedIn[i] != null) {
                str += "\n";
                if(i==0) str += "\t" + "** participants **\n";
                str += "\t" + "\t" + this.checkedIn[i].toString();
            }
        }
        return str;
    }

    /**
     * Increments the capacity of the list of members that can check into the fitness class by 4.
     */
    private void grow(){
        Member[] currentList = this.checkedIn;
        Member[] newList = new Member[currentList.length + 4];
        for(int i = 0; i < currentList.length; i++) {
            newList[i] = currentList[i];
        }
        this.checkedIn = newList;
    }
}