package com.example.softwaretest.Service;


import com.example.softwaretest.Entity.Commission;

import java.util.List;

public interface CommissionService {
    /**
     *
     *@author cj
     *@date 2022/6/9 7:48
     获取佣金所有测试用例
     * @return List<Commission> 
     */
    List<Commission> getTestCases ();

    void cleanTestCases();

    void saveTestCases(Commission commission);

    double testCommission(Commission commission);

    double queryPass();

    double total();
}
