package com.example.softwaretest.Mapper;

import com.example.softwaretest.Entity.Triangle;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TriangleMapper {
    /**
     *
     *@author cj
     *@date 2022/6/9 0:14
     * @return Triangle
     */
    List<Triangle> getTestCases();

    void cleanTestCases();

    void saveTestCases(Triangle triangle);

    double queryPass();

    double total();
}
