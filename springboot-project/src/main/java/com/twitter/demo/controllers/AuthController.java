package com.twitter.demo.controllers;

import com.twitter.demo.entities.User;
import com.twitter.demo.entities.dto.JwtResponse;
import com.twitter.demo.entities.dto.LoginDto;
import com.twitter.demo.jwt.JwtUtils;
import com.twitter.demo.repositories.UserRepository;
import com.twitter.demo.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {


    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        String token = jwtService.login(loginDto);
        JwtResponse response = new JwtResponse(token, "Login exitoso");
        return ResponseEntity.ok(response);
    }
  }

