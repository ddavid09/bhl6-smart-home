package pl.kurs.restapi.services;

import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class EnergyTariffServiceImpl implements EnergyTariffService {

    @Autowired
    TimeSimulator timeSimulator;

    @Override
    public double getSellPrice() {
        if(timeSimulator.isWorkingDay()){
            if(
                    timeSimulator.getLocalDateTime().toLocalDate().getMonth().equals(Month.JANUARY) ||
                    timeSimulator.getLocalDateTime().toLocalDate().getMonth().equals(Month.FEBRUARY) ||
                    timeSimulator.getLocalDateTime().toLocalDate().getMonth().equals(Month.MARCH) ||
                    timeSimulator.getLocalDateTime().toLocalDate().getMonth().equals(Month.OCTOBER) ||
                    timeSimulator.getLocalDateTime().toLocalDate().getMonth().equals(Month.NOVEMBER) ||
                    timeSimulator.getLocalDateTime().toLocalDate().getMonth().equals(Month.DECEMBER)
            ){
                if(timeSimulator.getLocalDateTime().toLocalTime().isBefore(
                        LocalTime.parse("05:59:59", DateTimeFormatter.ISO_LOCAL_TIME))){
                    return 0.5;
                }else if(timeSimulator.getLocalDateTime().toLocalTime().isBefore(
                        LocalTime.parse("12:59:59", DateTimeFormatter.ISO_LOCAL_TIME))){
                    return 2.5;
                }else if(timeSimulator.getLocalDateTime().toLocalTime().isBefore(
                        LocalTime.parse("14:59:59", DateTimeFormatter.ISO_LOCAL_TIME))){
                    return 1.0;
                }else if(timeSimulator.getLocalDateTime().toLocalTime().isBefore(
                        LocalTime.parse("21:59:59", DateTimeFormatter.ISO_LOCAL_TIME))){
                    return 2.5;
                }else if(timeSimulator.getLocalDateTime().toLocalTime().isBefore(
                        LocalTime.parse("23:59:59", DateTimeFormatter.ISO_LOCAL_TIME))){
                    return 0.5;
                }else {
                    return 0.5;
                }
            }
            else {
                if(timeSimulator.getLocalDateTime().toLocalTime().isBefore(
                        LocalTime.parse("05:59:59", DateTimeFormatter.ISO_LOCAL_TIME))){
                    return 0.5;
                }else if(timeSimulator.getLocalDateTime().toLocalTime().isBefore(
                        LocalTime.parse("14:59:59", DateTimeFormatter.ISO_LOCAL_TIME))){
                    return 2.5;
                }else if(timeSimulator.getLocalDateTime().toLocalTime().isBefore(
                        LocalTime.parse("16:59:59", DateTimeFormatter.ISO_LOCAL_TIME))){
                    return 1.0;
                }else if(timeSimulator.getLocalDateTime().toLocalTime().isBefore(
                        LocalTime.parse("21:59:59", DateTimeFormatter.ISO_LOCAL_TIME))){
                    return 2.5;
                }else if(timeSimulator.getLocalDateTime().toLocalTime().isBefore(
                        LocalTime.parse("23:59:59", DateTimeFormatter.ISO_LOCAL_TIME))){
                    return 0.5;
                }else {
                    return 0.5;
                }

            }
        } else {
            if(
                    timeSimulator.getLocalDateTime().toLocalDate().getMonth().equals(Month.JANUARY) ||
                            timeSimulator.getLocalDateTime().toLocalDate().getMonth().equals(Month.FEBRUARY) ||
                            timeSimulator.getLocalDateTime().toLocalDate().getMonth().equals(Month.MARCH) ||
                            timeSimulator.getLocalDateTime().toLocalDate().getMonth().equals(Month.OCTOBER) ||
                            timeSimulator.getLocalDateTime().toLocalDate().getMonth().equals(Month.NOVEMBER) ||
                            timeSimulator.getLocalDateTime().toLocalDate().getMonth().equals(Month.DECEMBER)
            ){
                return 0.5;
            }
            else {
                if(timeSimulator.getLocalDateTime().toLocalTime().isBefore(
                        LocalTime.parse("11:59:59", DateTimeFormatter.ISO_LOCAL_TIME))){
                    return 0.5;
                }else if(timeSimulator.getLocalDateTime().toLocalTime().isBefore(
                        LocalTime.parse("14:59:59", DateTimeFormatter.ISO_LOCAL_TIME))){
                    return 0.25;
                }else {
                    return 0.5;
                }
            }
        }
    }

    @Override
    public double getBuyPrice() {
        if(timeSimulator.isWorkingDay()){
            if(
                    timeSimulator.getLocalDateTime().toLocalDate().getMonth().equals(Month.JANUARY) ||
                            timeSimulator.getLocalDateTime().toLocalDate().getMonth().equals(Month.FEBRUARY) ||
                            timeSimulator.getLocalDateTime().toLocalDate().getMonth().equals(Month.MARCH) ||
                            timeSimulator.getLocalDateTime().toLocalDate().getMonth().equals(Month.OCTOBER) ||
                            timeSimulator.getLocalDateTime().toLocalDate().getMonth().equals(Month.NOVEMBER) ||
                            timeSimulator.getLocalDateTime().toLocalDate().getMonth().equals(Month.DECEMBER)
            ){
                if(timeSimulator.getLocalDateTime().toLocalTime().isBefore(
                        LocalTime.parse("05:59:59", DateTimeFormatter.ISO_LOCAL_TIME))){
                    return 1.0;
                }else if(timeSimulator.getLocalDateTime().toLocalTime().isBefore(
                        LocalTime.parse("12:59:59", DateTimeFormatter.ISO_LOCAL_TIME))){
                    return 2.0;
                }else if(timeSimulator.getLocalDateTime().toLocalTime().isBefore(
                        LocalTime.parse("14:59:59", DateTimeFormatter.ISO_LOCAL_TIME))){
                    return 1.0;
                }else if(timeSimulator.getLocalDateTime().toLocalTime().isBefore(
                        LocalTime.parse("21:59:59", DateTimeFormatter.ISO_LOCAL_TIME))){
                    return 2.0;
                }else if(timeSimulator.getLocalDateTime().toLocalTime().isBefore(
                        LocalTime.parse("23:59:59", DateTimeFormatter.ISO_LOCAL_TIME))){
                    return 1.0;
                }else {
                    return 1.0;
                }
            }
            else {
                if(timeSimulator.getLocalDateTime().toLocalTime().isBefore(
                        LocalTime.parse("05:59:59", DateTimeFormatter.ISO_LOCAL_TIME))){
                    return 1.0;
                }else if(timeSimulator.getLocalDateTime().toLocalTime().isBefore(
                        LocalTime.parse("14:59:59", DateTimeFormatter.ISO_LOCAL_TIME))){
                    return 2.0;
                }else if(timeSimulator.getLocalDateTime().toLocalTime().isBefore(
                        LocalTime.parse("16:59:59", DateTimeFormatter.ISO_LOCAL_TIME))){
                    return 1.0;
                }else if(timeSimulator.getLocalDateTime().toLocalTime().isBefore(
                        LocalTime.parse("21:59:59", DateTimeFormatter.ISO_LOCAL_TIME))){
                    return 2.0;
                }else if(timeSimulator.getLocalDateTime().toLocalTime().isBefore(
                        LocalTime.parse("23:59:59", DateTimeFormatter.ISO_LOCAL_TIME))){
                    return 1.0;
                }else {
                    return 1.0;
                }

            }
        } else {
            if(
                    timeSimulator.getLocalDateTime().toLocalDate().getMonth().equals(Month.JANUARY) ||
                            timeSimulator.getLocalDateTime().toLocalDate().getMonth().equals(Month.FEBRUARY) ||
                            timeSimulator.getLocalDateTime().toLocalDate().getMonth().equals(Month.MARCH) ||
                            timeSimulator.getLocalDateTime().toLocalDate().getMonth().equals(Month.OCTOBER) ||
                            timeSimulator.getLocalDateTime().toLocalDate().getMonth().equals(Month.NOVEMBER) ||
                            timeSimulator.getLocalDateTime().toLocalDate().getMonth().equals(Month.DECEMBER)
            ){
                return 1.0;
            }
            else {
                if(timeSimulator.getLocalDateTime().toLocalTime().isBefore(
                        LocalTime.parse("11:59:59", DateTimeFormatter.ISO_LOCAL_TIME))){
                    return 1.0;
                }else if(timeSimulator.getLocalDateTime().toLocalTime().isBefore(
                        LocalTime.parse("14:59:59", DateTimeFormatter.ISO_LOCAL_TIME))){
                    return 0.5;
                }else {
                    return 1.0;
                }
            }
        }
    }
}
