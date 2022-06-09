package com.example.softwaretest.Controller;


import com.example.softwaretest.Entity.Calendar;
import com.example.softwaretest.Entity.Commission;
import com.example.softwaretest.Entity.Triangle;
import com.example.softwaretest.Mapper.CalendarMapper;
import com.example.softwaretest.Mapper.CommissionMapper;
import com.example.softwaretest.Mapper.TriangleMapper;
import com.example.softwaretest.Service.TriangleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


@RestController
public class TestController {

    @Autowired
    private TriangleMapper triangleMapper;

    @Autowired
    private CommissionMapper commissionMapper;

    @Autowired
    private CalendarMapper calendarMapper;

    @GetMapping("/queryTriangle")
    public List<Triangle> queryTriangle(){
        return triangleMapper.getTestCases();
    }

    @GetMapping("/queryCommission")
    public List<Commission> queryCommission(){
        return commissionMapper.getTestCases();
    }

    @GetMapping("/queryCalendar")
    public List<Calendar> queryCalendar(){
        return calendarMapper.getTestCases();
    }


}
