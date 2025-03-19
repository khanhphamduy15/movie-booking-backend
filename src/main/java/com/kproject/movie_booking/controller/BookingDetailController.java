package com.kproject.movie_booking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kproject.movie_booking.services.BookingDetailService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@AllArgsConstructor
@RequestMapping("/booking-detail")
public class BookingDetailController {
    private BookingDetailService bookingDetailService;

    @PostMapping("path")
    public String postMethodName(@RequestBody String entity) {
        //TODO: process POST request
        
        return entity;
    }
    


    
}
