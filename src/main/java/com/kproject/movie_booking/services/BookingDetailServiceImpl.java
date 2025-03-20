package com.kproject.movie_booking.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kproject.movie_booking.exceptions.EntityNotFoundException;
import com.kproject.movie_booking.models.Booking;
import com.kproject.movie_booking.models.BookingDetail;
import com.kproject.movie_booking.repositories.BookingDetailRepository;
import com.kproject.movie_booking.repositories.BookingRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BookingDetailServiceImpl implements BookingDetailService {
    private BookingDetailRepository bookingDetailRepository;
    private BookingRepository bookingRepository;

    @Override
    public BookingDetail addBookingDetail(BookingDetail bookingDetail, Long bookingId) {
        Booking booking = BookingServiceImpl.unwrapBooking(bookingRepository.findById(bookingId), bookingId);
        bookingDetail.setBooking(booking);
        booking.addBookingDetail(bookingDetail);
        return bookingDetailRepository.save(bookingDetail);
    }

    @Override
    public List<BookingDetail> getBookingDetailsByBookingId(Long bookingId) {
        List<BookingDetail> bookingDetails = bookingDetailRepository.findByBookingId(bookingId);
        if (bookingDetails.isEmpty()) {
            throw new EntityNotFoundException(bookingId, Booking.class);
        }
        return bookingDetails;
    }

    @Override
    public BookingDetail getBookingDetailById(Long id) {
        Optional<BookingDetail> bookingDetail = bookingDetailRepository.findById(id);
        return unwrapBookingDetail(bookingDetail, id);
    }

    @Override
    public void deleteBookingDetail(Long id) {
        Optional<BookingDetail> bookingDetail = bookingDetailRepository.findById(id);
        BookingDetail unwrapedBookingDetail = unwrapBookingDetail(bookingDetail, id);
        bookingDetailRepository.delete(unwrapedBookingDetail);
    }

    static BookingDetail unwrapBookingDetail(Optional<BookingDetail> entity, Long id) {
        if (entity.isPresent()) 
            return entity.get();
        else
            throw new EntityNotFoundException(id, BookingDetail.class);
    }
}
