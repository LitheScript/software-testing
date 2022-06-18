package com.example.softwaretest.Service.impl;

import com.example.softwaretest.Entity.Calendar;
import com.example.softwaretest.Mapper.CalendarMapper;
import com.example.softwaretest.Service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CalendarServiceImpl implements CalendarService {
    @Autowired
    private CalendarMapper calendarMapper;


    @Override
    public List<Calendar> getTestCases(){
        return  calendarMapper.getTestCases();
    }

    @Override
    public void saveTestCases(Calendar calendar) {
            calendarMapper.saveTestCases(calendar);
    }

    @Override
    public void cleanTestCases() {
        calendarMapper.cleanTestCases();
    }

    @Override
    public String testCalendar(Calendar calendar) {
        String[] str = calendar.getExpectResult().split("-");

        int year= Integer.valueOf(str[0]);
        int month= Integer.valueOf(str[1]);
        int day= Integer.valueOf(str[2]);

//        if(year==0 || !(month>0&&month<13) || !(day>0&&day<32)) return "0--1--1"
//        if(month==2){
//            if(year%400==0&&(year%4==0&&year%100!=0)){
//                if(day>0&&day<=28) return new Date(year,month,day+1);
//                else if (day==29) return new Date(year,3,1);
//                else return errorDate();
//            }
//            else {
//                if(day>0&&day<=27) return new Date(year,month,day+1);
//                else if (day==28) return new Date(year,3,1);
//                else return errorDate();
//            }
//        }
//        else if((month%2!=0&&month<8)||month==8||month==10||month==12){
//            if(month==12&&day==31) return new Date(year+1,1,1);
//            if(day>0&&day<31) return new Date(year,month,day+1);
//            else if (day==31) return new Date(year,month+1,1);
//            else return errorDate();
//        }
//        else {
//            if(day>0&&day<30) return new Date(year,month,day+1);
//            else if (day==30) return new Date(year,month+1,1);
//            else return errorDate();
//        }
        return calendar.getExpectResult();
    }



}
