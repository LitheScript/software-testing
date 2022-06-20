package com.example.softwaretest.Entity;


import lombok.Data;

@Data
public class Triangle {
    private int id;
    private double a;
    private double b;
    private double c;
    private String expectResult;
    private String actualResult;
    private int pass;

}
