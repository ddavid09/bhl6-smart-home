package com.company;

public interface HomeHeaterService {

    enum Mode {
        Heat,Maintenance,NoHeat,Weathering
    }

    double getTemperatureInside();

    double getEnergyCost(Mode mode, double freeEnergyInKWh);

    double simAndGetEnergy(Mode mode, Long minutes, double freeEnergyInKWh) throws Exception;


}