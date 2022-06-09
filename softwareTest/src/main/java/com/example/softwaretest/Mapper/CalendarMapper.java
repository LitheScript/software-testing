package com.example.softwaretest.Mapper;

import com.example.softwaretest.Entity.Calendar;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface CalendarMapper {


    List<Calendar> getTestCases();
}
