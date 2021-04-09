package pl.kurs.restapi.controllers.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@AllArgsConstructor
public class RegisterUserRequest {
    @Size(min = 3, max = 20)
    private String username;
    @Size(max = 50)
    @Email
    private String email;
    @Size(min = 6, max = 40)
    private String password;

}
