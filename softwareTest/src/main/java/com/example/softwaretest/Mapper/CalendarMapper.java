package com.example.softwaretest.Mapper;

import com.example.softwaretest.Entity.Calendar;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface CalendarMapper {

    /**
     *
     *@author cj
     *@date 2022/6/9 13:36
     获取日历的所有测试用例
     * @return List<Calendar> 
     */
    List<Calendar> getTestCases();

    void saveTestCases(Calendar calendar);

    void cleanTestCases();

    double queryPass();

    double total();
}
