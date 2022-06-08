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
    private int oyear;
    private int omonth;
    private int oday;
}
