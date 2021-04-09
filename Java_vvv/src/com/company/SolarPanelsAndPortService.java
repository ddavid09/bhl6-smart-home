package com.company;

public interface SolarPanelsAndPortService {

    double simAndGetCost(double demandEnergy, SolarPanelsAndPortServiceImpl.Mode mode) throws Exception;

    double outputPowerOfSolarPanel();

}
