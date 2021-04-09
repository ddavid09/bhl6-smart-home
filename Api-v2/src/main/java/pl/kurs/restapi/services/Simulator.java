package pl.kurs.restapi.services;

import java.time.LocalDateTime;

public class Simulator {
    static long timeStampInMinutes = 1;

    TimeSimulator timeSimulator = new TimeSimulator();

    public void moveTimeForward(){
        timeSimulator.moveTimeForwardByMinutes(timeStampInMinutes);




    }


    public void simulate(){

        timeSimulator.set(LocalDateTime.of(2020,1,1,0,0,0),false);





    }
}
