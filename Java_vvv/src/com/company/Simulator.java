package com.company;

import java.time.LocalDateTime;

public class Simulator {
    static long timeStampInMinutes = 1;

    TimeSimulator timeSimulator = new TimeSimulator(false);
    ExpectedOutdoorTemperatureService expectedOutdoorTemperatureService = new ExpectedOutdoorTemperatureServiceImpl(timeSimulator);
    CloudSimulator cloudSimulator = new CloudSimulator(timeSimulator);
    EnergyTariffService energyTariffService = new EnergyTariffServiceImpl();

    HomeHeaterService homeHeaterService = new HomeHeaterServiceImpl(expectedOutdoorTemperatureService,timeSimulator);
    SolarPanelsAndPortService solarPanelsAndPortService = new SolarPanelsAndPortServiceImpl(cloudSimulator,timeSimulator,energyTariffService);

    SolarPanelsAndPortServiceImpl.Mode solarMode = SolarPanelsAndPortServiceImpl.Mode.BatterySupport;

    HomeHeaterService.Mode homeHeaterMode = HomeHeaterService.Mode.NoHeat;


    public void updateModes(){

    }

    public void oneStep(){
        try {
            updateModes();
            double energyInKWh = homeHeaterService.simAndGetEnergy(homeHeaterMode,timeStampInMinutes);
            double cost = solarPanelsAndPortService.simAndGetCost(energyInKWh,solarMode);
            timeSimulator.moveTimeForwardByMinutes(timeStampInMinutes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void simulate(){

        timeSimulator.set(LocalDateTime.of(2020,1,1,0,0,0),false);





    }
}
