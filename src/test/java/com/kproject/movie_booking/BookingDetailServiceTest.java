package com.kproject.movie_booking;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
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
import static org.mockito.Mockito.*;

import com.kproject.movie_booking.exceptions.EntityNotFoundException;
import com.kproject.movie_booking.models.Booking;
import com.kproject.movie_booking.models.BookingDetail;
import com.kproject.movie_booking.repositories.BookingDetailRepository;
import com.kproject.movie_booking.repositories.BookingRepository;
import com.kproject.movie_booking.services.BookingDetailServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class BookingDetailServiceTest {

    @InjectMocks
    private BookingDetailServiceImpl bookingDetailService;

    @Mock
    private BookingDetailRepository bookingDetailRepository;

    @Mock
    private BookingRepository bookingRepository;

    @Test
    public void getBookingDetailsByBookingId_Found() {
        Long bookingId = 1L;
        Booking booking = new Booking();
        booking.setId(bookingId);

        BookingDetail bookingDetail1 = new BookingDetail();
        bookingDetail1.setId(1L);
        bookingDetail1.setPrice(50000);
        bookingDetail1.setSeatNum("A1");
        bookingDetail1.setBooking(booking);
        BookingDetail bookingDetail2 = new BookingDetail();
        bookingDetail2.setId(2L);
        bookingDetail2.setPrice(150000);
        bookingDetail2.setSeatNum("A2");
        bookingDetail2.setBooking(booking);


        List<BookingDetail> details = Arrays.asList(bookingDetail1, bookingDetail2);

        when(bookingRepository.findById(bookingId)).thenReturn(Optional.of(booking));
        when(bookingDetailRepository.findByBookingId(bookingId)).thenReturn(details);

        List<BookingDetail> result = bookingDetailService.getBookingDetailsByBookingId(bookingId);

        assertEquals(2, result.size());
        verify(bookingDetailRepository, times(1)).findByBookingId(bookingId);
    }

    @Test(expected = EntityNotFoundException.class)
    public void getBookingDetailsByBookingId_NotFound() {
        Long bookingId = 99L;
        when(bookingRepository.findById(bookingId)).thenReturn(Optional.empty());
        bookingDetailService.getBookingDetailsByBookingId(bookingId);
    }

    @Test
    public void addBookingDetail_Success() {
        Long bookingId = 1L;
        Booking booking = new Booking();
        booking.setId(bookingId);

        BookingDetail bookingDetail = new BookingDetail();
        bookingDetail.setId(1L);
        bookingDetail.setPrice(50000);
        bookingDetail.setSeatNum("A1");
        bookingDetail.setBooking(booking);

        booking.addBookingDetail(bookingDetail);

        when(bookingRepository.findById(bookingId)).thenReturn(Optional.of(booking));
        when(bookingDetailRepository.save(any(BookingDetail.class))).thenReturn(bookingDetail);
        BookingDetail savedDetail = bookingDetailService.addBookingDetail(bookingDetail, bookingId);

        assertNotNull(savedDetail);
        verify(bookingDetailRepository, times(1)).save(bookingDetail);
    }
}
