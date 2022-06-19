package com.example.softwaretest.Mapper;


import com.example.softwaretest.Entity.Commission;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommissionMapper {
    /**
     *
     *@author cj
     *@date 2022/6/9 13:36
     获取佣金所有测试用例
     * @return List<Commission> 
     */
    List<Commission> getTestCases();

    void cleanTestCases();

    void saveTestCases(Commission commission);

    double queryPass();

    double total();
}
