package pl.kurs.restapi.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.kurs.restapi.services.RequiredInternalTemperatureService;
import pl.kurs.restapi.services.TimeSimulator;

import java.time.LocalDateTime;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class HelloController {

    private final TimeSimulator timeSimulator;
    private final RequiredInternalTemperatureService requiredInternalTemperatureService;

    @GetMapping("/")
    public ResponseEntity<Double> helloWorld(@RequestParam LocalDateTime localDateTime, @RequestParam boolean isVacation){
        timeSimulator.set(localDateTime,isVacation);
        return  new ResponseEntity<>(requiredInternalTemperatureService.get(), HttpStatus.OK);
    }

}
