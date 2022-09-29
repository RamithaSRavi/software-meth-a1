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

    /**
     * Default constructor which creates a date object with the current date.
     */
    public Date() {
        Calendar calendar = Calendar.getInstance();
        this.year = calendar.get(Calendar.YEAR);
        this.month = calendar.get(Calendar.MONTH);
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
                    return 0;
                }
                else {
                    if (this.day < date.getDay()){
                        return -1;
                    }
                    else {
                        return 1;
                    }
                }
            }
            else {
                if (this.month < date.getMonth()){
                    return -1;
                }
                else {
                    return 1;
                }
            }
        }
        else {
            if (this.year < date.getYear()){
                return -1;
            }
            else {
                return 1;
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
        System.out.println("**Test case #2: a date that has a day > 28 in a" +
                " February in a non-leap year ");
        testResult(date, expectedOutput, actualOutput);

        //Test case 2
        date = new Date("9/2/2022");
        expectedOutput = true;
        actualOutput = date.isValid();
        System.out.println("**Test case #3: a valid date with a single digit month " +
                "and single digit day");
        testResult(date, expectedOutput, actualOutput);

        //Test case 3
        date = new Date("3/30/2023");
        expectedOutput = true;
        actualOutput = date.isValid();
        System.out.println("**Test case #4: a valid date with a month that has 31 days, " +
                "with date of 30 and a year in the future");
        testResult(date, expectedOutput, actualOutput);

        //Test case 4
        date = new Date("12/2/2022");
        expectedOutput = true;
        actualOutput = date.isValid();
        System.out.println("**Test case #5: a valid date with a double digit month " +
                "and a single digit day, with a valid year.");
        testResult(date, expectedOutput, actualOutput);

        //Test case 5
        date = new Date("1/20/2004");
        expectedOutput = true;
        actualOutput = date.isValid();
        System.out.println("**Test case #6: a valid date with a single digit month " +
                "and a double digit day, with a valid year.");
        testResult(date, expectedOutput, actualOutput);

        //Test case 6
        date = new Date("12/20/2004");
        expectedOutput = true;
        actualOutput = date.isValid();
        System.out.println("**Test case #7: a valid date with a double digit month " +
                "and a double digit day, with a valid year.");
        testResult(date, expectedOutput, actualOutput);

        //Test case 7
        date = new Date("4/31/2003");
        expectedOutput = false;
        actualOutput = date.isValid();
        System.out.println("**Test case #8: an invalid date because the month only has " +
                "30 days but it has a date of 31, valid year");
        testResult(date, expectedOutput, actualOutput);

        //Test case 8
        date = new Date("13/31/2003");
        expectedOutput = false;
        actualOutput = date.isValid();
        System.out.println("**Test case #9: a valid date with a double digit month " +
                "and a double digit day, with a valid year.");
        testResult(date, expectedOutput, actualOutput);

        //Test case 9
        date = new Date("13/31/2003");
        expectedOutput = false;
        actualOutput = date.isValid();
        System.out.println("**Test case #9: a valid date with a double digit month " +
                "and a double digit day, with a valid year.");
        testResult(date, expectedOutput, actualOutput);

        //Test case 10
        date = new Date("3/32/2003");
        expectedOutput = false;
        actualOutput = date.isValid();
        testResult(date, expectedOutput, actualOutput);

        //Test case 11
        date = new Date("-1/31/2003");
        expectedOutput = false;
        actualOutput = date.isValid();
        testResult(date, expectedOutput, actualOutput);

        //Test case 12
        date = new Date("4/3/2003");
        expectedOutput = true;
        actualOutput = date.isValid();
        testResult(date, expectedOutput, actualOutput);

        //Test case 13
        date = new Date("4/31/2022");
        expectedOutput = false;
        actualOutput = date.isValid();
        testResult(date, expectedOutput, actualOutput);

        //Test case 14
        date = new Date("2/30/2011");
        expectedOutput = false;
        actualOutput = date.isValid();
        testResult(date, expectedOutput, actualOutput);

        //Test case 15
        date = new Date("1/20/2003");
        expectedOutput = true;
        actualOutput = date.isValid();
        testResult(date, expectedOutput, actualOutput);

        //Test case 16
        date = new Date("3/30/2021");
        expectedOutput = true;
        actualOutput = date.isValid();
        testResult(date, expectedOutput, actualOutput);

        //Test case 17
        date = new Date("3/30/2023");
        expectedOutput = true;
        actualOutput = date.isValid();
        testResult(date, expectedOutput, actualOutput);

        //Test case 18
        date = new Date("5/1/1996");
        expectedOutput = true;
        actualOutput = date.isValid();
        testResult(date, expectedOutput, actualOutput);

        //Test case 19
        date = new Date("3/31/1990");
        expectedOutput = true;
        actualOutput = date.isValid();
        testResult(date, expectedOutput, actualOutput);

        //Test case 20
        date = new Date("6/30/2023");
        expectedOutput = true;
        actualOutput = date.isValid();
        testResult(date, expectedOutput, actualOutput);

        //Test case 21
        date = new Date("12/1/1989");
        expectedOutput = true;
        actualOutput = date.isValid();
        testResult(date, expectedOutput, actualOutput);

        //Test case 22
        date = new Date("5/31/2023");
        expectedOutput = true;
        actualOutput = date.isValid();
        testResult(date, expectedOutput, actualOutput);

        //Test case 23
        date = new Date("2/29/2000");
        expectedOutput = true;
        actualOutput = date.isValid();
        testResult(date, expectedOutput, actualOutput);

        //Test case 24
        date = new Date("9/30/2023");
        expectedOutput = true;
        actualOutput = date.isValid();
        testResult(date, expectedOutput, actualOutput);

        //Test case 25
        date = new Date("8/8/1977");
        expectedOutput = true;
        actualOutput = date.isValid();
        testResult(date, expectedOutput, actualOutput);

        //Test case 26
        date = new Date("9/30/2020");
        expectedOutput = true;
        actualOutput = date.isValid();
        testResult(date, expectedOutput, actualOutput);

        //Test case 27
        date = new Date("9/9/1977");
        expectedOutput = true;
        actualOutput = date.isValid();
        testResult(date, expectedOutput, actualOutput);

        //Test case 28
        date = new Date("7/15/1977");
        expectedOutput = true;
        actualOutput = date.isValid();
        testResult(date, expectedOutput, actualOutput);

        //Test case 29
        date = new Date("12/31/2023");
        expectedOutput = true;
        actualOutput = date.isValid();
        testResult(date, expectedOutput, actualOutput);

        //Test case 30
        date = new Date("10/7/1991");
        expectedOutput = true;
        actualOutput = date.isValid();
        testResult(date, expectedOutput, actualOutput);

        //Test case 31
        date = new Date("3/31/2023");
        expectedOutput = true;
        actualOutput = date.isValid();
        testResult(date, expectedOutput, actualOutput);

        //Test case 32
        date = new Date("5/1/1999");
        expectedOutput = true;
        actualOutput = date.isValid();
        testResult(date, expectedOutput, actualOutput);

        //Test case 33
        date = new Date("1/31/2023");
        expectedOutput = true;
        actualOutput = date.isValid();
        testResult(date, expectedOutput, actualOutput);

        //Test case 34
        date = new Date("11/21/800");
        expectedOutput = false;
        actualOutput = date.isValid();
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

