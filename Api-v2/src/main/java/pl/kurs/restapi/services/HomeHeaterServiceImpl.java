package pl.kurs.restapi.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.el.ValueExpression;
import java.time.LocalDateTime;

@Service
public class HomeHeaterServiceImpl implements HomeHeaterService{

    @Autowired
    ExternalTemperatureServiceImpl externalTemperatureService;

    @Autowired
    TimeSimulator timeSimulator;

    public enum State {
        Heat,Maintenance,NoHeat,Weathering
    }


    private double temperatureInside = 20;
    private LocalDateTime lastTimeOfHeating = LocalDateTime.now();
    private State state = State.NoHeat;

    @Override
    public double getCost(){
        if(state == State.Maintenance){
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
        if(state == State.Heat){
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
    public void setHeater(State state){
        this.state=state;
    }

    @Override
    public void heatOrNot(){
        lastTimeOfHeating = timeSimulator.getLocalDateTime();
    }

    @Override
    public void weathering(double percentageOfHour) throws Exception {
        if(percentageOfHour>1.0){
            throw new Exception("BadValue");
        }
        double externalTemperature = externalTemperatureService.get();

        if(externalTemperature>temperatureInside){
            this.temperatureInside = temperatureInside + (externalTemperature-temperatureInside)*percentageOfHour;
        }
        else{
            this.temperatureInside = temperatureInside - (externalTemperature+temperatureInside)*percentageOfHour;
        }
    }








}
