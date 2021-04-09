package com.company;

public interface HomeHeaterService {

    enum Mode {
        Heat,Maintenance,NoHeat,Weathering
    }

    double getEnergyCost(HomeHeaterServiceImpl.Mode mode);

    double simAndGetEnergy(HomeHeaterServiceImpl.Mode mode, Long minutes) throws Exception;


}