package com.company;



import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

import static java.lang.Double.max;
import static java.lang.Double.min;


public class SolarPanelsAndPortServiceImpl implements SolarPanelsAndPortService{


    CloudSimulator cloudSimulator;
    TimeSimulator timeSimulator;
    EnergyTariffService energyTariffService;

    public SolarPanelsAndPortServiceImpl(CloudSimulator cloudSimulator, TimeSimulator timeSimulator, EnergyTariffService energyTariffService) {
        this.cloudSimulator = cloudSimulator;
        this.timeSimulator = timeSimulator;
        this.energyTariffService = energyTariffService;
    }

    public enum Mode{
        BatteryCharge,SellSolarEnergy,FullBatteryCharge,BatterySupport
    }

    private Mode mode = Mode.BatteryCharge;

    double maxBatteryInput = 1;
    double maxBatteryOutput = 2;
    double maxBatteryCapacity = 7;
    double maxPortInput = 10;
    double maxPortOutput = 5;
    double actualBatteryCapacity = 0;

    @Override
    public double simAndGetCost(double demandEnergy, Mode mode) throws Exception {
        double availableEnergyFromSolar = outputPowerOfSolarPanel();
        double availableEnergyFromPort = maxPortInput;
        double availableEnergyFromBattery = min(maxBatteryOutput,actualBatteryCapacity);
        double energyPurchased = 0;
        double energySold = 0;

        if(demandEnergy > availableEnergyFromSolar+availableEnergyFromPort+availableEnergyFromBattery){
            throw new Exception("Za malo energi");
        }

        switch (mode){
            case BatteryCharge:
                if(demandEnergy < availableEnergyFromSolar){
                    if(maxBatteryCapacity>actualBatteryCapacity){
                        actualBatteryCapacity = min(maxBatteryCapacity,actualBatteryCapacity+min(maxBatteryInput,availableEnergyFromSolar-demandEnergy));
                    }
                } else {
                    energyPurchased = demandEnergy-availableEnergyFromSolar;
                }
                break;
            case SellSolarEnergy:
                if(demandEnergy<availableEnergyFromSolar){
                    energySold = availableEnergyFromSolar-demandEnergy;
                } else {
                    energyPurchased = demandEnergy-availableEnergyFromSolar;
                }
                break;
            case FullBatteryCharge:
                if(actualBatteryCapacity < maxBatteryCapacity){
                    double purchasedEnergyForBattery = actualBatteryCapacity-min(maxBatteryCapacity,actualBatteryCapacity+maxBatteryInput);
                    actualBatteryCapacity = min(maxBatteryCapacity,actualBatteryCapacity+maxBatteryInput);
                    if(demandEnergy > availableEnergyFromSolar){
                        energyPurchased = demandEnergy-availableEnergyFromSolar+purchasedEnergyForBattery;
                    }
                } else{
                    if(demandEnergy>availableEnergyFromSolar){
                        energyPurchased = demandEnergy-availableEnergyFromSolar;
                    }
                }
                break;
            case BatterySupport:
                if(demandEnergy > availableEnergyFromSolar+availableEnergyFromBattery){
                    actualBatteryCapacity = (demandEnergy-availableEnergyFromSolar);
                }
                else {
                    actualBatteryCapacity -= availableEnergyFromBattery;
                    energyPurchased = demandEnergy-availableEnergyFromBattery-availableEnergyFromSolar;
                }
                break;
        }

        double returnValue = 0.0;
        if(energySold>0){
            returnValue -= energySold*energyTariffService.getSellPrice();
        }
        if(energyPurchased>0){
            returnValue += energyPurchased*energyTariffService.getBuyPrice();
        }
        return returnValue;
    }

    private boolean timeIsBefore(String time){
        return timeSimulator.getLocalDateTime().toLocalTime().isBefore(LocalTime.parse(time, DateTimeFormatter.ISO_LOCAL_TIME));
    }

    private boolean timeIsAfter(String time){
        return timeSimulator.getLocalDateTime().toLocalTime().isAfter(LocalTime.parse(time, DateTimeFormatter.ISO_LOCAL_TIME));
    }

    private boolean timeIsBetween(String afterTime, String beforeTime){
        return (timeIsAfter(afterTime) && timeIsBefore(beforeTime));
    }

