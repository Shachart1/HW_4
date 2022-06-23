public class DateCalculator {
    int[] ends_30 = {january,march,may,july,august,october,december};
    int[] ends_31 = {april,june,septemnber,};
    public static Date addToDate(Date date, int num) {
        if (num==0){
            return date;
        }
        if(num > 0){
            return addToDate(progress(date,1),num-1);
        }
        return addToDate(progress(date,-1),num+1);
    }


    private Date progressDay(Date date,int sign){
        int year = date.getYear();
        Months month = Months.valueOf((date.getMonth() - 1).toString());
        int day = date.getDay()+sign;
        if(day <= 27 && day >=1){
            return new Date(day,month.ordinal()+1,year);
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
    private Date progressYear(int day, Months month, int year){
        if(month.compareTo(Months.DECEMBER) > 0){ // need to add a Year
            day = 1; // 1st in January
            month = Months.JANUARY;
            year ++;
        }
        else { // need to decrease year
            day = 31; // 31st in December
            month = Months.DECEMBER;
            year --;
        }
        return new Date(day, month.ordinal() + 1, year); // adding 1 to month since we decreased it before to use enum
    }

    /**
     * checking if a year is a leap year
     * @param year - the current year
     * @return true - year is leap year, false - leap is regular year
     */
    private boolean isLeapYear(int year){
        if((year % 400 == 0) || (year % 4 == 0 && year % 100 != 0)){
            return true;
        }
        return false;
    }

    private Date progressMonth(int day,int month, int year){

    }
}
