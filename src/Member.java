/**
 * The class for each individual gym member.
 * This stores the gym member's first name, last name, date of birth, membership
 * expiration date, and their primary gym location, and has instance methods for
 * comparing a member to other members.
 * @author Ramitha Ravishankar, Gloria Liu
 */
public class Member implements Comparable<Member>{
    private String fname;
    private String lname;
    private Date dob;
    private Date expire;
    private Location location;

    public static final String EXPIRED_MEMBERSHIP = "expired ";
    public static final String NOT_EXPIRED_MEMBERSHIP = "expires ";

    public enum Location {
        BRIDGEWATER ("08807", "Somerset"),
        EDISON ("08837", "Middlesex"),
        FRANKLIN ("08873", "Somerset"),
        PISCATAWAY ("08854", "Middlesex"),
        SOMERVILLE ("08876", "Somerset");

        private final String zip;
        private final String county;

        /**
         * Gets the county of the Location object.
         * @return the Location object's county.
         */
        public String getCounty(){
            return this.county;
        }

        /**
         * Gets the zip of the Location object.
         * @return the Location object's zip.
         */
        public String getZip(){
            return this.zip;
        }

        Location(String zip, String county) {
            this.zip = zip;
            this.county = county;
        }

        //TODO: need to overide the toString method of enum otherwise it returns just the name
        @Override
        public String toString(){
            switch(this) {
                case BRIDGEWATER: return "BRIDGEWATER, " + this.zip + ", " + this.county;
                case EDISON: return "EDISON, " + this.zip + ", " + this.county;
                case FRANKLIN: return "FRANKLIN, " + this.zip + ", " + this.county;
                case PISCATAWAY: return "PISCATAWAY, " + this.zip + ", " + this.county;
                case SOMERVILLE: return "SOMERVILLE, " + this.zip + ", " + this.county;
                default: return null;
            }
        }
    }

    /**
     * Parameterized constructor used to instantiate a Member object.
     * The first name, last name, and date of birth are sufficient to uniquely identify a member.
     * @param fname first name of the member.
     * @param lname last name of the member.
     * @param dob date of birth of the member.
     */
    public Member (String fname, String lname, Date dob) {
        this.fname = fname.toLowerCase();
        this.lname = lname.toLowerCase();
        this.dob = dob;
    }

    /**
     * Parameterized constructor used to instantiate a Member object.
     * @param fname first name of the member.
     * @param lname lname last name of the member.
     * @param dob date of birth of the member.
     * @param expire date that membership expires.
     * @param location city name identifying the gym location.
     */
    public Member (String fname, String lname, Date dob, Date expire, Location location) {
        this.fname = fname.toLowerCase();
        this.lname = lname.toLowerCase();
        this.dob = dob;
        this.expire = expire;
        this.location = location;
    }
    @Override
    public String toString() {
        Date currentDate = new Date();
        if (expire.compareTo(currentDate) < 0){
            return (fname.substring(0, 1).toUpperCase() + fname.substring(1) + " "
                    + lname.substring(0, 1).toUpperCase() + lname.substring(1) + ", "
                    + "DOB: " + dob.toString() + ", "
                    + "Membership " + EXPIRED_MEMBERSHIP + expire.toString() + ", "
                    + "Location: " + location.toString());
        }
        else {
            return (fname.substring(0, 1).toUpperCase() + fname.substring(1) + " "
                    + lname.substring(0, 1).toUpperCase() + lname.substring(1) + ", "
                    + "DOB: " + dob.toString() + ", "
                    + "Membership " + NOT_EXPIRED_MEMBERSHIP + expire.toString() + ", "
                    + "Location: " + location.toString());
        }
    }

    /**
     * States if two member objects are equal
     * @param obj: Member object that should be compared
     * @return true if both member objects are equal
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Member){
            Member member = (Member) obj;
            return (this.fname.equals(member.fname) && this.lname.equals(member.lname)
                    && this.dob.equals(member.dob));
        } else {
            return false;
        }
    }

    /**
     * Compares this member to another member, to see who should come first
     * alphabetically by first looking at last name then first name.
     * @param member the other member this member is compared with.
     * @return 0 if the first & last are equal, a negative number if this member
     * is alphabetically in front of the member in the parameter, and a positive
     * number if this member comes alphabetically after the parameter member.
     */
    @Override
    public int compareTo(Member member) {
        if (this.lname.compareTo(member.getLname()) == 0){
            return (this.fname.compareTo(member.getFname()));
        }
        else {
            return (this.lname.compareTo(member.getLname()));
        }
    }

    /**
     * Gets first name of the member.
     * @return the first name of the member.
     */
    public String getFname() {
        return this.fname;
    }

    /**
     * Gets last name of the member.
     * @return the last name of the member.
     */
    public String getLname() {
        return this.lname;
    }

    /**
     * Gets the date of birth of the member.
     * @return the date of birth of the member.
     */
    public Date getDob() {
        return this.dob;
    }

    /**
     * Gets the expiration date of the member's gym membership.
     * @return the expiration date of member's gym membership.
     */
    public Date getExpire() {
        return this.expire;
    }

    /**
     * Gets the location of the member's gym membership.
     * @return the location of member's gym membership.
     */
    public Location getLocation() {
        return this.location;
    }

    /**
     * Testbed main for compareTo() method.
     */
    public static void main(String[] args){
        Date memberOneDOB = new Date("9/9/1977");
        Date memberTwoDOB = new Date("8/8/1977");
        Member member1 = new Member("Roy", "Brooks", memberOneDOB);
        Member member2 = new Member("Roy", "Brooks", memberTwoDOB);
        int expectedOutput = -1;
        int actualOutput = member1.compareTo(member2);
        System.out.println("**Test Case 1: ");
        testResult(member1, member2, expectedOutput, actualOutput);
    }

    private static void testResult(Member member1, Member member2, int expectedOutput, int actualOutput){
        System.out.println(member1.toString() + " comparesTo(" + member2.toString() + ")");
        System.out.print(" returns " + actualOutput);
        if (actualOutput == expectedOutput)
            System.out.println(", PASS.\n");
        else
            System.out.println(", FAIL.\n");
    }
}
