package com.example.softwaretest.Service;

import com.example.softwaretest.Entity.Calendar;

import java.util.List;

public interface CalendarService {
    /**
     *
     *@author cj
     *@date 2022/6/9 7:47
     获取万年历所有测试用例
     * @return List<Calendar>
     */
    List<Calendar> getTestCases ();


    void saveTestCases(Calendar calendar);

    void cleanTestCases();

    String testCalendar(Calendar calendar);

    double queryPass();

    double total();
}
