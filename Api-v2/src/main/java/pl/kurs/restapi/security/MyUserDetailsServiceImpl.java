package pl.kurs.restapi.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.kurs.restapi.controllers.dto.request.LoginUserRequest;
import pl.kurs.restapi.controllers.dto.request.RegisterUserRequest;
import pl.kurs.restapi.controllers.dto.response.JwtTokenResponse;
import pl.kurs.restapi.entities.RoleEntity;
import pl.kurs.restapi.entities.UserEntity;
import pl.kurs.restapi.repository.RoleRepository;
import pl.kurs.restapi.repository.UserRepository;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MyUserDetailsServiceImpl implements MyUserDetailsService,UserDetailsService {

    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder encoder;


    @Override
    public MyUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return MyUserDetails.build(userEntity);
    }


    @Override
    public JwtTokenResponse login(LoginUserRequest loginUserRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUserRequest.getUsername(), loginUserRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();

        String jwt = jwtUtils.generateToken(userDetails);
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return new JwtTokenResponse(
                jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles);
    }

    @Override
    public String register(@Valid RegisterUserRequest registerUserRequest) throws Exception {
        if (userRepository.existsByUsername(registerUserRequest.getUsername())) {
            throw new Exception("Error: Username is already taken!");
        }

        if (userRepository.existsByEmail(registerUserRequest.getEmail())) {
            throw new Exception("Error: Email is already in use!");
        }

        Set<RoleEntity> roleEntities = new HashSet<>();
        RoleEntity userRoleEntity = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roleEntities.add(userRoleEntity);

        UserEntity userEntity = new UserEntity(
                registerUserRequest.getUsername(),
                registerUserRequest.getEmail(),
                encoder.encode(registerUserRequest.getPassword()),
                true,
                roleEntities
        );
        userRepository.save(userEntity);
        return "User registered successfully!";
    }

}
