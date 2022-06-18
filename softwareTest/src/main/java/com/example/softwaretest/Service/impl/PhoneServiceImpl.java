package com.example.softwaretest.Service.impl;

import com.example.softwaretest.Entity.Phone;
import com.example.softwaretest.Mapper.PhoneMapper;
import com.example.softwaretest.Service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneServiceImpl implements PhoneService {
    @Autowired
    private PhoneMapper phoneMapper;


    @Override
    public List<Phone> getTestCases() {
        return phoneMapper.getTestCases();
    }

    @Override
    public void cleanTestCases() {
        phoneMapper.cleanTestCases();
    }

    @Override
    public void saveTestCases(Phone phone) {
        phoneMapper.saveTestCases(phone);
    }

    @Override
    public double testCalendar(Phone phone) {
        return 0;
    }
}
