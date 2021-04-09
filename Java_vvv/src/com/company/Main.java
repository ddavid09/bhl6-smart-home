package com.company;

import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.now();
        TimeSimulator timeSimulator = new TimeSimulator(false);
        RequiredRequiredInternalTemperatureServiceImpl requiredInternalTemperatureService = new RequiredRequiredInternalTemperatureServiceImpl(timeSimulator);

        System.out.printf("temperatura "+requiredInternalTemperatureService.get());
	// write your code here
    }
}
