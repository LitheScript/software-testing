package com.example.softwaretest.Entity;

import lombok.Data;

@Data
public class Phone {
    private int id;
    private int length;
    private int times;
    private double expectOff;
    private double expectResult;
    private double actualOff;
    private double actualResult;
    private int pass;
}
