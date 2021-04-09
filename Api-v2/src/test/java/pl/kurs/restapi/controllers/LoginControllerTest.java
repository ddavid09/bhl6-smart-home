package pl.kurs.restapi.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import pl.kurs.restapi.controllers.dto.request.RegisterUserRequest;
import pl.kurs.restapi.controllers.dto.response.JwtTokenResponse;
import pl.kurs.restapi.security.MyUserDetailsServiceImpl;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private MyUserDetailsServiceImpl userDetailsService;
    @Autowired
    private ObjectMapper objectMapper;

    @Transactional
    @Test
    void shouldRegisterLoginAndGetContent() throws Exception {
        String result = userDetailsService.register(
                new RegisterUserRequest(
                        "test213",
                        "test213",
                        "test213"
                )
        );
        assertEquals("User registered successfully!",result);

        MvcResult login = mockMvc.perform(post("/login")
                .content("{\"username\": \"test213\", \"password\": \"test213\"}")
                .contentType("application/json")
        )
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();

        JwtTokenResponse jwtTokenResponse = objectMapper.readValue(login.getResponse().getContentAsString(), JwtTokenResponse.class);
        String token = jwtTokenResponse.getToken();

        mockMvc.perform(get("/posts")
                .header("Authorization", token)
        )
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(content().string(Matchers.notNullValue()));

        mockMvc.perform(get("/posts"))
                .andDo(print())
                .andExpect(status().is(401));

    }
}