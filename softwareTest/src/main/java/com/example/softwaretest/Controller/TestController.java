package com.example.softwaretest.Controller;


import com.example.softwaretest.Entity.Calendar;
import com.example.softwaretest.Entity.Commission;
import com.example.softwaretest.Entity.Triangle;
import com.example.softwaretest.Mapper.CalendarMapper;
import com.example.softwaretest.Mapper.CommissionMapper;
import com.example.softwaretest.Mapper.TriangleMapper;
import com.example.softwaretest.Result.Response;
import com.example.softwaretest.Service.CalendarService;
import com.example.softwaretest.Service.CommissionService;
import com.example.softwaretest.Service.TriangleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@RestController
public class TestController {

    @Autowired
    private TriangleService triangleService;

    @Autowired
    private CommissionService commissionService;

    @Autowired
    private CalendarService calendarService;

    @GetMapping("/queryTriangle")
    public List<Triangle> queryTriangle(){
        return triangleService.getTestCases();
    }

    @GetMapping("/queryCommission")
    public List<Commission> queryCommission(){
        return commissionService.getTestCases();
    }

    @GetMapping("/queryCalendar")
    public List<Calendar> queryCalendar(){
        return calendarService.getTestCases();
    }

    @PostMapping("/testTriangle")
    public Response testTriangle(List<Triangle> testCases){

        List<Integer> results;
        results = triangleService.testTriangle(testCases);
        return Response.success().data((Map<String, Object>) results);
    }


}
