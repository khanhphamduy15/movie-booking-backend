package com.kproject.movie_booking.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kproject.movie_booking.models.Booking;
import com.kproject.movie_booking.services.BookingService;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@AllArgsConstructor
@RequestMapping("/booking")
public class BookingController {
    private BookingService bookingService;
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Booking>> getBookingsByUserId(@RequestParam Long userId) {
        return new ResponseEntity<List<Booking>>(bookingService.getBookingsByUser(userId),HttpStatus.OK);
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<Booking> saveBookingByUserId(@RequestBody Booking booking, @PathVariable Long userId) {
        return new ResponseEntity<Booking>(bookingService.createBooking(booking, userId),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById( @PathVariable Long id) {
        return new ResponseEntity<Booking>(bookingService.getBookingById(id),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Booking> cancelBookingById( @PathVariable Long id) {
        bookingService.cancelBooking(id);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    
    
    
    
}
