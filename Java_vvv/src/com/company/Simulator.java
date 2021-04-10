package com.company;

import java.time.LocalDateTime;

public class Simulator {
    static long timeStampInMinutes = 1;

    TimeSimulator timeSimulator = new TimeSimulator(false);
    ExpectedOutdoorTemperatureService expectedOutdoorTemperatureService = new ExpectedOutdoorTemperatureServiceImpl(timeSimulator);
    CloudSimulator cloudSimulator = new CloudSimulator(timeSimulator);
    EnergyTariffService energyTariffService = new EnergyTariffServiceImpl(timeSimulator);
    PowerConsumptionOfElectricalEquipmentService powerConsumptionOfElectricalEquipmentService = new PowerConsumptionOfElectricalEquipmentServiceImpl(timeSimulator);
    WaterTankService waterTankService = new WaterTankServiceImpl(timeSimulator);
    RequiredIndoorTemperatureService requiredIndoorTemperatureService = new RequiredIndoorTemperatureServiceImpl(timeSimulator);
    HomeHeaterService homeHeaterService = new HomeHeaterServiceImpl(expectedOutdoorTemperatureService,timeSimulator);
    SolarPanelsAndPortService solarPanelsAndPortService = new SolarPanelsAndPortServiceImpl(cloudSimulator,timeSimulator,energyTariffService);

    SolarPanelsAndPortServiceImpl.Mode solarMode = SolarPanelsAndPortServiceImpl.Mode.BatteryCharge;
    HomeHeaterService.Mode homeHeaterMode = HomeHeaterService.Mode.Heat;


   public HomeHeaterService.Mode getHeatMode(){
       double outdoorTemperature = expectedOutdoorTemperatureService.get();
       double requiredTemperature = requiredIndoorTemperatureService.get();
       double indoorTemperature = homeHeaterService.getTemperatureInside();

       if(requiredTemperature-1 <= indoorTemperature && indoorTemperature <= requiredTemperature+1){ //czy trzeba grzac/chlodzic - tak nie koniecznie
           return HomeHeaterService.Mode.NoHeat;

       }
       else {
           if(requiredTemperature>indoorTemperature){ //trzeba grzac
               if(outdoorTemperature>indoorTemperature){
                   return HomeHeaterService.Mode.Weathering;
               }
               else {
                   return HomeHeaterService.Mode.Heat;
               }
           }
           else { //trzeba chlodzic
               if(outdoorTemperature<indoorTemperature){
                   return HomeHeaterService.Mode.Weathering;
               }
               else {
                   return HomeHeaterService.Mode.NoHeat;
               }

           }
       }
   }


    public void updateModes(){

    }

    public void oneStep(){
        try {
            updateModes();
            double freeEnergyInKWh = powerConsumptionOfElectricalEquipmentService.getHeatFreePower();
            double energyForDevicesInKWh = powerConsumptionOfElectricalEquipmentService.getHeatFreePower();
            double energyForHeatingInKWh = homeHeaterService.simAndGetEnergy(homeHeaterMode,timeStampInMinutes,freeEnergyInKWh);
            double energyForWatterInKWh = waterTankService.simAndGetEnergyCost(timeStampInMinutes);
            double cost = solarPanelsAndPortService.simAndGetCost(energyForHeatingInKWh+energyForDevicesInKWh,solarMode);

            System.out.print("Time: "+timeSimulator.getLocalDateTime());
            System.out.print(" temp inside:"+homeHeaterService.getTemperatureInside());
            System.out.print(" temp outside:"+expectedOutdoorTemperatureService.get());
            System.out.print(" energyForHeatingInKWh:"+energyForHeatingInKWh);
            System.out.print(" energyForWatterInKWh:"+energyForWatterInKWh);
            System.out.println(" energyForDevicesInKWh:"+energyForDevicesInKWh+" cost:"+cost);

            timeSimulator.moveTimeForwardByMinutes(timeStampInMinutes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void simulate(){

        timeSimulator.set(LocalDateTime.of(2020,1,1,0,0,0),false);
        try {
            cloudSimulator.setPercentageOfCloud(0.0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for(int i=0; i<2000; i++){
            oneStep();
        }





    }
}
