package pl.kurs.restapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service
public class ExternalTemperatureServiceImpl implements  ExternalTemperatureService{

    @Autowired
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
