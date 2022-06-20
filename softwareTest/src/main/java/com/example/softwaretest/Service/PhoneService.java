package com.example.softwaretest.Service;

import com.example.softwaretest.Entity.Phone;

import java.util.List;

public interface PhoneService {
    /**
     *
     *@author cj
     *@date 2022/6/18 23:15
     返回所有测试用例
     * @return List<Phone>
     */
    List<Phone> getTestCases ();

    void cleanTestCases();

    void saveTestCases(Phone phone);

    double testPhone(Phone phone);

    double queryPass();

    double total();
}
