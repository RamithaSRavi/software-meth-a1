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

    private void printMembers(MemberDatabase memberDatabase){
        if (memberDatabase.getMlist().length == 4){
            System.out.println("Member database is empty!");
        } else {
            System.out.println("\n-list of members-");
            memberDatabase.print();
            System.out.println("-end of list-\n");
        }
    }

    private void printMembersByCounty(MemberDatabase memberDatabase) {
        if (memberDatabase.getMlist().length == 4){
            System.out.println("Member database is empty!");
        } else {
            System.out.println("\n-list of members sorted by county and zip code-");
            memberDatabase.printByCounty();
            System.out.println("-end of list-\n");
        }
    }

    private void printMembersByName(MemberDatabase memberDatabase) {
        if (memberDatabase.getMlist().length == 4){
            System.out.println("Member database is empty!");
        } else {
            System.out.println("\n-list of members sorted by last name, and first name-");
            memberDatabase.printByName();
            System.out.println("-end of list-\n");
        }
    }

    private void printMembersByExpiryDate(MemberDatabase memberDatabase) {
        if (memberDatabase.getMlist().length == 4){
            System.out.println("Member database is empty!");
        } else {
            System.out.println("\n-list of members sorted by membership expiration date-");
            memberDatabase.printByExpirationDate();
            System.out.println("-end of list-\n");
        }
    }

    private void printFitnessSchedule(){
        System.out.println(this.pilates.printSchedule());
        System.out.println(this.spinning.printSchedule());
        System.out.println(this.cardio.printSchedule());
        System.out.println();
    }

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

    private void checkMemberStatusAndDropMember(String classType, Member member, String fname, String lname) {
        switch(classType.toUpperCase()) {
            case "PILATES":
                if(this.pilates.checkMemberStatus(member)) {
                    this.pilates.dropClass(member);
                    System.out.println(fname + " " + lname + " dropped " + classType + ".");
                } else {
                    System.out.println(fname + " " + lname + " is not a participant in Pilates.");
                }
            case "SPINNING":
                if(this.spinning.checkMemberStatus(member)) {
                    this.spinning.dropClass(member);
                    System.out.println(fname + " " + lname + " dropped " + classType + ".");
                } else {
                    System.out.println(fname + " " + lname + " is not a participant in Spinning.");
                }
            case "CARDIO":
                if(this.cardio.checkMemberStatus(member)) {
                    this.cardio.dropClass(member);
                    System.out.println(fname + " " + lname + " dropped " + classType + ".");
                } else {
                    System.out.println(fname + " " + lname + " is not a participant in Cardio.");
                }
        }
    }

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