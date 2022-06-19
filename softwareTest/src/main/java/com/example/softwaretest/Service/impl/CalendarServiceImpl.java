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
    public List<Calendar> getTestCases() {
        return calendarMapper.getTestCases();
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

        int year = calendar.getYear();
        int month = calendar.getMonth();
        int day = calendar.getDay();

        if (!(year > 1899 && year < 2051)) return errorYear();
        if (!(month > 0 && month < 13)) return errorMonth();
        if (!(day > 0 && day < 32)) return errorDay();
        if (month == 2) {
            if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
                if (day > 0 && day <= 28) return plusDay(year, month, day);
                else if (day == 29) return plusMonth(year, month, day);
                else return errorDay();
            } else {
                if (day > 0 && day <= 27) return plusDay(year, month, day);
                else if (day == 28) return plusMonth(year, month, day);
                else return errorDay();
            }
        } else if ((month % 2 != 0 && month < 8) || month == 8 || month == 10 || month == 12) {
            if (month == 12 && day == 31) return plusYear(year, month, day);
            if (day > 0 && day < 31) return plusDay(year, month, day);
            else if (day == 31) return plusMonth(year, month, day);
            else return errorDay();
        } else {
            if (day > 0 && day < 30) return plusDay(year, month, day);
            else if (day == 30) return plusMonth(year, month, day);
            else return errorDay();
        }
    }

    @Override
    public double queryPass() {
        return calendarMapper.queryPass();
    }

    @Override
    public double total() {
        return calendarMapper.total();
    }


    public String errorYear() {
        return "年的值不在指定范围内";
    }

    public String errorMonth() {
        return "月的值不在指定范围内";
    }

    public String errorDay() {
        return "日的值不在指定范围内";
    }

    public String plusDay(int year, int month, int day) {
        day++;
        return format(year, month, day);
    }

    public String plusMonth(int year, int month, int day) {
        month++;
        return format(year, month, 1);
    }

    public String plusYear(int year, int month, int day) {
        year++;
        return format(year, 1, 1);
    }

    String format(int year, int month, int day) {
        StringBuffer str = new StringBuffer();
        str.append(year);
        str.append(" ");
        if (month < 10) {
            str.append(0);
            str.append(month);
        } else {
            str.append(month);
        }
        str.append(" ");
        if (day < 10) {
            str.append(0);
            str.append(day);
        } else {
            str.append(day);
        }
        return str.toString();
    }


}
