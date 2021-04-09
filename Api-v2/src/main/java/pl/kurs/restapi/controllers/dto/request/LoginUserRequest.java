package pl.kurs.restapi.controllers.dto.request;

import lombok.Data;

@Data
public class LoginUserRequest {
    private String username;
    private String password;
}
