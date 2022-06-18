package com.example.softwaretest.Service.impl;

import com.example.softwaretest.Entity.Commission;
import com.example.softwaretest.Mapper.CommissionMapper;
import com.example.softwaretest.Service.CommissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

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

//    public double test(Commission commission) {
//
//        int MAXHOST = 70;
//        int MAXDISPLAYS = 80;
//        int MAXPERIPHERAL = 90;
//
//        int host = commission.getHost();
//        int display = commission.getDisplay();
//        int peripheral = commission.getPeripheral();
//
//        if (host == 0 || display == 0 || peripheral == 0) {
//            return -1;
//        }
//
//        int totalSales = 0;
//        if (totalSales <= 1000) {
//            return totalSales * 0.1;
//        } else if (totalSales > 1000 && totalSales <= 1800) {
//            return totalSales * 0.15;
//        } else if (totalSales > 1800) {
//            return totalSales * 0.2;
//        }
//
//
//        if (in.hasNextInt()) {
//            salesOfDisplays = in.nextInt();
//            if (salesOfDisplays < 1) {
//                System.out.println("error!请输入大于1的数");
//            } else if (salesOfDisplays > MAXDISPLAYS) {
//                System.out.println("error!超过显示屏最大销售量");
//            }
//            displays.setSales(salesOfDisplays);
//        } else {
//            System.out.println("error!请输入整数");
//        }
//        if (in.hasNextInt()) {
//            salesOfPeripheral = in.nextInt();
//            if (salesOfPeripheral < 1) {
//                System.out.println("error!请输入大于1的数");
//            } else if (salesOfPeripheral > MAXPERIPHERAL) {
//                System.out.println("error!超过外设最大销售量");
//            }
//            peripheral.setSales(salesOfPeripheral);
//        } else {
//            System.out.println("error!请输入整数");
//        }
//
//    }
//    }
}
