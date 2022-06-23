public class DateCalculator {
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

    public static Date addToDate(Date date, int num) {
        if (num==0){
            return date;
        }
        if(num > 0){
            return addToDate(progressDay(date,1),num-1);
        }
        return addToDate(progressDay(date,-1),num+1);
    }

    /**
     * will update the day as needed and return the new date
     * @param date - date to update
     * @param sign - 1 if we want to add a day and -1 if we want to decrease a day
     * @return - updated date
     */
    private static Date progressDay(Date date,int sign){
        int year = date.getYear();
        int month = date.getMonth();
        int day = date.getDay()+sign;
        if(day <= 27 && day >=1){
            return new Date(day,month ,year);
        }
        else{
            return progressMonth(day,month,year);
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
        else { // need to decrease year
            day = 31; // 31st in December
            month = DECEMBER;
            year --;
        }
        return new Date(day, month + 1, year); // adding 1 to month since we decreased it before to use enum
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

    private static int numDays(int month) {
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
                return 28; // regular year
            default:
                return -1; // ERROR
        }
    }

    /**
     * checking if day is legal in this month
     * @param day
     * @param month
     * @param year
     * @return updated date
     */
    private static Date progressMonth(int day,int month, int year){
        int daysInMonth = numDays(month);
        if (day<1){
            return progressYear(numDays(month-1),month -1,year);
        }
        if (day <daysInMonth){
            return new Date(day,month,year);
        }
        else{
            return progressYear(1,month +1,year);
        }
  }
}
