package pl.kurs.restapi.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kurs.restapi.services.HelloService;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class HelloController {

    private final HelloService helloService;

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/")
    public ResponseEntity<String> helloWorld(){
        return  new ResponseEntity<>(helloService.helloWorld(), HttpStatus.OK);
    }

}
