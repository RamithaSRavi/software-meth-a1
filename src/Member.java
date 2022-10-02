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
    public static final int LESS_THAN = -1;
    public static final int GREATER_THAN = 1;
    public static final int EQUAL_TO = 0;

    public enum Location {
        BRIDGEWATER ("08807", "SOMERSET"),
        EDISON ("08837", "MIDDLESEX"),
        FRANKLIN ("08873", "SOMERSET"),
        PISCATAWAY ("08854", "MIDDLESEX"),
        SOMERVILLE ("08876", "SOMERSET");

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
            this.county = county.toUpperCase();
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
    //TODO: fix magic numbers
    public int compareTo(Member member) {
        if (this.lname.compareTo(member.getLname()) == 0){
            if (this.fname.compareTo(member.getFname()) < 0){
                return LESS_THAN;
            }
            else if (this.fname.compareTo(member.getFname()) > 0){
                return GREATER_THAN;
            }
            else {
                return EQUAL_TO;
            }
        }
        else {
            if (this.lname.compareTo(member.getLname()) < 0) {
                return LESS_THAN;
            }
            else {
                return GREATER_THAN;
            }
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
        int expectedOutput = 0;
        int actualOutput = member1.compareTo(member2);
        System.out.println("**Test Case 1: Two members with the same first and last names: ");
        testResult(member1, member2, expectedOutput, actualOutput);

        memberOneDOB = new Date("9/9/1977");
        memberTwoDOB = new Date("10/7/1977");
        member1 = new Member("Roy", "Brooks", memberOneDOB);
        member2 = new Member("Carl", "Brown", memberTwoDOB);
        expectedOutput = -1;
        actualOutput = member1.compareTo(member2);
        System.out.println("**Test Case 2: Members should be sorted by last name first: ");
        testResult(member1, member2, expectedOutput, actualOutput);

        memberOneDOB = new Date("10/7/1977");
        memberTwoDOB = new Date("9/9/1977");
        member1 = new Member("Carl", "Brown", memberOneDOB);
        member2 = new Member("Roy", "Brooks", memberTwoDOB);
        expectedOutput = 1;
        actualOutput = member1.compareTo(member2);
        System.out.println("**Test Case 3: Members should be sorted by last name first: ");
        testResult(member1, member2, expectedOutput, actualOutput);

        memberOneDOB = new Date("5/1/1996");
        memberTwoDOB = new Date("1/20/2003");
        member1 = new Member("Jane", "Doe", memberOneDOB);
        member2 = new Member("John", "Doe", memberTwoDOB);
        expectedOutput = -1;
        actualOutput = member1.compareTo(member2);
        System.out.println("**Test Case 4: If members have the same last name, " +
                "they should be sorted alphabetically by first name");
        testResult(member1, member2, expectedOutput, actualOutput);

        memberOneDOB = new Date("1/20/2004");
        memberTwoDOB = new Date("1/20/2003");
        member1 = new Member("John", "Doe", memberOneDOB);
        member2 = new Member("John", "Doe", memberTwoDOB);
        expectedOutput = 0;
        actualOutput = member1.compareTo(member2);
        System.out.println("**Test Case 5: Two members with the same first name and last name: ");
        testResult(member1, member2, expectedOutput, actualOutput);

        memberOneDOB = new Date("2/29/2000");
        memberTwoDOB = new Date("9/9/1997");
        member1 = new Member("Duke", "Ellington", memberOneDOB);
        member2 = new Member("Roy", "Brooks", memberTwoDOB);
        expectedOutput = 1;
        actualOutput = member1.compareTo(member2);
        System.out.println("**Test Case 6: Members should be sorted by last name: ");
        testResult(member1, member2, expectedOutput, actualOutput);

        memberOneDOB = new Date("7/15/1977");
        memberTwoDOB = new Date("2/29/2000");
        member1 = new Member("Kate", "Lindsey", memberOneDOB);
        member2 = new Member("Duke", "Ellington", memberTwoDOB);
        expectedOutput = 1;
        actualOutput = member1.compareTo(member2);
        System.out.println("**Test Case 7: Members should be sorted by last name: ");
        testResult(member1, member2, expectedOutput, actualOutput);

        memberOneDOB = new Date("7/15/1997");
        memberTwoDOB = new Date("12/1/1989");
        member1 = new Member("Kate", "Lindsey", memberOneDOB);
        member2 = new Member("Mary", "Lindsey", memberTwoDOB);
        expectedOutput = -1;
        actualOutput = member1.compareTo(member2);
        System.out.println("**Test Case 8: If members have the same last name, " +
                "they should be sorted alphabetically by first name");
        testResult(member1, member2, expectedOutput, actualOutput);

        memberOneDOB = new Date("12/1/1989");
        memberTwoDOB = new Date("7/15/1997");
        member1 = new Member("Mary", "Lindsey", memberOneDOB);
        member2 = new Member("Kate", "Lindsey", memberTwoDOB);
        expectedOutput = 1;
        actualOutput = member1.compareTo(member2);
        System.out.println("**Test Case 9: If members have the same last name, " +
                "they should be sorted alphabetically by first name");
        testResult(member1, member2, expectedOutput, actualOutput);

        memberOneDOB = new Date("12/1/1989");
        memberTwoDOB = new Date("3/31/1990");
        member1 = new Member("Mary", "Lindsey", memberOneDOB);
        member2 = new Member("April", "March", memberTwoDOB);
        expectedOutput = -1;
        actualOutput = member1.compareTo(member2);
        System.out.println("**Test Case 10: Members should be sorted by last name: ");
        testResult(member1, member2, expectedOutput, actualOutput);

        memberOneDOB = new Date("3/31/1990");
        memberTwoDOB = new Date("12/1/1989");
        member1 = new Member("April", "March", memberOneDOB);
        member2 = new Member("Mary", "Lindsey", memberTwoDOB);
        expectedOutput = 1;
        actualOutput = member1.compareTo(member2);
        System.out.println("**Test Case 11: Members should be sorted by last name: ");
        testResult(member1, member2, expectedOutput, actualOutput);

        memberOneDOB = new Date("1/31/2023");
        memberTwoDOB = new Date("3/31/1990");
        member1 = new Member("Bill", "Scanlan", memberOneDOB);
        member2 = new Member("April", "March", memberTwoDOB);
        expectedOutput = 1;
        actualOutput = member1.compareTo(member2);
        System.out.println("**Test Case 12: Members should be sorted by last name: ");
        testResult(member1, member2, expectedOutput, actualOutput);

        memberOneDOB = new Date("3/31/1990");
        memberTwoDOB = new Date("5/1/1999");
        member1 = new Member("Paul", "Siegel", memberOneDOB);
        member2 = new Member("Bill", "Scanlan", memberTwoDOB);
        expectedOutput = 1;
        actualOutput = member1.compareTo(member2);
        System.out.println("**Test Case 13: Members should be sorted by last name: ");
        testResult(member1, member2, expectedOutput, actualOutput);
    }

    private static void testResult(Member member1, Member member2, int expectedOutput, int actualOutput){
        System.out.println(member1.getFname() + " " + member1.getLname() + " comparesTo("
                + member2.getFname() + " " + member2.getLname() + ")");
        System.out.print(" returns " + actualOutput);
        if (actualOutput == expectedOutput)
            System.out.println(", PASS.\n");
        else
            System.out.println(", FAIL.\n");
    }
}
