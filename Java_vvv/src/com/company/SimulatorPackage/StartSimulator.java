package com.company.SimulatorPackage;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

public class StartSimulator {

    public static void startSimulator(){
        Environment environment = new Environment(false);
        EnvironmentData environmentData = new EnvironmentData(environment, 10);
        int people = 3; //Licza mieszkańców domu od 3 do 6
        int waterForPerson = 180 / people;
        int timeForWash = waterForPerson / 15;
        int year;
        int month;
        int day;
        LocalDateTime dateTime;
        LocalDateTime[] washTime = new LocalDateTime[people];
        boolean[] washing = new boolean[people];
        int[] washingTimer = new int[people];
        boolean newDay = true;
        boolean airing = false;
        boolean heating = false;
        boolean maintaining = false;
        boolean falling = false;
        boolean heatingWater = false;
        int airingTime = 0;
        double waterLevel = 0;
        double powerUsage;


        environment.getTimeSimulator().set(environment.getTimeSimulator().getLocalDateTime().plusHours(3), false);
        System.out.println(environment.getTimeSimulator().getLocalDateTime() + " :: Rozpoczęcie symulacji");
        while(environmentData.getSimulationTime() < 10000) {
            environmentData.downloadNewData(environment);

            if(environment.getTimeSimulator().getLocalDateTime().getHour() == 0 && environment.getTimeSimulator().getLocalDateTime().getMinute() == 0)
                newDay = true;

            if(newDay){
                year = environment.getTimeSimulator().getLocalDateTime().getYear();
                month = environment.getTimeSimulator().getLocalDateTime().getMonthValue();
                day = environment.getTimeSimulator().getLocalDateTime().getDayOfMonth();
                dateTime = LocalDateTime.of(year, month, day, 16, 0, 0);
                for(int i = 0; i < people; i++){
                    LocalDateTime newDate = dateTime;
                    newDate = newDate.plusHours(ThreadLocalRandom.current().nextInt(0, 5));
                    newDate = newDate.plusMinutes(ThreadLocalRandom.current().nextInt(0, 59));
                    newDate = newDate.plusSeconds(ThreadLocalRandom.current().nextInt(0, 59));
                    washTime[i] = newDate;
                    washing[i] = false;
                    washingTimer[i] = 0;
                    System.out.println(washTime[i]);
                }
                newDay = false;
            }

            if(airing && airingTime < 59){
                System.out.println("Wietrzenie!");
                airingTime += 1;
            }
            else if(airing && airingTime == 59){
                System.out.println("Koniec wietrzenia!");
                environmentData.changeTempInToOut();
                airing = false;
                airingTime = 0;
            }
            else{
                if(environmentData.getTempDifferenceOutReq() < 1 ){
                    if(environmentData.getTempDifferenceOutIn() < 1){
                        System.out.println("Podtrzymanie!");
                        heating = false;
                        maintaining = true;
                        falling = false;
                    }
                    else{
                        if(environmentData.getTempDifferenceInReq() < 1){
                            System.out.println("Podtrzymanie!");
                            heating = false;
                            maintaining = true;
                            falling = false;
                        }
                        else{
                            System.out.println("Rozpoczynamy wietrzenie!");
                            heating = false;
                            maintaining = false;
                            falling = false;
                            airing = true;
                        }
                    }
                }
                else{
                    if(environmentData.getTempDifferenceInReq() < 1){
                        System.out.println("Podtrzymanie!");
                        heating = false;
                        maintaining = true;
                        falling = false;
                    }
                    else{
                        if(environmentData.getTempIn() < environmentData.getTempExpected()){
                            System.out.println("Ogrzewanie!");
                            heating = true;
                            maintaining = false;
                            falling = false;
                        }
                        else{
                            System.out.println("Spadek!");
                            heating = false;
                            maintaining = false;
                            falling = true;
                        }
                    }
                }
            }

            for(int i = 0; i < people; i++){
                if(washTime[i].getHour() == environment.getTimeSimulator().getLocalDateTime().getHour() && washTime[i].getMinute() == environment.getTimeSimulator().getLocalDateTime().getMinute()){
                    washing[i] = true;
                }
            }

            for(int i = 0; i < people; i++){
                if(washing[i]){
                    if(waterLevel > waterForPerson && washingTimer[i] < timeForWash){
                        System.out.println("Człowiek nr " + i + " się myje!");
                        waterLevel -= 15;
                        washingTimer[i] += 1;
                    }
                    if(washingTimer[i] == timeForWash){
                        System.out.println("Człowiek nr " + i + " skończył się myć!");
                        washing[i] = false;
                    }
                }
            }

            powerUsage = 0;
            if(waterLevel < 150){
                heatingWater = true;
            }
            else if(waterLevel == 150){
                heatingWater = false;
            }
            if(heatingWater){
                waterLevel += 2.5;
                powerUsage += 6;
            }

            double heatingPowerNeeded = environmentData.getAvgPowerHomeHeat() - environmentData.getDevicesHeat();
            double maintainingPowerNeeded = environmentData.getAvgPowerHomeMaintenance() - environmentData.getDevicesHeat();

            if(heating){
                if(heatingPowerNeeded + powerUsage <= 10){
                    powerUsage += heatingPowerNeeded;
                    environmentData.incrementTempIn(1);
                }
                else {
                    double x = 10 - powerUsage - heatingPowerNeeded;
                    x = heatingPowerNeeded + x;
                    powerUsage += x;
                    if(x > maintainingPowerNeeded) environmentData.incrementTempIn(x / environmentData.getAvgPowerHomeHeat());
                    if(x < maintainingPowerNeeded) environmentData.decrementTempIn(x / environmentData.getAvgPowerHomeMaintenance());
                }
            }
            if(maintaining){
                if(maintainingPowerNeeded + powerUsage <= 10){
                    powerUsage += maintainingPowerNeeded;
                }
                else {
                    double x = 10 - powerUsage - maintainingPowerNeeded;
                    x = maintainingPowerNeeded + x;
                    powerUsage += x;
                    environmentData.decrementTempIn(x / environmentData.getAvgPowerHomeMaintenance());
                }
            }
            if(falling){
                environmentData.decrementTempIn(1);
            }

            environmentData.printData(environment.getTimeSimulator().getLocalDateTime());
            environment.getTimeSimulator().moveTimeForwardByMinutes(1);
            environmentData.incrementSimulationTime();
        }
    }
}
