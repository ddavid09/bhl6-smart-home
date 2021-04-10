package com.company;




import static java.lang.Double.max;
import static java.lang.Double.min;


public class HomeHeaterServiceImpl implements HomeHeaterService{


    ExpectedOutdoorTemperatureService externalTemperatureService;
    TimeSimulator timeSimulator;

    public HomeHeaterServiceImpl(ExpectedOutdoorTemperatureService externalTemperatureService, TimeSimulator timeSimulator) {
        this.externalTemperatureService = externalTemperatureService;
        this.timeSimulator = timeSimulator;
    }

    private double temperatureInside = 20;

    @Override
    public double getTemperatureInside() {
        return temperatureInside;
    }

    @Override
    public double getEnergyCost(Mode mode, double freeEnergyInKWh){
        double returnValue=0;
        if(mode == Mode.Maintenance){
            if(externalTemperatureService.get() > 20){ //>20
                returnValue = 0;
            } else if(externalTemperatureService.get() > 15){ //15-20
                returnValue = 0.5;
            } else if(externalTemperatureService.get() > 5){ //5-15
                returnValue = 1;
            } else if(externalTemperatureService.get() > 0){ //0-5
                returnValue = 2;
            } else if(externalTemperatureService.get() > -5){ //0 - -5
                returnValue = 3;
            } else if(externalTemperatureService.get() > -10){ //-5 - 10
                returnValue = 5;
            } else if(externalTemperatureService.get() > -20){ //-10 - -20
                returnValue = 7;
            } else{ // <-20
                returnValue = 9;
            }
        }
        else if(mode == Mode.Heat){
            if(externalTemperatureService.get() > 20){ //>20
                returnValue = 0;
            } else if(externalTemperatureService.get() > 15){ //15-20
                returnValue = 2;
            } else if(externalTemperatureService.get() > 5){ //5-15
                returnValue = 4;
            } else if(externalTemperatureService.get() > 0){ //0-5
                returnValue = 5;
            } else if(externalTemperatureService.get() > -5){ //0 - -5
                returnValue = 6;
            } else if(externalTemperatureService.get() > -10){ //-5 - 10
                returnValue = 7;
            } else if(externalTemperatureService.get() > -20){ //-10 - -20
                returnValue = 10;
            } else{ // <-20
                returnValue = 12;
            }
        }
        return max(0,returnValue-freeEnergyInKWh);
    }

    @Override
    public double simAndGetEnergy(Mode mode, Long minutes, double freeEnergyInKWh) throws Exception {
        if(mode == Mode.Heat){
            this.temperatureInside = min(temperatureInside+1,25);
        } else if(mode == Mode.Weathering){
            this.weathering(minutes);
        } else if(mode == Mode.NoHeat){
            this.noHeat(minutes);
        }

        return getEnergyCost(mode,freeEnergyInKWh)*minutes/60;
    }

    private void noHeat(Long minutes){
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
        if(externalTemperatureService.get()<temperatureInside){
            this.temperatureInside = max(externalTemperatureService.get(),(this.temperatureInside-temperatureDecrease));
        }
    }

    private void weathering(Long minutes) throws Exception {
        double percentageOfHour = minutes/60;

        double externalTemperature = externalTemperatureService.get();

        if(externalTemperature < temperatureInside){ //chlodze
            this.temperatureInside = temperatureInside - (temperatureInside-externalTemperature)*percentageOfHour;
            this.temperatureInside = max(temperatureInside,externalTemperature);
        }
        else{ //grzeje
            this.temperatureInside = temperatureInside + (externalTemperature-temperatureInside)*percentageOfHour;
            this.temperatureInside = min(temperatureInside,externalTemperature);
        }
    }







}
