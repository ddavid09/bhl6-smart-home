package com.company;

public interface HomeHeaterService {


    double getEnergyCost(HomeHeaterServiceImpl.Mode mode);

    double simAndGetEnergy(HomeHeaterServiceImpl.Mode mode, Long minutes) throws Exception;


}