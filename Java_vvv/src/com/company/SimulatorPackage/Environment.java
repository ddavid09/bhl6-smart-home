package com.company.SimulatorPackage;

import com.company.*;

public class Environment {
    private final TimeSimulator timeSimulator;
    private final ExpectedOutdoorTemperatureServiceImpl expectedOutdoorTemperatureService;
    private final RequiredIndoorTemperatureServiceImpl requiredIndoorTemperatureService;
    private final PowerConsumptionOfElectricalEquipmentServiceImpl powerConsumptionOfElectricalEquipmentService;
    private final CloudSimulator cloudSimulator;
    private final HomeHeaterServiceImpl homeHeaterService;
    private final SolarPanelsAndPortServiceImpl solarPanelsAndPortService;

    public Environment(boolean isVacation){
        this.timeSimulator = new TimeSimulator(isVacation);
        this.expectedOutdoorTemperatureService = new ExpectedOutdoorTemperatureServiceImpl(timeSimulator);
        this.requiredIndoorTemperatureService = new RequiredIndoorTemperatureServiceImpl(timeSimulator);
        this.powerConsumptionOfElectricalEquipmentService = new PowerConsumptionOfElectricalEquipmentServiceImpl(timeSimulator);
        this.cloudSimulator = new CloudSimulator(timeSimulator);
        this.homeHeaterService = new HomeHeaterServiceImpl(expectedOutdoorTemperatureService, timeSimulator);
        EnergyTariffServiceImpl energyTariffService = new EnergyTariffServiceImpl(timeSimulator);
        this.solarPanelsAndPortService = new SolarPanelsAndPortServiceImpl(cloudSimulator, timeSimulator, energyTariffService);
    }

    public TimeSimulator getTimeSimulator() {
        return timeSimulator;
    }

    public ExpectedOutdoorTemperatureServiceImpl getExpectedOutdoorTemperatureService() {
        return expectedOutdoorTemperatureService;
    }

    public RequiredIndoorTemperatureServiceImpl getRequiredIndoorTemperatureService() {
        return requiredIndoorTemperatureService;
    }

    public PowerConsumptionOfElectricalEquipmentServiceImpl getPowerConsumptionOfElectricalEquipmentService() {
        return powerConsumptionOfElectricalEquipmentService;
    }

    public CloudSimulator getCloudSimulator() {
        return cloudSimulator;
    }

    public HomeHeaterServiceImpl getHomeHeaterService() {
        return homeHeaterService;
    }

    public SolarPanelsAndPortServiceImpl getSolarPanelsAndPortService() {
        return solarPanelsAndPortService;
    }
}
