package com.example.softwaretest.Service.impl;

import com.example.softwaretest.Entity.Commission;
import com.example.softwaretest.Mapper.CommissionMapper;
import com.example.softwaretest.Service.CommissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommissionServiceImpl implements CommissionService {
    @Autowired
    private CommissionMapper commissionMapper;

    @Override
    public List<Commission> getTestCases(){
        return commissionMapper.getTestCases();
    }
}
