package dev.danvega.jwt.controller;

import dev.danvega.jwt.dto.LoginDto;
import dev.danvega.jwt.dto.RegisterDto;
import dev.danvega.jwt.models.Role;
import dev.danvega.jwt.models.UserEntity;
import dev.danvega.jwt.repository.RolesRepository;
import dev.danvega.jwt.repository.UserRepository;
import dev.danvega.jwt.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private final TokenService tokenService;


    public String token(Authentication authentication) {
        return tokenService.generateToken(authentication);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(),loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return token(authentication);
    }

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RolesRepository rolesRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(TokenService tokenService, AuthenticationManager authenticationManager,
                          UserRepository userRepository,
                          RolesRepository rolesRepository,
                          PasswordEncoder passwordEncoder)
    {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register (@RequestBody RegisterDto registerDto){

        logger.info(registerDto.toString());

        if(userRepository.existsByUsername(registerDto.getUsername())){
            return new ResponseEntity<>("username is taken", HttpStatus.BAD_REQUEST);
        }else{
            UserEntity user = new UserEntity();
            user.setUsername(registerDto.getUsername());
            user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

            Role roles = rolesRepository.findByName("USER").get();
            user.setRoles(Collections.singletonList(roles));

            userRepository.save(user);

            return new ResponseEntity<>("User Registered", HttpStatus.OK);
        }

    }


}
