package pl.kurs.restapi.services;

public interface WaterTankService {
    double getBoilCost();
    double getTankSize();
    double getWater(double amount);
    void boilTank();
}