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
        int month = date.getMonth() - 1;
        int day = date.getDay()+sign;
        if(day <= 27 && day >=1){
            return new Date(day,month+1,year);
        }
        else{
            return progressMonth(day,month,year);
        }
    }

    private Date progressYear(int day, int month, int year){
        if(month > Months.DECEMBER.ordinal()){ // need to add a Year
            day = 1; // 1st in January
            month = Months.JANUARY.ordinal();
            year ++;
        }
        else { // need to decrease year
            day = 31; // 31st in December
            month = Months.DECEMBER.ordinal();
            year --;
        }
        return new Date(day, month + 1, year); // adding 1 to month since we decreased it before to use enum
    }
    private boolean isSpecialYear(int year){
        if((year % 400 == 0) || (year % 4 == 0 && year % 100 != 0)){
            return true;
        }
        return false;
    }

    private Date progressMonth(int day,int month, int year){

    }
}
