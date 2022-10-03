import java.util.Scanner;
import java.util.StringTokenizer;
/**
 * The User Interface class to process user command lines and interact with MemberDatabase and FitnessClass classes.
 * @author Ramitha Ravishankar, Gloria Liu
 */
public class GymManager {
    private FitnessClass pilates;
    private FitnessClass cardio;
    private FitnessClass spinning;

    /**
     * Default constructor used to instantiate GymManager object.
     */
    public GymManager() {
        this.pilates = new FitnessClass("PILATES", "Jennifer", "9:30");
        this.cardio = new FitnessClass("CARDIO", "Kim", "14:00");
        this.spinning = new FitnessClass("SPINNING", "Denise", "14:00");
    }

    /**
     * Checking if all the dates are valid and make a member eligible.
     * Make sure all dates are valid dates, and member is > 18.
     * @param birthDate the birthdate of the member to be added.
     * @param todayDate the current date.
     * @param expiryDate the expiration date of the membership.
     * @return whether the date allows the member to get a membership.
     */
    private boolean checkDateValidity(Date birthDate, Date todayDate, Date expiryDate) {
        if(!birthDate.isValid()) {
            System.out.println("DOB " + birthDate.toString() + ": invalid calendar date!");
            return false;
        }else if(birthDate.compareTo(todayDate) == 0 || birthDate.compareTo(todayDate) > 0) {
            System.out.println("DOB " + birthDate.toString() + ": cannot be today or a future date!");
            return false;
        }else if(todayDate.getYear() - birthDate.getYear() < 18) {
            System.out.println("DOB " + birthDate.toString() + ": must be 18 or older to join!");
            return false;
        }else if (todayDate.getYear() - birthDate.getYear() == 18){
            if (todayDate.getMonth() - birthDate.getMonth() < 0){
                System.out.println("DOB " + birthDate.toString() + ": must be 18 or older to join!");
                return false;
            }
            if (todayDate.getMonth() - birthDate.getMonth() == 0
                    && todayDate.getDay() - birthDate.getDay() < 0){
                System.out.println("DOB " + birthDate.toString() + ": must be 18 or older to join!");
                return false;
            }
        }else if(!expiryDate.isValid()) {
            System.out.println("Expiration date " + expiryDate.toString() + ": invalid calendar date!");
            return false;
        }
        return true;
    }

    /**
     * Adds a member to the database.
     * @param input the input read from the command line.
     * @param memberDatabase the gym database the member should be added to.
     */
    private void addToMemberDatabase(StringTokenizer input, MemberDatabase memberDatabase) {
        String fname = input.nextToken();
        String lname = input.nextToken();
        Date birthDate = new Date(input.nextToken());
        Date todayDate = new Date();
        Date expiryDate =  new Date(input.nextToken());
        if(!checkDateValidity(birthDate, todayDate, expiryDate)) return;
        Location cityLoc = null;
        String city = input.nextToken().toUpperCase();
        boolean validCity = false;
        if (city.equals("PISCATAWAY")){
            validCity = true;
            cityLoc = Location.PISCATAWAY;
        } else if (city.equals("BRIDGEWATER")){
            validCity = true;
            cityLoc = Location.BRIDGEWATER;
        } else if (city.equals("EDISON")){
            validCity = true;
            cityLoc = Location.EDISON;
        } else if (city.equals("SOMERVILLE")){
            validCity = true;
            cityLoc = Location.SOMERVILLE;
        } else if (city.equals("FRANKLIN")){
            validCity = true;
            cityLoc = Location.FRANKLIN;
        }
        if(validCity == false) {
            System.out.println(city + ": invalid location!");
            return;
        }
        Member member = new Member(fname, lname, birthDate, expiryDate, cityLoc);
        if(memberDatabase.add(member) == false) {
            System.out.println(fname + " " + lname + " is already in the database.");
            return;
        }
        System.out.println(fname + " " + lname + " added.");
    }

    /**
     * Removes a member from the database.
     * @param input the input of the member read from the command line.
     * @param memberDatabase the database to remove the member from.
     */
    private void removeFromMemberDatabase(StringTokenizer input, MemberDatabase memberDatabase) {
        String fname = input.nextToken();
        String lname = input.nextToken();
        String stringBirthDate = input.nextToken();
        Date birthDate = new Date(stringBirthDate);

        Member member = new Member(fname, lname, birthDate);
        if(memberDatabase.remove(member) == false){
            System.out.println(fname + " " + lname + " is not in the database.");
            return;
        }
        System.out.println(fname + " " + lname + " removed.");
    }

