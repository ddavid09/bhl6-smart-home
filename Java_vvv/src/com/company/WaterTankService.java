package com.company;

public interface WaterTankService {
    double getBoilCost();
    double getTankSize();
    double getWater(double amount);
    void boilTank();
}