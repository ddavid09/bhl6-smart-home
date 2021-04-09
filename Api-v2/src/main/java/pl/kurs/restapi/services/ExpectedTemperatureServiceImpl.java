package pl.kurs.restapi.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service
public class ExpectedTemperatureServiceImpl implements ExpectedTemperatureService{

    @Autowired
    TimeSimulator timeSimulator;

    @Override
    public double get() {
        if(timeSimulator.isVacations()){
            return 12;
        }
        else {
            if(timeSimulator.isWorkingDay()){
                if(timeSimulator.getLocalDateTime().toLocalTime().isBefore(
                        LocalTime.parse("04:59:59", DateTimeFormatter.ISO_LOCAL_TIME)
                )){
                    return 20;
                }
                else{
                    return 23;
                }
            }
            else {
                if(timeSimulator.getLocalDateTime().toLocalTime().isBefore(
                        LocalTime.parse("07:59:59", DateTimeFormatter.ISO_LOCAL_TIME)
                )){
                    return 20;
                }
                else{
                    return 23;
                }
            }
        }
    }
}
