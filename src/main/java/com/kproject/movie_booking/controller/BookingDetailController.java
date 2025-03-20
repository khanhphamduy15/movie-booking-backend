package com.kproject.movie_booking.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kproject.movie_booking.models.BookingDetail;
import com.kproject.movie_booking.services.BookingDetailService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/booking-detail")
public class BookingDetailController {
    private BookingDetailService bookingDetailService;

    @PostMapping("/booking/{bookingId}")
    public ResponseEntity<BookingDetail> saveBookingDetail(@Valid @RequestBody BookingDetail bookingDetail,
            @PathVariable Long bookingId) {
        return new ResponseEntity<>(bookingDetailService.addBookingDetail(bookingDetail, bookingId),
                HttpStatus.CREATED);
    }

    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<List<BookingDetail>> getBookingDetailsByBookingId(@PathVariable Long bookingId) {
        return new ResponseEntity<>(bookingDetailService.getBookingDetailsByBookingId(bookingId), HttpStatus.OK);
    }

    @GetMapping("/{bookingDetailId}")
    public ResponseEntity<BookingDetail> getBookingDetailsById(@PathVariable Long bookingDetailId) {
        return new ResponseEntity<>(bookingDetailService.getBookingDetailById(bookingDetailId), HttpStatus.OK);
    }

    @DeleteMapping("/{bookingDetailId}")
    public ResponseEntity<BookingDetail> deleteBookingDetailsById(@PathVariable Long bookingDetailId) {
        bookingDetailService.deleteBookingDetail(bookingDetailId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
