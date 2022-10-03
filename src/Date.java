import java.util.Calendar;
/**
 * The class for creating and comparing dates, and checking date validity.
 * This stores the gym member's first name, last name, date of birth, membership
 * expiration date, and their primary gym location, and has instance methods for
 * comparing a member to other members.
 * @author Ramitha Ravishankar, Gloria Liu
 */
public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;
    private static final int YEAR_INDEX = 2;
    private static final int MONTH_INDEX = 0;
    private static final int DAY_INDEX = 1;
    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUARTERCENTENNIAL = 400;
    public static final int JANUARY = 1;
    public static final int FEBRUARY = 2;
    public static final int MARCH = 3;
    public static final int APRIL = 4;
    public static final int MAY = 5;
    public static final int JUNE = 6;
    public static final int JULY = 7;
    public static final int AUGUST = 8;
    public static final int SEPTEMBER = 9;
    public static final int OCTOBER = 10;
    public static final int NOVEMBER = 11;
    public static final int DECEMBER = 12;
    public static final int MINIMUM_DATE = 1;
    public static final int LONG_MONTH_DAYS = 31;
    public static final int SHORT_MONTH_DAYS = 30;
    public static final int FEB_LEAP_YEAR_DAYS = 29;
    public static final int FEB_NON_LEAP_YEAR_DAYS = 28;
    public static final int MIN_YEAR = 1900;
    public static final int LESS_THAN = -1;
    public static final int GREATER_THAN = 1;
    public static final int EQUAL_TO = 0;

    /**
     * Default constructor which creates a date object with the current date.
     */
    public Date() {
        Calendar calendar = Calendar.getInstance();
        this.year = calendar.get(Calendar.YEAR);
        this.month = calendar.get(Calendar.MONTH) + 1;
        this.day = calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * Takes a date in string format and creates a Date object
     * @param date string in the format “mm/dd/yyyy”
     */
    public Date(String date) {
        String[] splitDate = date.split("/");
        this.year = Integer.parseInt(splitDate[YEAR_INDEX]);
        this.month = Integer.parseInt(splitDate[MONTH_INDEX]);
        this.day = Integer.parseInt(splitDate[DAY_INDEX]);
    }

    /**
     * Compares this date to another date, to see which date came first.
     * @param date the date to compare this date object to.
     * @return 0 if the dates are equal, -1 if this date is before the parameter
     * date, 1 if this date is later than the parameter date.
     */
    @Override
    public int compareTo(Date date) {
        if (this.year == date.getYear()){
            if (this.month == date.getMonth()){
                if (this.day == date.getDay()){
                    return EQUAL_TO;
                }
                else {
                    if (this.day < date.getDay()){
                        return LESS_THAN;
                    }
                    else {
                        return GREATER_THAN;
                    }
                }
            }
            else {
                if (this.month < date.getMonth()){
                    return LESS_THAN;
                }
                else {
                    return GREATER_THAN;
                }
            }
        }
        else {
            if (this.year < date.getYear()){
                return LESS_THAN;
            }
            else {
                return GREATER_THAN;
            }
        }
    }

    /**
     * Check if a date is a valid calendar date.
     * Checks for a valid day for the date's month, including on leap years.
     * @return true if it's valid, false otherwise.
     */
    public boolean isValid() {
        if (year < MIN_YEAR){
            return false;
        }
        if (month == FEBRUARY){
            if (isLeapYear()){
                return MINIMUM_DATE <= day && day <= FEB_LEAP_YEAR_DAYS;
            }
            else {
                return MINIMUM_DATE <= day && day <= FEB_NON_LEAP_YEAR_DAYS;
            }
        }
        else if (month == APRIL || month == JUNE || month == SEPTEMBER ||
                month == NOVEMBER){
            return MINIMUM_DATE <= day && day <= SHORT_MONTH_DAYS;
        }
        else if (month == JANUARY || month == MARCH || month == MAY ||
                month == JULY || month == AUGUST || month == OCTOBER ||
                month == DECEMBER){
            return MINIMUM_DATE <= day && day <= LONG_MONTH_DAYS;
        }
        else {
            //not a valid month
            return false;
        }
    }

    /**
     * Gets the year of the date object.
     * @return the date object's year.
     */
    public int getYear(){
        return this.year;
    }

    /**
     * Gets the month of the date object.
     * @return the date object's month.
     */
    public int getMonth(){
        return this.month;
    }

    /**
     * Gets the day of the date object.
     * @return the date object's day of the month.
     */
    public int getDay(){
        return this.day;
    }

    /**
     * Convert the date into a string format.
     * @return the date in a string format month/day/year, with no leading 0's.
     */
    public String toString(){
        return String.valueOf(month) + "/" + String.valueOf(day) + "/" +
                String.valueOf(year);
    }

    /**
     * Determines whether this date is equal in year, month, and day to another date.
     * @param date the other date to be compared.
     * @return true if the dates are equal, false otherwise.
     */
    public boolean equals(Date date){
        if(date instanceof Date) {
            return (this.year == date.getYear() && this.month == date.getMonth()
                    && this.day == date.getDay());
        } else {
            return false;
        }
    }

    /**
     * Helper method that determines if a year is a leap year.
     * @return true if the date's year is a leap year, false otherwise.
     */
    private boolean isLeapYear(){
        if (year % QUADRENNIAL == 0){
            if (year % CENTENNIAL == 0){
                if (year % QUARTERCENTENNIAL == 0){
                    return true;
                }
                else {
                    return false;
                }
            }
            else {
                return true;
            }
        }
        else {
            return false;
        }
    }

    /**
     * Testbed main to test the isValid() method.
     */
    public static void main(String[] args){
        //Test case 1
        Date date = new Date("2/29/2003");
        boolean expectedOutput = false;
        boolean actualOutput = date.isValid();
        System.out.println("**Test case #1: a date in a non-leap year has only 28 days in February: ");
        testResult(date, expectedOutput, actualOutput);

        //Test case 2
        date = new Date("9/2/2022");
        expectedOutput = true;
        actualOutput = date.isValid();
        System.out.println("**Test case #2: a valid date in September: ");
        testResult(date, expectedOutput, actualOutput);

        //Test case 3
        date = new Date("3/30/2023");
        expectedOutput = true;
        actualOutput = date.isValid();
        System.out.println("**Test case #3: a valid date in March in a future year: ");
        testResult(date, expectedOutput, actualOutput);

        //Test case 4
        date = new Date("12/2/2022");
        expectedOutput = true;
        actualOutput = date.isValid();
        System.out.println("**Test case #4: a valid date in December, in the future: ");
        testResult(date, expectedOutput, actualOutput);

        //Test case 5
        date = new Date("1/20/2004");
        expectedOutput = true;
        actualOutput = date.isValid();
        System.out.println("**Test case #5: a valid date in January: ");
        testResult(date, expectedOutput, actualOutput);

        //Test case 6
        date = new Date("12/20/2004");
        expectedOutput = true;
        actualOutput = date.isValid();
        System.out.println("**Test case #6: a valid date in December: ");
        testResult(date, expectedOutput, actualOutput);

        //Test case 7
        date = new Date("4/31/2003");
        expectedOutput = false;
        actualOutput = date.isValid();
        System.out.println("**Test case #7: a date with an invalid day value for April: ");
        testResult(date, expectedOutput, actualOutput);

        //Test case 8
        date = new Date("13/31/2003");
        expectedOutput = false;
        actualOutput = date.isValid();
        System.out.println("**Test case #8: a date with an invalid month value: ");
        testResult(date, expectedOutput, actualOutput);

        //Test case 9
        date = new Date("5/3/2/2003");
        expectedOutput = false;
        actualOutput = date.isValid();
        System.out.println("**Test case #9: a date with the an incorrect parameter input: ");
        testResult(date, expectedOutput, actualOutput);

        //Test case 10
        date = new Date("3/32/2003");
        expectedOutput = false;
        actualOutput = date.isValid();
        System.out.println("**Test case #10: a date with an invalid day value: ");
        testResult(date, expectedOutput, actualOutput);

        //Test case 11
        date = new Date("-1/31/2003");
        expectedOutput = false;
        actualOutput = date.isValid();
        System.out.println("**Test case #11: a date with an invalid month value: ");
        testResult(date, expectedOutput, actualOutput);

        //Test case 12
        date = new Date("4/3/2003");
        expectedOutput = true;
        actualOutput = date.isValid();
        System.out.println("**Test case #12: a valid date in the past: ");
        testResult(date, expectedOutput, actualOutput);

        //Test case 13
        date = new Date("4/31/2022");
        expectedOutput = false;
        actualOutput = date.isValid();
        System.out.println("**Test case #13: a date with an invalid day value: ");
        testResult(date, expectedOutput, actualOutput);

        //Test case 14
        date = new Date("2/30/2011");
        expectedOutput = false;
        actualOutput = date.isValid();
        System.out.println("**Test case #14: a date in a non-leap year should only have 28 days in February: ");
        testResult(date, expectedOutput, actualOutput);

        //Test case 15
        date = new Date("1/20/2003");
        expectedOutput = true;
        actualOutput = date.isValid();
        System.out.println("**Test case #15: a valid date in January: ");
        testResult(date, expectedOutput, actualOutput);

        //Test case 16
        date = new Date("3/30/2021");
        expectedOutput = true;
        actualOutput = date.isValid();
        System.out.println("**Test case #16: a valid date in March: ");
        testResult(date, expectedOutput, actualOutput);

        //Test case 17
        date = new Date("5/1/1996");
        expectedOutput = true;
        actualOutput = date.isValid();
        System.out.println("**Test case #17: a valid date in a year <2000: ");
        testResult(date, expectedOutput, actualOutput);

        //Test case 18
        date = new Date("3/31/1990");
        expectedOutput = true;
        actualOutput = date.isValid();
        System.out.println("**Test case #18: a valid date in March: ");
        testResult(date, expectedOutput, actualOutput);

        //Test case 19
        date = new Date("6/30/2023");
        expectedOutput = true;
        actualOutput = date.isValid();
        System.out.println("**Test case #19: a valid date in June in the future: ");
        testResult(date, expectedOutput, actualOutput);

        //Test case 20
        date = new Date("12/1/1989");
        expectedOutput = true;
        actualOutput = date.isValid();
        System.out.println("**Test case #20: a valid date in December in a year < 2000: ");
        testResult(date, expectedOutput, actualOutput);

        //Test case 21
        date = new Date("5/31/2023");
        expectedOutput = true;
        actualOutput = date.isValid();
        System.out.println("**Test case #21: a valid date in May in the future: ");
        testResult(date, expectedOutput, actualOutput);

        //Test case 22
        date = new Date("2/29/2000");
        expectedOutput = true;
        actualOutput = date.isValid();
        System.out.println("**Test case #22: a date in a leap year has 29 days in February: ");
        testResult(date, expectedOutput, actualOutput);

        //Test case 23
        date = new Date("9/30/2023");
        expectedOutput = true;
        actualOutput = date.isValid();
        System.out.println("**Test case #23: a valid date in September: ");
        testResult(date, expectedOutput, actualOutput);

        //Test case 24
        date = new Date("8/8/1977");
        expectedOutput = true;
        actualOutput = date.isValid();
        System.out.println("**Test case #24: a valid date with the same day and month values: ");
        testResult(date, expectedOutput, actualOutput);

        //Test case 25
        date = new Date("9/30/2020");
        expectedOutput = true;
        actualOutput = date.isValid();
        System.out.println("**Test case #25: a valid date in September: ");
        testResult(date, expectedOutput, actualOutput);

        //Test case 26
        date = new Date("9/9/1977");
        expectedOutput = true;
        actualOutput = date.isValid();
        System.out.println("**Test case #26: a valid date with the same day and month values: ");
        testResult(date, expectedOutput, actualOutput);

        //Test case 27
        date = new Date("7/15/1977");
        expectedOutput = true;
        actualOutput = date.isValid();
        System.out.println("**Test case #27: a valid date in July: ");
        testResult(date, expectedOutput, actualOutput);

        //Test case 28
        date = new Date("12/31/2023");
        expectedOutput = true;
        actualOutput = date.isValid();
        System.out.println("**Test case #28: December has 31 days: ");
        testResult(date, expectedOutput, actualOutput);

        //Test case 29
        date = new Date("10/7/1991");
        expectedOutput = true;
        actualOutput = date.isValid();
        System.out.println("**Test case #29: a valid date in October: ");
        testResult(date, expectedOutput, actualOutput);

        //Test case 30
        date = new Date("3/31/2023");
        expectedOutput = true;
        actualOutput = date.isValid();
        System.out.println("**Test case #30: March has 31 days: ");
        testResult(date, expectedOutput, actualOutput);

        //Test case 31
        date = new Date("5/1/1999");
        expectedOutput = true;
        actualOutput = date.isValid();
        System.out.println("**Test case #31: the day value can be at least 1: ");
        testResult(date, expectedOutput, actualOutput);

        //Test case 32
        date = new Date("1/31/2023");
        expectedOutput = true;
        actualOutput = date.isValid();
        System.out.println("**Test case #32: January has 31 days: ");
        testResult(date, expectedOutput, actualOutput);

        //Test case 33
        date = new Date("11/21/800");
        expectedOutput = false;
        actualOutput = date.isValid();
        System.out.println("**Test case #33: year values should be >1900: ");
        testResult(date, expectedOutput, actualOutput);
    }

    private static void testResult(Date date, boolean expectedOutput, boolean actualOutput){
        System.out.println(date.toString());
        System.out.println("isValid() returns " + actualOutput + ",");
        if (actualOutput == expectedOutput)
            System.out.println("PASS.\n");
        else
            System.out.println(", FAIL.\n");
    }
}

