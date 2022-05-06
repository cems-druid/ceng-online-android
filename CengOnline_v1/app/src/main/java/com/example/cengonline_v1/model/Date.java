package com.example.cengonline_v1.model;

public class Date {

    private int day;
    private int month;
    private int year;

    public Date(int day, int month, int year){
        this.day = day;
        this.month = month;
        this.year =year;
    }

    //getter and setters
    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDateFormat(int day, int month, int year){
        String dayS = String.valueOf(day);
        String monthS = String.valueOf(month);
        String yearS = String.valueOf(year);

        return dayS + "." + monthS + "." + yearS;
    }
}
