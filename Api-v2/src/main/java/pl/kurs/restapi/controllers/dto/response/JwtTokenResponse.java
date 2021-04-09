package pl.kurs.restapi.controllers.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@AllArgsConstructor
public class JwtTokenResponse {
    private String token;
    private final String type = "Bearer";
    private String id;
    private String username;
    private String email;
    private List<String> roles;
}
