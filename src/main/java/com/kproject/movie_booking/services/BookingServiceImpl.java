package com.kproject.movie_booking.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kproject.movie_booking.exceptions.BookingCancelledException;
import com.kproject.movie_booking.exceptions.EntityNotFoundException;
import com.kproject.movie_booking.models.Booking;
import com.kproject.movie_booking.models.Showtime;
import com.kproject.movie_booking.models.User;
import com.kproject.movie_booking.repositories.BookingRepository;
import com.kproject.movie_booking.repositories.ShowtimeRepository;
import com.kproject.movie_booking.repositories.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BookingServiceImpl implements BookingService {

    private BookingRepository bookingRepository;
    private UserRepository userRepository;
    private ShowtimeRepository showtimeRepository;

    @Override
    public Booking createBooking(Booking booking, Long userId, Long showtimeId) {
        User user = UserServiceImpl.unwrapUser(userRepository.findById(userId), userId);
        Showtime showtime = ShowtimeServiceImpl.unwrapShowtime(showtimeRepository.findById(showtimeId), showtimeId);
        booking.setUser(user);
        booking.setShowtime(showtime);
        booking.setStatus("PENDING");
        booking.setTotalPrice(0.0);
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
        String status = unwrappedBooking.getStatus();
        if (status.equals("PENDING")) {
            unwrappedBooking.setStatus("CANCELLED");
            bookingRepository.save(unwrappedBooking);
        }
        else throw new BookingCancelledException(bookingId, Booking.class);
    }

    static Booking unwrapBooking(Optional<Booking> entity, Long id) {
        if (entity.isPresent())
            return entity.get();
        else
            throw new EntityNotFoundException(id, Booking.class);
    }

    @Override
    public List<Booking> getAllBookings() {
        return (List<Booking>) bookingRepository.findAll();
    }

    @Override
    public void deleteBookingById(Long bookingId) {
        Optional<Booking> booking = bookingRepository.findById(bookingId);
        Booking unwrappedBooking = unwrapBooking(booking, bookingId);
        bookingRepository.delete(unwrappedBooking);
    }

}
