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
    public FitnessClass(String cName, String instructor, String classTime) {
        this.className = cName;
        this.instructor = instructor.toUpperCase();
        this.classTime = Time.convertTime(classTime);
        this.checkedIn = new Member[4];
    }

    /**
     * Gets the class name of the fitness class object.
     * @return the class name.
     */
    public String getClassName(){
        return this.className;
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
        Member[] checkedList = this.checkedIn;
        for(int i=0; i<checkedList.length; i++) {
            if(checkedList[i] == null) {
                checkedList[i] = member;
                if(i+1 >= checkedList.length) {
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
            if(checkedIn[i] != null && checkedIn[i].equals(member)){
                for (int j = i; this.checkedIn[j] != null && j < this.checkedIn.length-1; j++){
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
        String str = this.className.charAt(0) + this.className.toLowerCase().substring(1);
        str += " - " + this.instructor + " " + this.classTime.toString();
        Member[] checkedList = this.checkedIn;
        for(int i=0; i<checkedList.length; i++) {
            if (checkedList[0] == null){
                return str;
            }
            if(checkedList[i] != null) {
                if(i==0) str += "\n\t" + "** participants **";
                str += "\n" + "\t" + "\t" + checkedList[i].toString();
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