package com.spring.blog.controller;

import com.spring.blog.entity.JwtResponse;
import com.spring.blog.payload.LoginDto;
import com.spring.blog.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {


    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<JwtResponse> login(@RequestBody LoginDto logindto) {
        String token = authService.login(logindto);

        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setJwtToken(token);

        return ResponseEntity.ok(jwtResponse);
    }


        @ExceptionHandler(BadCredentialsException.class)
        public String exceptionHandler() {
            return "Credentials Invalid !!";
        }

    }


