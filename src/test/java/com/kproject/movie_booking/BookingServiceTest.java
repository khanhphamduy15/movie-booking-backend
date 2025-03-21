package com.kproject.movie_booking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.kproject.movie_booking.models.Booking;
import com.kproject.movie_booking.models.Showtime;
import com.kproject.movie_booking.models.User;
import com.kproject.movie_booking.repositories.BookingRepository;
import com.kproject.movie_booking.services.BookingServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class BookingServiceTest {
    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private BookingServiceImpl bookingServiceImpl;

    @Test
    public void getAllBookings() {
        Booking booking = new Booking();
        User user = new User();
        user.setId(2L);
        booking.setId(1L);
        booking.setUser(user);
        when(bookingRepository.findAll()).thenReturn(Arrays.asList(
                booking));
        List<Booking> result = bookingServiceImpl.getAllBookings();
        assertEquals(1, result.get(0).getId());
        assertEquals(2, result.get(0).getUser().getId());
    }

    @Test
    public void addBookingTest() {
        Booking booking = new Booking();
        User user = new User();
        Showtime showtime = new Showtime();
        user.setId(2L);
        showtime.setId(1L);
        booking.setId(1L);
        booking.setUser(user);
        booking.setShowtime(showtime);

        when(bookingRepository.findAll()).thenReturn(Arrays.asList(
                booking));
        bookingRepository.save(booking);
        verify(bookingRepository, times(1)).save(booking);
    }

    @Test
    public void cancelBookingTest() {
        Booking booking = new Booking();
        User user = new User();
        Showtime showtime = new Showtime();
        
        user.setId(2L);
        showtime.setId(1L);
        booking.setId(10L);
        booking.setUser(user);
        booking.setShowtime(showtime);
        booking.setStatus("PENDING");
    
        when(bookingRepository.findById(10L)).thenReturn(Optional.of(booking));
    
        bookingServiceImpl.cancelBooking(10L);
    
        assertEquals("CANCELLED", booking.getStatus()); 
        verify(bookingRepository, times(1)).save(booking);
    }
    
}