    @Override
    public double outputPowerOfSolarPanel(){
        if(timeSimulator.getLocalDateTime().getMonth().equals(Month.JANUARY) || timeSimulator.getLocalDateTime().getMonth().equals(Month.DECEMBER)){
            if(cloudSimulator.getPercentageOfCloud() > 0.9){
                if(timeIsAfter("15:00:00") || timeIsBefore("08:00:00")){
                    return 0;
                } else if ( timeIsBetween("08:00:00","10:00:00") || timeIsBetween("14:00:00","15:00:00")){
                    return 1.5;
                } else {
                    return 3;
                }
            }
            else if(cloudSimulator.getPercentageOfCloud() > 0.6){
                if(timeIsAfter("15:00:00") || timeIsBefore("08:00:00")){
                    return 0;
                } else if ( timeIsBetween("08:00:00","10:00:00") || timeIsBetween("14:00:00","15:00:00")){
                    return 1;
                } else {
                    return 2;
                }
            }
            else {
                if(timeIsAfter("15:00:00") || timeIsBefore("08:00:00")){
                    return 0;
                } else if ( timeIsBetween("08:00:00","10:00:00") || timeIsBetween("14:00:00","15:00:00")){
                    return 0.5;
                } else {
                    return 1.5;
                }
            }
        }
        else if(
                timeSimulator.getLocalDateTime().getMonth().equals(Month.FEBRUARY) || timeSimulator.getLocalDateTime().getMonth().equals(Month.MARCH) ||
                timeSimulator.getLocalDateTime().getMonth().equals(Month.OCTOBER) || timeSimulator.getLocalDateTime().getMonth().equals(Month.NOVEMBER)
        ){
            if(cloudSimulator.getPercentageOfCloud() > 0.9){
                if(timeIsAfter("16:00:00") || timeIsBefore("07:00:00")){
                    return 0;
                } else if ( timeIsBetween("07:00:00","09:00:00") || timeIsBetween("15:00:00","16:00:00")){
                    return 2;
                } else {
                    return 4;
                }
            }
            else if(cloudSimulator.getPercentageOfCloud() > 0.6){
                if(timeIsAfter("16:00:00") || timeIsBefore("07:00:00")){
                    return 0;
                } else if ( timeIsBetween("07:00:00","09:00:00") || timeIsBetween("15:00:00","16:00:00")){
                    return 2;
                } else {
                    return 3;
                }
            }
            else {
                if(timeIsAfter("16:00:00") || timeIsBefore("07:00:00")){
                    return 0;
                } else if ( timeIsBetween("07:00:00","09:00:00") || timeIsBetween("15:00:00","16:00:00")){
                    return 0.5;
                } else {
                    return 1.5;
                }
            }

        }
        else if(
                timeSimulator.getLocalDateTime().getMonth().equals(Month.APRIL) ||
                timeSimulator.getLocalDateTime().getMonth().equals(Month.MAY) ||
                timeSimulator.getLocalDateTime().getMonth().equals(Month.SEPTEMBER)
        ){
            if(cloudSimulator.getPercentageOfCloud() > 0.9){
                if(timeIsAfter("18:00:00") || timeIsBefore("06:00:00")){
                    return 0;
                } else if ( timeIsBetween("06:00:00","07:00:00") || timeIsBetween("17:00:00","18:00:00")){
                    return 3.5;
                } else {
                    return 5;
                }
            }
            else if(cloudSimulator.getPercentageOfCloud() > 0.6){
                if(timeIsAfter("18:00:00") || timeIsBefore("06:00:00")){
                    return 0;
                } else if ( timeIsBetween("06:00:00","07:00:00") || timeIsBetween("17:00:00","18:00:00")){
                    return 3;
                } else {
                    return 4;
                }
            }
            else {
                if(timeIsAfter("18:00:00") || timeIsBefore("06:00:00")){
                    return 0;
                } else if ( timeIsBetween("06:00:00","07:00:00") || timeIsBetween("17:00:00","18:00:00")){
                    return 1;
                } else {
                    return 2;
                }
            }
        }
        else {
            if(cloudSimulator.getPercentageOfCloud() > 0.9){
                if(timeIsAfter("20:00:00") || timeIsBefore("05:00:00")){
                    return 0;
                } else if ( timeIsBetween("05:00:00","07:00:00") || timeIsBetween("19:00:00","20:00:00")){
                    return 3.5;
                } else {
                    return 5;
                }
            }
            else if(cloudSimulator.getPercentageOfCloud() > 0.6){
                if(timeIsAfter("20:00:00") || timeIsBefore("05:00:00")){
                    return 0;
                } else if ( timeIsBetween("05:00:00","07:00:00") || timeIsBetween("19:00:00","20:00:00")){
                    return 3;
                } else {
                    return 4;
                }
            }
            else {
                if(timeIsAfter("20:00:00") || timeIsBefore("05:00:00")){
                    return 0;
                } else if ( timeIsBetween("05:00:00","07:00:00") || timeIsBetween("19:00:00","20:00:00")){
                    return 1;
                } else {
                    return 2;
                }
            }

        }

    }

    public double getMaxBatteryInput() {
        return maxBatteryInput;
    }

    public void setMaxBatteryInput(double maxBatteryInput) {
        this.maxBatteryInput = maxBatteryInput;
    }

    public double getMaxBatteryOutput() {
        return maxBatteryOutput;
    }

    public void setMaxBatteryOutput(double maxBatteryOutput) {
        this.maxBatteryOutput = maxBatteryOutput;
    }

    public double getMaxBatteryCapacity() {
        return maxBatteryCapacity;
    }

    public void setMaxBatteryCapacity(double maxBatteryCapacity) {
        this.maxBatteryCapacity = maxBatteryCapacity;
    }

    public double getMaxPortInput() {
        return maxPortInput;
    }

    public void setMaxPortInput(double maxPortInput) {
        this.maxPortInput = maxPortInput;
    }

    public double getMaxPortOutput() {
        return maxPortOutput;
    }

    public void setMaxPortOutput(double maxPortOutput) {
        this.maxPortOutput = maxPortOutput;
    }

    public double getActualBatteryCapacity(){
        return this.actualBatteryCapacity;
    }
}
