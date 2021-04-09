package pl.kurs.restapi.security;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import pl.kurs.restapi.controllers.dto.request.LoginUserRequest;
import pl.kurs.restapi.controllers.dto.request.RegisterUserRequest;
import pl.kurs.restapi.controllers.dto.response.JwtTokenResponse;

import javax.validation.Valid;

public interface MyUserDetailsService  {

    public MyUserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
    public JwtTokenResponse login(LoginUserRequest loginUserRequest) ;
    public String register(@Valid @RequestBody RegisterUserRequest registerUserRequest) throws Exception;

}
