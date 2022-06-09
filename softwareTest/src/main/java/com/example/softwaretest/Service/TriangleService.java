package com.example.softwaretest.Service;

import com.example.softwaretest.Entity.Triangle;

import java.util.List;

public interface TriangleService {

    /**
     *
     *@author cj
     *@date 2022/6/9 7:47
     获取三角形所有测试用例
     * @return List<Triangle> 
     */
    List<Triangle> getTestCases ();
}
