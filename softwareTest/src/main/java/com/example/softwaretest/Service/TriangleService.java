package com.example.softwaretest.Service;

import com.example.softwaretest.Entity.Triangle;

import java.util.List;

public interface TriangleService {

    /**
     * @return List<Triangle>
     * @author cj
     * @date 2022/6/9 7:47
     * 获取三角形所有测试用例
     */
    List<Triangle> getTestCases();


    /**
     * @return List<Integer>
     * @author cj
     * 测试测试用例
     * @date 2022/6/9 8:21
     */
    List<Integer> testTriangle(List<Triangle> testCases);

    void cleanTestCases();

    void saveTestCases(Triangle triangle);

    String testTriangle(Triangle triangle);

    double queryPass();

    double total();
}


