package com.company;

public interface WaterTankService {

    double getEnergyCost();
    double getTankSize();
    double getWater(double amount);
    void boilTank();

    double simAndGetEnergyCost(Long minutes);

    boolean isOn();
}