package softwareTest.com.nextDate;


/**
 * @author Lori
 * @date 2022/4/27 23:24
 * @description:
 */
public class nextDateCalculatorImpl implements nextDateCalculator {
    public Date calculate(Date givenDate) {
        int year= givenDate.getY();
        int month= givenDate.getM();
        int day= givenDate.getD();

        if(!(month>0&&month<13) || !(day>0&&day<32)) return errorDate();
        if(month==2){
            if(year%400==0&&(year%4==0&&year%100!=0)){
                if(day>0&&day<=28) return new Date(year,month,day+1);
                else if (day==29) return new Date(year,3,1);
                else return errorDate();
            }
            else {
                if(day>0&&day<=27) return new Date(year,month,day+1);
                else if (day==28) return new Date(year,3,1);
                else return errorDate();
            }
        }
        else if((month%2!=0&&month<8)||month==8||month==10||month==12){
            if(month==12&&day==31) return new Date(year+1,1,1);
            if(day>0&&day<31) return new Date(year,month,day+1);
            else if (day==31) return new Date(year,month+1,1);
            else return errorDate();
        }
        else {
            if(day>0&&day<30) return new Date(year,month,day+1);
            else if (day==30) return new Date(year,month+1,1);
            else return errorDate();
        }
    }

    public Date errorDate(){
        return new Date(-1,-1,-1);
    }
}
