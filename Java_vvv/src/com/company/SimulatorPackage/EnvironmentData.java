package com.company.SimulatorPackage;

import com.company.HomeHeaterService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EnvironmentData {

    private double simulationTime = 0;
    private double tempIn;
    private double tempExpected;
    private double tempOut;
    private double avgPower;
    private double devicesHeat;
    private double clouds;
    private double avgPowerHomeHeat;
    private double avgPowerHomeMaintenance;
    private double solarSystemPower;
    private double tempDifferenceOutIn;
    private double tempDifferenceOutReq;
    private double tempDifferenceInReq;
    private double looseHeat;

    public EnvironmentData(Environment environment, double tempIn){
        this.tempIn = tempIn;
        downloadNewData(environment);
    }

    public void downloadNewData(Environment environment){
        this.tempExpected = environment.getRequiredIndoorTemperatureService().get();
        this.tempOut = environment.getExpectedOutdoorTemperatureService().get();
        this.avgPower = environment.getPowerConsumptionOfElectricalEquipmentService().getPowerConsumption();
        this.devicesHeat = environment.getPowerConsumptionOfElectricalEquipmentService().getHeatFreePower();
        this.clouds = environment.getCloudSimulator().getPercentageOfCloud();
        this.avgPowerHomeHeat = environment.getHomeHeaterService().getEnergyCost(HomeHeaterService.Mode.Heat, environment.getPowerConsumptionOfElectricalEquipmentService().getHeatFreePower());
        this.avgPowerHomeMaintenance = environment.getHomeHeaterService().getEnergyCost(HomeHeaterService.Mode.Maintenance, environment.getPowerConsumptionOfElectricalEquipmentService().getHeatFreePower());
        this.solarSystemPower = environment.getSolarPanelsAndPortService().outputPowerOfSolarPanel();
        this.tempDifferenceOutIn = Math.abs(tempOut - tempIn);
        this.tempDifferenceOutReq = Math.abs(tempOut - tempExpected);
        this.tempDifferenceInReq = Math.abs(tempIn - tempExpected);
        this.looseHeat = environment.getHomeHeaterService().looseHeat();
    }

    public void printData(LocalDateTime localDateTime){
        System.out.println();
        System.out.println("-----------------------------------------");
        System.out.println(localDateTime.format(DateTimeFormatter.ofPattern("dd-MM-yy HH:mm:ss")) + "\n");
        System.out.println("Temperatura Wewnętrzna " + tempIn);
        System.out.println("Temperatura Oczekiwana " + tempExpected + "\n");

        System.out.println("Temperatura Zewnętrzna " + tempOut);
        System.out.println("Zapotrzebowanie Mocy Do Grzania " + avgPowerHomeHeat);
        System.out.println("Zapotrzebowanie Mocy Do Podtrzymania " + avgPowerHomeMaintenance + "\n");

        System.out.println("Zachmurzenie " + clouds);
        System.out.println("Moc ogniw fotowoltaicznych " + solarSystemPower + "\n");

        System.out.println("Średnia Moc Urządzeń " + avgPower + "\n");
        System.out.println("Ciepło Urządzeń " + devicesHeat + "\n");

        System.out.println("Różnica temperatur Out i In " + tempDifferenceOutIn);
        System.out.println("Różnica temperatur Out i Req " + tempDifferenceOutReq);
        System.out.println("Różnica temperatur In i Req " + tempDifferenceInReq);
        System.out.println("-----------------------------------------");
    }

    public double getSimulationTime(){
        return simulationTime;
    }

    public void incrementSimulationTime(){
        double dt = 1;
        this.simulationTime += dt;
    }

    public double getTempIn() {
        return tempIn;
    }

    public void incrementTempIn(double percent){
        this.tempIn += 1.0 / 60.0 * percent;
    }

    public void decrementTempIn(double percent){
        this.tempIn -= looseHeat * percent;
    }

    public void changeTempInToOut(){
        this.tempIn = this.tempOut;
    }

    public double getTempExpected() {
        return tempExpected;
    }

    public double getTempOut() {
        return tempOut;
    }

    public double getAvgPower() {
        return avgPower;
    }

    public double getDevicesHeat() {
        return devicesHeat;
    }

    public double getClouds() {
        return clouds;
    }

    public double getAvgPowerHomeHeat() {
        return avgPowerHomeHeat;
    }

    public double getAvgPowerHomeMaintenance() {
        return avgPowerHomeMaintenance;
    }

    public double getSolarSystemPower() {
        return solarSystemPower;
    }

    public double getTempDifferenceOutIn() {
        return tempDifferenceOutIn;
    }

    public double getTempDifferenceOutReq() {
        return tempDifferenceOutReq;
    }

    public double getTempDifferenceInReq() {
        return tempDifferenceInReq;
    }

    public double getLooseHeat() {
        return looseHeat;
    }
}
