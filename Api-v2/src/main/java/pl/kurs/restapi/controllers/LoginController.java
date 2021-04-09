package pl.kurs.restapi.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.kurs.restapi.controllers.dto.request.LoginUserRequest;
import pl.kurs.restapi.controllers.dto.request.RegisterUserRequest;
import pl.kurs.restapi.controllers.dto.response.JwtTokenResponse;
import pl.kurs.restapi.security.JwtUtils;
import pl.kurs.restapi.security.MyUserDetailsServiceImpl;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class LoginController {

    private final MyUserDetailsServiceImpl userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginUserRequest loginUserRequest) throws Exception{
        try{
            final JwtTokenResponse jwt = userDetailsService.login(loginUserRequest);
            return new ResponseEntity<>(jwt,HttpStatus.OK);
        }
        catch (BadCredentialsException e){
            return new ResponseEntity<>(null,HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterUserRequest registerUserRequest ) {
        try {
            return new ResponseEntity<>(userDetailsService.register(registerUserRequest),HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
        }
    }
}

