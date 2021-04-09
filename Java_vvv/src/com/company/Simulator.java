package com.company;

import java.time.LocalDateTime;

public class Simulator {
    static long timeStampInMinutes = 1;

    TimeSimulator timeSimulator = new TimeSimulator(false);


    public void changeParams(){

    }

    public void oneStep(){
        timeSimulator.moveTimeForwardByMinutes(timeStampInMinutes);












    }


    public void simulate(){

        timeSimulator.set(LocalDateTime.of(2020,1,1,0,0,0),false);





    }
}
