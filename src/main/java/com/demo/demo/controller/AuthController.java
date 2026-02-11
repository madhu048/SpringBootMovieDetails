package com.demo.demo.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.demo.DTOclasses.LoginRequest;
import com.demo.demo.DTOclasses.LoginResponse;
import com.demo.demo.Utils.JWTUtil;

@RestController
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/auth/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        // Authenticate the user using authenticationManager
        // if authentication is successful, generate a JWT token using jwtUtil and
        // return it in the response
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        String token = jwtUtil.generateToken(authentication.getName());
        return new LoginResponse(token);
    }

}
