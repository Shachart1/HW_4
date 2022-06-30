/**
 * this class represents a date calculator.
 */
public class DateCalculator{
    final static int JANUARY = 1;
    final static int FEBRUARY = 2;
    final static int MARCH = 3;
    final static int APRIL = 4;
    final static int MAY = 5;
    final static int JUNE = 6;
    final static int JULY = 7;
    final static int AUGUST = 8;
    final static int SEPTEMBER = 9;
    final static int OCTOBER = 10;
    final static int NOVEMBER = 11;
    final static int DECEMBER = 12;

    /**
     * progress the date by num days
     * @param date - given date to update
     * @param num - number of days to add to date
     * @return - updated date
     */
    public static Date addToDate(Date date, int num) {
        int day = date.getDay();
        int month = date.getMonth();
        int year = date.getYear();
        // recursive base
        if (num==0){
            return date;
        }

        // making sure there is no stack overflow from recursive process
        if (num > 365){ // need to add a year
            // date < 1.3 - progress with 29.2
            if(isLeapYear(year) && month<MARCH){
                if(day == 29 && month == FEBRUARY){ // get ready to progress a year to 1.3
                    day = 1;
                    month = MARCH;
                }
                    return addToDate(new Date(day, month, year + 1), num - 366);
            }
            // date >= 1.3 before leap year - progress with 29.2
            if(isLeapYear(year + 1) && month>FEBRUARY){
                return addToDate(new Date(day, month, year + 1), num - 366);
            }
            // no 29.2 - progress regularly
            else{
                return addToDate(new Date(day, month, year + 1), num - 365);
            }
        }
        if (num < -365) { // need to take one year back
            // date >= 1.3 in a leap year - progress with 29.2
            if(isLeapYear(year) && month>FEBRUARY){
                return addToDate(new Date(day, month, year - 1), num + 366);
            }
            // date < 1.3 after leap year - progress with 29.2
            if(isLeapYear(year - 1) && month<MARCH){

                return addToDate(new Date(day, month, year - 1), num + 366);
            }
            // no 29.2 - progress regularly
            else{
                // date == 29.9. go back 365 days to 28.2 instead of 29.2
                if(day == 29 && month == FEBRUARY){ //getting ready to go back 366 days to 28.2
                    day = 28;
                }
                return addToDate(new Date(day, month, year - 1), num + 365);
            }
        }

        // recursive step
        if(num > 0){
            return addToDate(progressDay(day,month,year,1),num-1);
        }
        return addToDate(progressDay(day,month,year,-1),num+1);
    }

    /**
     * will update the day as needed and return the new date
     * @param day - day of the original date
     * @param month - month of the original date
     * @param year - year of the original date
     * @param sign - 1 if we want to add a day and -1 if we want to decrease a day
     * @return - updated date
     */
    private static Date progressDay(int day, int month, int year,int sign){
        day += sign;
        if(day <= 27 && day >=1){
            return new Date(day,month ,year);
        }
        else{
            return progressMonth(day,month,year);
        }
    }

    /**
     * checking if day is legal in this month
     * @param day - given day
     * @param month - given month
     * @param year - given year
     * @return updated date
     */
    private static Date progressMonth(int day,int month, int year){
        int daysInMonth = numDays(month,year);
        if (day < 1){
            return progressYear(numDays(month-1,year),month -1,year);
        }
        if (day <= daysInMonth){
            return new Date(day,month,year);
        }
        else{
            return progressYear(1,month +1,year);
        }
    }

    /**
     * update year by either adding a year or decreasing one based on month.
     * set the date to be the first day of the year if added a year.
     * set the date to be the last day of the year if decreased a year.
     * @param day - progressed day
     * @param month - progressed month
     * @param year - current year
     * @return the progressed date
     */
    private static Date progressYear(int day, int month, int year){
        if(month > DECEMBER){ // need to add a Year
            day = 1; // 1st in January
            month = JANUARY;
            year ++;
        }
        if(month < JANUARY) { // need to decrease year
            day = 31; // 31st in December
            month = DECEMBER;
            year --;
        }
        return new Date(day, month, year); // adding 1 to month since we decreased it before to use enum
    }

    /**
     * checking if a year is a leap year
     * @param year - the current year
     * @return true - year is leap year, false - leap is regular year
     */
    private static boolean isLeapYear(int year){
        if((year % 400 == 0) || (year % 4 == 0 && year % 100 != 0)){
            return true;
        }
        return false;
    }

    /**
     * checks number of days in a month, taking into account leap years.
     * @param month - given month
     * @param year - given year
     * @return - the number of days in the given month in the given year
     */
    private static int numDays(int month, int year) {
        switch (month){
            case JANUARY:
            case MARCH:
            case MAY:
            case JULY:
            case AUGUST:
            case OCTOBER:
            case DECEMBER:
                return 31; //31 days in the month

            case APRIL:
            case JUNE:
            case SEPTEMBER:
            case NOVEMBER:
                return 30; // 30 days in the month

            case FEBRUARY:
                if(isLeapYear(year)) return 29; // Leap year
                return 28; // Regular year
            default:
                return -1; // ERROR
        }
    }
}
//