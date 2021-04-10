package com.company.SimulatorPackage;

import com.company.SolarPanelsAndPortServiceImpl;

public class DataClass {
    private final String time;
    private final String date;
    private final double tempIn;
    private final double tempExpected;
    private final double tempOut;
    private final double avgPower;
    private final double devicesHeat;
    private final double clouds;
    private final double avgPowerHomeHeat;
    private final double avgPowerHomeMaintenance;
    private final double solarSystemPower;
    private final double looseHeat;
    private final boolean airing;
    private final boolean heating;
    private final boolean maintaining;
    private final boolean falling;
    private final boolean heatingWater;
    private final double waterLevel;
    private final double powerUsage;
    private final double earnings;
    private final double salary;
    private final double batteryCapacity;
    private final double buyPrice;
    private final double sellPrice;
    private final SolarPanelsAndPortServiceImpl.Mode workMode;

    public DataClass(
            String time,
            String date,
            double tempIn,
            double tempExpected,
            double tempOut,
            double avgPower,
            double devicesHeat,
            double clouds,
            double avgPowerHomeHeat,
            double avgPowerHomeMaintenance,
            double solarSystemPower,
            double looseHeat,
            boolean airing,
            boolean heating,
            boolean maintaining,
            boolean falling,
            boolean heatingWater,
            double waterLevel,
            double powerUsage,
            double earnings,
            double salary,
            double batteryCapacity,
            double buyPrice,
            double sellPrice,
            SolarPanelsAndPortServiceImpl.Mode workMode
    ){
        this.time = time;
        this.date = date;
        this.tempIn = tempIn;
        this.tempExpected = tempExpected;
        this.tempOut = tempOut;
        this.avgPower = avgPower;
        this.devicesHeat = devicesHeat;
        this.clouds = clouds;
        this.avgPowerHomeHeat = avgPowerHomeHeat;
        this.avgPowerHomeMaintenance = avgPowerHomeMaintenance;
        this.solarSystemPower = solarSystemPower;
        this.looseHeat = looseHeat;
        this.airing = airing;
        this.heating = heating;
        this.maintaining = maintaining;
        this.falling = falling;
        this.heatingWater = heatingWater;
        this.waterLevel = waterLevel;
        this.powerUsage = powerUsage;
        this.earnings = earnings;
        this.salary = salary;
        this.batteryCapacity = batteryCapacity;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.workMode = workMode;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    public double getTempIn() {
        return tempIn;
    }

    public double getTempExpected() {
        return tempExpected;
    }

    public double getTempOut() {
        return tempOut;
    }

    public double getAvgPower() {
        return avgPower;
    }

    public double getDevicesHeat() {
        return devicesHeat;
    }

    public double getClouds() {
        return clouds;
    }

    public double getAvgPowerHomeHeat() {
        return avgPowerHomeHeat;
    }

    public double getAvgPowerHomeMaintenance() {
        return avgPowerHomeMaintenance;
    }

    public double getSolarSystemPower() {
        return solarSystemPower;
    }

    public double getLooseHeat() {
        return looseHeat;
    }

    public boolean isAiring() {
        return airing;
    }

    public boolean isHeating() {
        return heating;
    }

    public boolean isMaintaining() {
        return maintaining;
    }

    public boolean isFalling() {
        return falling;
    }

    public boolean isHeatingWater() {
        return heatingWater;
    }

    public double getWaterLevel() {
        return waterLevel;
    }

    public double getPowerUsage() {
        return powerUsage;
    }

    public double getEarnings() {
        return earnings;
    }

    public double getSalary() {
        return salary;
    }

    public double getBatteryCapacity() {
        return batteryCapacity;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public SolarPanelsAndPortServiceImpl.Mode getWorkMode() {
        return workMode;
    }
}