    /**
     * Prints the members in the gym database.
     * @param memberDatabase the database to extract the member list from.
     */
    private void printMembers(MemberDatabase memberDatabase){
        if (memberDatabase.getMlist().length == 4){
            System.out.println("Member database is empty!");
        } else {
            System.out.println("\n-list of members-");
            memberDatabase.print();
            System.out.println("-end of list-\n");
        }
    }

    /**
     * Prints the members in the gym database sorted by county.
     * @param memberDatabase the database to extract the member list from.
     */
    private void printMembersByCounty(MemberDatabase memberDatabase) {
        if (memberDatabase.getMlist().length == 4){
            System.out.println("Member database is empty!");
        } else {
            System.out.println("\n-list of members sorted by county and zip code-");
            memberDatabase.printByCounty();
            System.out.println("-end of list-\n");
        }
    }

    /**
     * Prints the members in the gym database sorted alphabetically by name.
     * @param memberDatabase the database to extract the member list from.
     */
    private void printMembersByName(MemberDatabase memberDatabase) {
        if (memberDatabase.getMlist().length == 4){
            System.out.println("Member database is empty!");
        } else {
            System.out.println("\n-list of members sorted by last name, and first name-");
            memberDatabase.printByName();
            System.out.println("-end of list-\n");
        }
    }

    /**
     * Prints the members in the gym database by expiration date (earliest first).
     * @param memberDatabase the database to extract the member list from.
     */
    private void printMembersByExpiryDate(MemberDatabase memberDatabase) {
        if (memberDatabase.getMlist().length == 4){
            System.out.println("Member database is empty!");
        } else {
            System.out.println("\n-list of members sorted by membership expiration date-");
            memberDatabase.printByExpirationDate();
            System.out.println("-end of list-\n");
        }
    }

    /**
     * Prints the fitness schedule of the gym, including the instructors, participants, and times for the classes.
     */
    private void printFitnessSchedule(){
        System.out.println("\n--Fitness Classes--");
        System.out.println(this.pilates.printSchedule());
        System.out.println(this.spinning.printSchedule());
        System.out.println(this.cardio.printSchedule());
        System.out.println();
    }

    /**
     * Checks in a member into a fitness class based on whether the member has a valid membership.
     * @param input the input read from the command line with the member info.
     * @param memberDatabase the member database that has all member info, to validate the member.
     */
    private void checkInMember(StringTokenizer input, MemberDatabase memberDatabase){
        String classType = input.nextToken();
        if(!(classType.toUpperCase().equals(spinning.getClassName()) || classType.toUpperCase().equals(pilates.getClassName()) || classType.toUpperCase().equals(cardio.getClassName()))) {
            System.out.println(classType + " class does not exist.");
            return;
        }

        String fname = input.nextToken();
        String lname = input.nextToken();
        String stringBirthDate = input.nextToken();
        Date birthDate = new Date(stringBirthDate);
        if(!birthDate.isValid()) {
            System.out.println("DOB " + birthDate.toString() + ": invalid calendar date!");
            return;
        }

        Member member = new Member(fname, lname, birthDate);
        int memberInd = memberDatabase.findMember(member);
        if(memberInd == -1) {
            System.out.println(fname + " " + lname + " " + stringBirthDate + " is not in the database.");
            return;
        } else if (memberDatabase.getMlist()[memberInd].getExpire().compareTo(new Date()) < 0 ) {
            System.out.println(fname + " " + lname + " " + memberDatabase.getMlist()[memberInd].getExpire().toString() + " membership expired.");
            return;
        } else {
            member = memberDatabase.getMlist()[memberInd];
        }

        checkMemberStatusAndTimeConflictInClass(classType, member, fname, lname);
    }

    /**
     * Checks if a member can be checked into a fitness class based on whether they're checked in already or
     * if they have a time conflict.
     * @param classType the class the member is checking into.
     * @param member the member that is checking in.
     * @param fname the first name of the member.
     * @param lname the last name of the member.
     */
    private void checkMemberStatusAndTimeConflictInClass(String classType, Member member, String fname, String lname){
        switch(classType.toUpperCase()) {
            case "PILATES":
                if(this.pilates.checkMemberStatus(member)) {
                    System.out.println(fname + " " + lname + " has already checked in Pilates.");
                    break;
                }
                this.pilates.checkIn(member);
                System.out.println(fname + " " + lname + " checked in Pilates.");
                break;
            case "SPINNING":
                if(this.spinning.checkMemberStatus(member)) {
                    System.out.println(fname + " " + lname + " has already checked in Spinning.");
                    break;
                }else if(this.cardio.checkMemberStatus(member)) {
                    System.out.println("Spinning time conflict -- " + fname + " " + lname + " has already checked in Cardio.");
                    break;
                }
                this.spinning.checkIn(member);
                System.out.println(fname + " " + lname + " checked in Spinning.");
                break;
            case "CARDIO":
                if(this.cardio.checkMemberStatus(member)) {
                    System.out.println(fname + " " + lname + " has already checked in Cardio.");
                    break;
                }else if(this.spinning.checkMemberStatus(member)) {
                    System.out.println("Cardio time conflict -- " + fname + " " + lname + " has already checked in Spinning.");
                    break;
                }
                this.cardio.checkIn(member);
                System.out.println(fname + " " + lname + " checked in Cardio.");
        }
    }

