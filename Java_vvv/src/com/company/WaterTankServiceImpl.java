package com.company;


import static java.lang.Double.min;

public class WaterTankServiceImpl implements WaterTankService{

    double waterAmount = 0.0;

    @Override
    public double getBoilCost() {
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
}
