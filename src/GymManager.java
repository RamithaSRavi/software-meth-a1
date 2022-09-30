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
        this.pilates = new FitnessClass("Pilates");
        this.cardio = new FitnessClass("Cardio");
        this.spinning = new FitnessClass("Spinning");
    }

    /**
     * Continuoulsy read user's command lines and terminate when user inputs "Q".
     */
    public void run() {
        //TODO: make run() less than 40 lines by creating helper methods
        MemberDatabase memberDatabase = new MemberDatabase();
        System.out.println("Gym Manager running...");
        boolean runProgram = true;
        Scanner sc = new Scanner(System.in);

        while(runProgram) {
            String line = sc.nextLine();
            try {
                StringTokenizer input = new StringTokenizer(line, " ");
                String command = input.nextToken();
                switch (command) {
                    case "A":
                        String fname = input.nextToken();
                        String lname = input.nextToken();
                        String stringBirthDate = input.nextToken();
                        Date birthDate = new Date(stringBirthDate);
                        Date todayDate = new Date();

                        if(!birthDate.isValid()) {
                            System.out.println("DOB " + birthDate.toString() + ": invalid calendar date!");
                            break;
                        }else if(birthDate.compareTo(todayDate) == 0 || birthDate.compareTo(todayDate) > 0) {
                            System.out.println("DOB " + birthDate.toString() + ": cannot be today or a future date!");
                            break;
                        }else if(todayDate.getYear() - birthDate.getYear() < 18) {
                            System.out.println("DOB " + birthDate.toString() + ": must be 18 or older to join!");
                            break;
                        }

                        String stringExpDate = input.nextToken();
                        Date expiryDate =  new Date(stringExpDate);

                        if(!expiryDate.isValid()) {
                            System.out.println("Expiration date " + expiryDate.toString() + ": invalid calendar date!");
                            break;
                        }

                        String city = input.nextToken();
                        boolean validCity = false;
                        for(Member.Location loc : Member.Location.values()) {
                            if(loc.toString().equals(city)) {
                                validCity = true;
                                Member.Location cityLoc = loc;
                            }
                        }
                        if(validCity == false) {
                            System.out.println(city + ": invalid location!");
                            break;
                        }

                        Member member = new Member(fname, lname, birthDate, expiryDate, city);
                        if(memberDatabase.add(member) == false) {
                            System.out.println(fname + " " + lname + " is already in the database.");
                            break;
                        }

                        System.out.println(fname + " " + lname + " added.");
                        break;

                    case "R":
                        fname = input.nextToken();
                        lname = input.nextToken();
                        stringBirthDate = input.nextToken();
                        birthDate = new Date(stringBirthDate);

                        member = new Member(fname, lname, birthDate);
                        if(memberDatabase.remove(member) == false){
                            System.out.println(fname + " " + lname + " is not in the database.");
                            break;
                        }
                        System.out.println(fname + " " + lname + " removed.");

                        break;
                    case "P":
                        System.out.println("-list of members-");
                        memberDatabase.print();
                        System.out.println("-end of list-");
                        break;
                    case "PC":
                        System.out.println("-list of members sorted by county and zip code-");
                        memberDatabase.printByCounty();
                        System.out.println("-end of list-");
                        break;
                    case "PN":
                        System.out.println("-list of members sorted by last name, and first name-");
                        memberDatabase.printByName();
                        System.out.println("-end of list-");
                        break;
                    case "PD":
                        System.out.println("-list of members sorted by membership expiration date-");
                        memberDatabase.printByExpirationDate();
                        System.out.println("-end of list-");
                        break;
                    case "S":
                        System.out.println("-Fitness classes-");
                        System.out.println("Pilates - JENNIFER 9:30");
                        System.out.println(this.pilates.printSchedule());
                        System.out.println("Spinning - DENISE 14:00");
                        System.out.println(this.spinning.printSchedule());
                        System.out.println("Cardio - KIM 14:00");
                        System.out.println(this.cardio.printSchedule());
                        break;
                    case "C":
                        String classType = input.nextToken();
                        if(classType.equals("Pilates") || classType.equals("Spinning") || classType.equals("Cardio")) {
                            System.out.println(classType + " class does not exist.");
                            break;
                        }

                        fname = input.nextToken();
                        lname = input.nextToken();
                        stringBirthDate = input.nextToken();
                        birthDate = new Date(stringBirthDate);
                        if(!birthDate.isValid()) {
                            System.out.println("DOB " + birthDate.toString() + ": invalid calendar date!");
                            break;
                        }

                        member = new Member(fname, lname, birthDate);
                        int memberInd = memberDatabase.find(member);
                        if(memberInd == -1) {
                            System.out.println(fname + " " + lname + " is not in the database.");
                            break;
                        } else if (memberDatabase.getMList()[memberInd].expire.compareTo(new Date()) < 0 ) {
                            System.out.println(fname + " " + lname + " " + memberDatabase.getMList()[memberInd].expire.toString() + " membership expired.");
                            break;
                        }

                        switch(classType) {
                            case "Pilates":
                                if(this.pilates.checkMemberStatus(member)) {
                                    System.out.println(fname + " " + lname + " has already checked in Pilates.");
                                    break;
                                }
                                this.pilates.checkIn(member);
                                break;
                            case "Spinning":
                                if(this.spinning.checkMemberStatus(member)) {
                                    System.out.println(fname + " " + lname + " has already checked in Spinning.");
                                    break;
                                }else if(this.cardio.checkMemberStatus(member)) {
                                    System.out.println("Spinning time conflict -- " + fname + " " + lname + " has already checked in Cardio.");
                                    break;
                                }
                                this.spinning.checkIn(member);
                                break;
                            case "Cardio":
                                if(this.cardio.checkMemberStatus(member)) {
                                    System.out.println(fname + " " + lname + " has already checked in Cardio.");
                                    break;
                                }else if(this.spinning.checkMemberStatus(member)) {
                                    System.out.println("Cardio time conflict -- " + fname + " " + lname + " has already checked in Spinning.");
                                    break;
                                }
                                this.cardio.checkIn(member);
                                break;
                        }
                        System.out.println(fname + " " + lname + " checked in " + classType);
                        break;
                    case "D":
                        classType = input.nextToken();
                        if(!(classType.equals("Pilates") || classType.equals("Spinning") || classType.equals("Cardio"))) {
                            System.out.println(classType + " class does not exist.");
                            break;
                        }
                        fname = input.nextToken();
                        lname = input.nextToken();
                        stringBirthDate = input.nextToken();
                        birthDate = new Date(stringBirthDate);
                        if(!birthDate.isValid()) {
                            System.out.println("DOB " + birthDate.toString() + ": invalid calendar date!");
                            break;
                        }
                        member = new Member(fname, lname, birthDate);

                        switch(classType) {
                            case "Pilates":
                                if(this.pilates.checkMemberStatus(member)) {
                                    this.pilates.dropClass(member);
                                } else {
                                    System.out.println(fname + lname + " is not a participant in Pilates.");
                                }
                                break;
                            case "Spinning":
                                if(this.spinning.checkMemberStatus(member)) {
                                    this.spinning.dropClass(member);
                                } else {
                                    System.out.println(fname + lname + " is not a participant in Spinning.");
                                }
                                break;
                            case "Cardio":
                                if(this.cardio.checkMemberStatus(member)) {
                                    this.cardio.dropClass(member);
                                } else {
                                    System.out.println(fname + lname + " is not a participant in Cardio.");
                                }
                                break;
                        }
                        System.out.println(fname + lname + " dropped " + classType);
                        break;
                    case "Q":
                        System.out.println("Gym Manager terminated.");
                        System.exit(0);
                        break;
                    default:
                        System.out.println(command + " is an invalid command!");
                        break;
                }
            } catch (Exception e){
                continue;
            }
        }
    }
}