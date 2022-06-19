package com.example.softwaretest.Mapper;

import com.example.softwaretest.Entity.Commission;
import com.example.softwaretest.Entity.Phone;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 *@author cj
 *@date 2022/6/18 23:14
 * @param
 */
@Mapper
@Repository
public interface PhoneMapper {

    List<Phone> getTestCases();

    void cleanTestCases();

    void saveTestCases(Phone phone);

    double queryPass();

    double total();
}
