package com.example.softwaretest.Entity;

import lombok.Data;

@Data
public class Phone {
    private int callLength;
    private int times;
    private double expectResult;
    private double actualResult;
    private int pass;
}
