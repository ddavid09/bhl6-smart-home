package com.company;



import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class ExpectedOutdoorTemperatureServiceImpl implements ExpectedOutdoorTemperatureService {

    public ExpectedOutdoorTemperatureServiceImpl(TimeSimulator timeSimulator) {
        this.timeSimulator = timeSimulator;
    }

    TimeSimulator timeSimulator;

    @Override
    public double get() {
        if(timeSimulator.getLocalDateTime().toLocalTime().isBefore(
                LocalTime.parse("04:59:59", DateTimeFormatter.ISO_LOCAL_TIME))){
            return 10;
        }else if(timeSimulator.getLocalDateTime().toLocalTime().isBefore(
                LocalTime.parse("07:59:59", DateTimeFormatter.ISO_LOCAL_TIME))){
            return 15;
        }else if(timeSimulator.getLocalDateTime().toLocalTime().isBefore(
                LocalTime.parse("09:59:59", DateTimeFormatter.ISO_LOCAL_TIME))){
            return 16;
        }else if(timeSimulator.getLocalDateTime().toLocalTime().isBefore(
                LocalTime.parse("15:59:59", DateTimeFormatter.ISO_LOCAL_TIME))){
            return 20;
        }else if(timeSimulator.getLocalDateTime().toLocalTime().isBefore(
                LocalTime.parse("19:59:59", DateTimeFormatter.ISO_LOCAL_TIME))){
            return 14;
        }else if(timeSimulator.getLocalDateTime().toLocalTime().isBefore(
                LocalTime.parse("23:59:59", DateTimeFormatter.ISO_LOCAL_TIME))){
            return 10;
        }else {
            return 10;
        }
    }
}
