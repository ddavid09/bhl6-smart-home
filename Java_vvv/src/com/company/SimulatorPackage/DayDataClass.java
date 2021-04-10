package com.company.SimulatorPackage;

public class DayDataClass {
    private final String time;
    private final String date;
    private final double earnings;
    private final double salary;

    public DayDataClass(
        String time,
        String date,
        double earnings,
        double salary
    ){
        this.time = time;
        this.date = date;
        this.earnings = earnings;
        this.salary = salary;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    public double getEarnings() {
        return earnings;
    }

    public double getSalary() {
        return salary;
    }
}
