package com.company;




import java.time.LocalDateTime;

public class HomeHeaterServiceImpl implements HomeHeaterService{


    ExpectedOutdoorTemperatureService externalTemperatureService;
    TimeSimulator timeSimulator;

    public HomeHeaterServiceImpl(ExpectedOutdoorTemperatureService externalTemperatureService, TimeSimulator timeSimulator) {
        this.externalTemperatureService = externalTemperatureService;
        this.timeSimulator = timeSimulator;
    }

    private double temperatureInside = 20;

    @Override
    public double getEnergyCost(Mode mode){
        if(mode == Mode.Maintenance){
            if(externalTemperatureService.get() > 20){ //>20
                return 0;
            } else if(externalTemperatureService.get() > 15){ //15-20
                return 0.5;
            } else if(externalTemperatureService.get() > 5){ //5-15
                return 1;
            } else if(externalTemperatureService.get() > 0){ //0-5
                return 2;
            } else if(externalTemperatureService.get() > -5){ //0 - -5
                return 3;
            } else if(externalTemperatureService.get() > -10){ //-5 - 10
                return 5;
            } else if(externalTemperatureService.get() > -20){ //-10 - -20
                return 7;
            } else{ // <-20
                return 9;
            }
        }
        if(mode == Mode.Heat){
            if(externalTemperatureService.get() > 20){ //>20
                return 0;
            } else if(externalTemperatureService.get() > 15){ //15-20
                return 2;
            } else if(externalTemperatureService.get() > 5){ //5-15
                return 4;
            } else if(externalTemperatureService.get() > 0){ //0-5
                return 5;
            } else if(externalTemperatureService.get() > -5){ //0 - -5
                return 6;
            } else if(externalTemperatureService.get() > -10){ //-5 - 10
                return 7;
            } else if(externalTemperatureService.get() > -20){ //-10 - -20
                return 10;
            } else{ // <-20
                return 12;
            }
        }
        else{
            return 0;
        }
    }

    @Override
    public double simAndGetEnergy(Mode mode, Long minutes) throws Exception {
        if(mode == Mode.Heat){
            this.temperatureInside = temperatureInside+1;
        } else if(mode == Mode.Weathering){
            this.weathering(minutes);
        } else if(mode == Mode.NoHeat){
            this.maintenance(minutes);
        }
        return getEnergyCost(mode)*minutes/60;
    }

    private void maintenance(Long minutes){
        double hoursByOneDegree = 0;
        if(externalTemperatureService.get() > 20){ //>20
            hoursByOneDegree = 20;
        } else if(externalTemperatureService.get() > 15){ //15-20
            hoursByOneDegree = 6;
        } else if(externalTemperatureService.get() > 5){ //5-15
            hoursByOneDegree = 4;
        } else if(externalTemperatureService.get() > 0){ //0-5
            hoursByOneDegree = 3;
        } else if(externalTemperatureService.get() > -5){ //0 - -5
            hoursByOneDegree = 2;
        } else if(externalTemperatureService.get() > -10){ //-5 - 10
            hoursByOneDegree = 1;
        } else if(externalTemperatureService.get() > -20){ //-10 - -20
            hoursByOneDegree = 0.5;
        } else{ // <-20
            hoursByOneDegree = 0.25;
        }

        double temperatureDecrease = hoursByOneDegree*(minutes/60);
        this.temperatureInside = this.temperatureInside-temperatureDecrease;
    }

    private void weathering(Long minutes) throws Exception {
        double percentageOfHour = minutes/60;

        double externalTemperature = externalTemperatureService.get();

        if(externalTemperature>temperatureInside){
            this.temperatureInside = temperatureInside + (externalTemperature-temperatureInside)*percentageOfHour;
        }
        else{
            this.temperatureInside = temperatureInside - (externalTemperature+temperatureInside)*percentageOfHour;
        }
    }







}
