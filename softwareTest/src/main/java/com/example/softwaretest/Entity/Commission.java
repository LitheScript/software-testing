package com.example.softwaretest.Entity;

import lombok.Data;

@Data
public class Commission {
    private int id;
    private int host;
    private int display;
    private int after;
    private int peripheral;
    private int sales;
    private double commission;
    private double actualCommission;
    private int pass;
}
