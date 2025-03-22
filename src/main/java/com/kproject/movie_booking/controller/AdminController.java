package com.kproject.movie_booking.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kproject.movie_booking.models.User;
import com.kproject.movie_booking.services.UserService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;
    @PostMapping("/register")
    public ResponseEntity<User> getMethodName(@Valid @RequestBody User admin) {
        userService.registerAdmin(admin);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
}
