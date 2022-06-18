package com.example.softwaretest.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Calendar {
    private int id;
    private int year;
    private int month;
    private int day;
    private String expectResult;
    private String actualResult;
    private int pass;
}
