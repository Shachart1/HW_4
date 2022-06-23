public class DateCalculator {
    public static Date addToDate(Date date, int num) {
        if (num==0){
            return date;
        }
        if(num > 0){
            return addToDate(progress(date,1),num-1);
        }
        return addToDate(progress(date,-1),num+1);
    }


    private Date progress(Date date,int sign){
        int day = date.getDay();

    }

    private int
}
