package com.kproject.movie_booking.services;

import java.util.List;
import java.util.Optional;

import com.kproject.movie_booking.models.Booking;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BookingServiceImpl implements BookingService {

    private BookingService bookingService;

    @Override
    public Booking createBooking(Booking booking) {
        return bookingService.createBooking(booking);
    }

    @Override
    public List<Booking> getBookingsByUser(Long userId) {
        return (List<Booking>) bookingService.getBookingsByUser(userId);
    }

    @Override
    public Optional<Booking> getBookingById(Long bookingId) {
        return bookingService.getBookingById(bookingId);
    }

    @Override
    public void cancelBooking(Long bookingId) {
        bookingService.cancelBooking(bookingId);
    }

}
