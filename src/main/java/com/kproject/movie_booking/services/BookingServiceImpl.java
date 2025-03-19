package com.kproject.movie_booking.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kproject.movie_booking.exceptions.EntityNotFoundException;
import com.kproject.movie_booking.models.Booking;
import com.kproject.movie_booking.repositories.BookingRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BookingServiceImpl implements BookingService {

    private BookingRepository bookingRepository;
    @Override
    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public Optional<List<Booking>> getBookingsByUser(Long userId) {
        return (Optional<List<Booking>>) bookingRepository.findByUserId(userId);
    }

    @Override
    public Optional<Booking> getBookingById(Long bookingId) {
        return bookingRepository.findById(bookingId);
    }

    @Override
    public void cancelBooking(Long bookingId) {
        Optional<Booking> booking = bookingRepository.findById(bookingId);
        Booking unwrappedBooking = unwrapBooking(booking, bookingId);
        bookingRepository.delete(unwrappedBooking);
    }

    static Booking unwrapBooking(Optional<Booking> entity, Long id) {
        if (entity.isPresent())
            return entity.get();
        else
            throw new EntityNotFoundException(id, Booking.class);
    }

}
