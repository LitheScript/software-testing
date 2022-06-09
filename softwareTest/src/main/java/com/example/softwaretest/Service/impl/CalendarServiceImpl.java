package com.example.softwaretest.Service.impl;

import com.example.softwaretest.Entity.Calendar;
import com.example.softwaretest.Mapper.CalendarMapper;
import com.example.softwaretest.Service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalendarServiceImpl implements CalendarService {
    @Autowired
    private CalendarMapper calendarMapper;


    @Override
    public List<Calendar> getTestCases(){
        return  calendarMapper.getTestCases();
    }



}
