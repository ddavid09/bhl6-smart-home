package com.company;


import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static java.lang.Double.min;

public class WaterTankServiceImpl implements WaterTankService{

    double waterAmount = 0.0;
    TimeSimulator timeSimulator;

    public WaterTankServiceImpl(TimeSimulator timeSimulator) {
        this.timeSimulator = timeSimulator;
    }

    @Override
    public double getEnergyCost() {
        return 6;
    }

    @Override
    public double getTankSize() {
        return 150;
    }

    @Override
    public double getWater(double amount) {
        double returnWaterAmount = min(amount,waterAmount);
        waterAmount -= returnWaterAmount;
        return returnWaterAmount;
    }

    @Override
    public void boilTank() {
        waterAmount = 150;
    }

    @Override
    public double simAndGetEnergyCost(Long minutes){
        if(isOn()){
                return getEnergyCost()*(minutes/60);
        } else {
            return 0;
        }
    }

    @Override
    public boolean isOn(){
        return timeIsBetween("18:00:00", "19:12:00");

    }

    private boolean timeIsBefore(String time){
        return timeSimulator.getLocalDateTime().toLocalTime().isBefore(LocalTime.parse(time, DateTimeFormatter.ISO_LOCAL_TIME));
    }

    private boolean timeIsAfter(String time){
        return timeSimulator.getLocalDateTime().toLocalTime().isAfter(LocalTime.parse(time, DateTimeFormatter.ISO_LOCAL_TIME));
    }

    private boolean timeIsBetween(String afterTime, String beforeTime){
        return (timeIsAfter(afterTime) && timeIsBefore(beforeTime));
    }


}
