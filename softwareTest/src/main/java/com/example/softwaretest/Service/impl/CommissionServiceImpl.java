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
    public List<Commission> getTestCases() {
        return commissionMapper.getTestCases();
    }

    @Override
    public void cleanTestCases() {
        commissionMapper.cleanTestCases();
    }

    @Override
    public void saveTestCases(Commission commission) {
        commissionMapper.saveTestCases(commission);
    }

    @Override
    public double testCommission(Commission commission) {

        if(commission.getAfter()!=-1){
            return -5;
        }

        int host = commission.getHost();
        int display = commission.getDisplay();
        int peripheral = commission.getPeripheral();

        if(host==-1){
            return -4;
        }
        if (host == 0 || display == 0 || peripheral == 0) {
            return -1;
        }
        if(host<0||display<0||peripheral<0){
            return -2;
        }
        if(host >70 ||display >80 ||peripheral>90){
            return -3;
        }

        int totalSales = host*25+display*30+peripheral*45;
        if (totalSales <= 1000) {
            return totalSales * 0.1;
        } else if (totalSales > 1000 && totalSales <= 1800) {
            return totalSales * 0.15;
        } else{
            return totalSales * 0.2;
        }
    }

    @Override
    public double queryPass() {
        return commissionMapper.queryPass();
    }

    @Override
    public double total() {
        return commissionMapper.total();
    }


}
