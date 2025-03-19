package com.kproject.movie_booking.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kproject.movie_booking.exceptions.EntityNotFoundException;
import com.kproject.movie_booking.models.Booking;
import com.kproject.movie_booking.models.User;
import com.kproject.movie_booking.repositories.BookingRepository;
import com.kproject.movie_booking.repositories.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BookingServiceImpl implements BookingService {

    private BookingRepository bookingRepository;
    private UserRepository userRepository;
    
    @Override
    public Booking createBooking(Booking booking, Long userId) {
        User user = UserServiceImpl.unwrapUser(userRepository.findById(userId), userId);
        booking.setUser(user);
        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getBookingsByUser(Long userId) {
        return (List<Booking>) bookingRepository.findByUserId(userId);
    }

    @Override
    public Booking getBookingById(Long bookingId) {
        Optional<Booking> booking = bookingRepository.findById(bookingId);
        return unwrapBooking(booking, bookingId);
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
