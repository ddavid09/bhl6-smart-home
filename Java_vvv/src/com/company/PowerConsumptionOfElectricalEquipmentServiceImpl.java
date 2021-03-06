package com.company;




import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class PowerConsumptionOfElectricalEquipmentServiceImpl implements PowerConsumptionOfElectricalEquipmentService {


    TimeSimulator timeSimulator;

    public PowerConsumptionOfElectricalEquipmentServiceImpl(TimeSimulator timeSimulator) {
        this.timeSimulator = timeSimulator;
    }

    @Override
    public double getHeatFreePower(){
        return 0.7*getPowerConsumption();
    }

    @Override
    public double getPowerConsumption() {
        if(timeSimulator.isVacations()){
            return 0.5;
        }
        else {
            if(timeSimulator.isWorkingDay()){
                if(timeSimulator.getLocalDateTime().toLocalTime().isBefore(
                        LocalTime.parse("04:59:59", DateTimeFormatter.ISO_LOCAL_TIME))){
                    return 0.5;
                }else if(timeSimulator.getLocalDateTime().toLocalTime().isBefore(
                        LocalTime.parse("07:59:59", DateTimeFormatter.ISO_LOCAL_TIME))){
                    return 3.0;
                }else if(timeSimulator.getLocalDateTime().toLocalTime().isBefore(
                        LocalTime.parse("09:59:59", DateTimeFormatter.ISO_LOCAL_TIME))){
                    return 2.0;
                }else if(timeSimulator.getLocalDateTime().toLocalTime().isBefore(
                        LocalTime.parse("15:59:59", DateTimeFormatter.ISO_LOCAL_TIME))){
                    return 1.0;
                }else if(timeSimulator.getLocalDateTime().toLocalTime().isBefore(
                        LocalTime.parse("19:59:59", DateTimeFormatter.ISO_LOCAL_TIME))){
                    return 2.0;
                }else if(timeSimulator.getLocalDateTime().toLocalTime().isBefore(
                        LocalTime.parse("23:59:59", DateTimeFormatter.ISO_LOCAL_TIME))){
                    return 1.0;
                }else {
                    return 0.5;
                }
            }else{
                if(timeSimulator.getLocalDateTime().toLocalTime().isBefore(
                        LocalTime.parse("07:59:59", DateTimeFormatter.ISO_LOCAL_TIME))){
                    return 0.5;
                }else if(timeSimulator.getLocalDateTime().toLocalTime().isBefore(
                        LocalTime.parse("11:59:59", DateTimeFormatter.ISO_LOCAL_TIME))){
                    return 3.0;
                }else if(timeSimulator.getLocalDateTime().toLocalTime().isBefore(
                        LocalTime.parse("16:59:59", DateTimeFormatter.ISO_LOCAL_TIME))){
                    return 1.0;
                }else if(timeSimulator.getLocalDateTime().toLocalTime().isBefore(
                        LocalTime.parse("23:59:59", DateTimeFormatter.ISO_LOCAL_TIME))){
                    return 2.0;
                }else {
                    return 0.5;
                }
            }
        }

    }
}
