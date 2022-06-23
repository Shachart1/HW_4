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
        int month = date.getMonth();
        int day = date.getDay()+sign;
        if(day <= 27 && day >=1){
            return new Date(day,month,year);
        }
        else{
            return progressMonth(day,month,year);
        }

    }
    private Date progressMonth(int day,int month, int year){

    }
}
