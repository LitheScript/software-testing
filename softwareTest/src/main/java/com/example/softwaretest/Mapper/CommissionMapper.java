package com.example.softwaretest.Mapper;


import com.example.softwaretest.Entity.Commission;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommissionMapper {
    List<Commission> getTestCases();
}
