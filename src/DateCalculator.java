public class DateCalculator {

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
        int month = date.getMonth() - 1;
        int day = date.getDay()+sign;
        if(day <= 27 && day >=1){
            return new Date(day,month +1,year);
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
        if(month > Months.DECEMBER.ordinal()){ // need to add a Year
            day = 1; // 1st in January
            month = Months.JANUARY.ordinal();
            year ++;
        }
        if() { // need to decrease year
            day = 31; // 31st in December
            month = Months.DECEMBER.ordinal();
            year --;
        }
        return new Date(day, month + 1, year); // adding 1 to month since we decreased it before to use enum
    }


    private static numDays(int month){

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

    private static Date progressMonth(int day,int month, int year){
  switch (month){
      case (month)
  }
    }
}
