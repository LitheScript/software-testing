package com.example.softwaretest.Entity;


import lombok.Data;

@Data
public class Triangle {
    private int a;
    private int b;
    private int c;
    private String expectResult;
    private int pass;

}
