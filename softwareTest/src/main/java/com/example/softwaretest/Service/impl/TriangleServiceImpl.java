package com.example.softwaretest.Service.impl;

import com.example.softwaretest.Entity.Triangle;
import com.example.softwaretest.Mapper.TriangleMapper;
import com.example.softwaretest.Service.TriangleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TriangleServiceImpl implements TriangleService {

    @Autowired
    private TriangleMapper triangleMapper;

    @Override
    public List<Triangle> getTestCases(){return triangleMapper.getTestCases();}
}