    /**
     * Checks if a member can be dropped from a class, and then drops the member if possible.
     * @param classType the class the member wants to be dropped from.
     * @param member the member to be dropped.
     * @param fname the first name of the member.
     * @param lname the last name of the member.
     */
    private void checkMemberStatusAndDropMember(String classType, Member member, String fname, String lname) {
        switch(classType.toUpperCase()) {
            case "PILATES":
                if(this.pilates.checkMemberStatus(member)) {
                    this.pilates.dropClass(member);
                    System.out.println(fname + " " + lname + " dropped " + classType + ".");
                } else {
                    System.out.println(fname + " " + lname + " is not a participant in Pilates.");
                }
                break;
            case "SPINNING":
                if(this.spinning.checkMemberStatus(member)) {
                    this.spinning.dropClass(member);
                    System.out.println(fname + " " + lname + " dropped " + classType + ".");
                } else {
                    System.out.println(fname + " " + lname + " is not a participant in Spinning.");
                }
                break;
            case "CARDIO":
                if(this.cardio.checkMemberStatus(member)) {
                    this.cardio.dropClass(member);
                    System.out.println(fname + " " + lname + " dropped " + classType + ".");
                } else {
                    System.out.println(fname + " " + lname + " is not a participant in Cardio.");
                }
                break;
        }
    }

    /**
     * Drops a member read in from the command line input from a class.
     * @param input the command line input with the member and class information.
     */
    private void dropMember(StringTokenizer input) {
        String classType = input.nextToken();
        if(!(classType.toUpperCase().equals(spinning.getClassName()) || classType.toUpperCase().equals(pilates.getClassName()) || classType.toUpperCase().equals(cardio.getClassName()))) {
            System.out.println(classType + " class does not exist.");
            return;
        }
        String fname = input.nextToken();
        String lname = input.nextToken();
        String stringBirthDate = input.nextToken();
        Date birthDate = new Date(stringBirthDate);
        if(!birthDate.isValid()) {
            System.out.println("DOB " + birthDate.toString() + ": invalid calendar date!");
            return;
        }
        Member member = new Member(fname, lname, birthDate);
        checkMemberStatusAndDropMember(classType, member, fname, lname);
    }

    /**
     * Reads the first character of the command line and calls respective method to perform operation.
     * @param input StringTokenizer for the command string
     * @param memberDatabase the member database that has all existing member info
     * @param runProgram status of program, true if user has not entered "Q", else false
     * @param command user's command in the terminal
     * @return
     */
    private boolean readCommand(StringTokenizer input, MemberDatabase memberDatabase, boolean runProgram, String command) {
        switch (command) {
            case "A":
                addToMemberDatabase(input, memberDatabase);
                break;
            case "R":
                removeFromMemberDatabase(input, memberDatabase);
                break;
            case "P":
                printMembers(memberDatabase);
                break;
            case "PC":
                printMembersByCounty(memberDatabase);
                break;
            case "PN":
                printMembersByName(memberDatabase);
                break;
            case "PD":
                printMembersByExpiryDate(memberDatabase);
                break;
            case "S":
                printFitnessSchedule();
                break;
            case "C":
                checkInMember(input, memberDatabase);
                break;
            case "D":
                dropMember(input);
                break;
            case "Q":
                System.out.println("Gym Manager terminated.");
                runProgram = false;
                break;
            default:
                System.out.println(command + " is an invalid command!");
        }
        if(runProgram) return true;
        else return false;
    }

    /**
     * Continuously read user's command lines and terminate when user inputs "Q".
     */
    public void run() {
        MemberDatabase memberDatabase = new MemberDatabase();
        System.out.println("Gym Manager running...");
        boolean runProgram = true;
        Scanner sc = new Scanner(System.in);

        while(runProgram) {
            String line = sc.nextLine();
            if (line.isEmpty()){
                System.out.println();
                continue;
            }
            StringTokenizer input = new StringTokenizer(line, " ");
            String command = input.nextToken();

            if(!readCommand(input, memberDatabase, runProgram, command)) {
                runProgram = false;
                System.exit(0);
            }
        }
    }
}