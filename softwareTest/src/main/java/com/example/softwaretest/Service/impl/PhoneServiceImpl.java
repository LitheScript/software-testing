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
    public double testPhone(Phone phone) {
        int callLength = phone.getLength();
        int times = phone.getTimes();

        if (callLength < 0 || callLength > 44460) {
            return -1;
        }
        if (callLength>=0 && callLength <= 60) {
            if (times <= 1) {
                return 0.01;
            }
            return 0;
        } else if (60 < callLength && callLength <= 120) {
            if (times <= 2) {
                return 0.015;
            }
            return 0;

        } else if (120 < callLength && callLength <= 180) {
            if (times <= 3) {
                return 0.02;
            }
            return 0;
        } else if (180 < callLength && callLength <= 300) {
            if (times <= 3) {
                return 0.025;
            }
            return 0;
        } else {
            if (times <= 6) {
                return 0.03;
            }
            return 0;
        }
    }

    @Override
    public double queryPass() {
        return phoneMapper.queryPass();
    }

    @Override
    public double total() {
        return phoneMapper.total();
    }
}
