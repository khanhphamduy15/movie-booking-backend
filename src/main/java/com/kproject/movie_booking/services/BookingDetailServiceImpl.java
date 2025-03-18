package com.kproject.movie_booking.services;

import java.util.List;
import java.util.Optional;

import com.kproject.movie_booking.exceptions.EntityNotFoundException;
import com.kproject.movie_booking.models.BookingDetail;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BookingDetailServiceImpl implements BookingDetailService {

    private BookingDetailService bookingDetailService;

    @Override
    public BookingDetail addBookingDetail(BookingDetail bookingDetail) {
        return bookingDetailService.addBookingDetail(bookingDetail);
    }

    @Override
    public List<BookingDetail> getBookingDetailsByBookingId(Long bookingId) {
        return bookingDetailService.getBookingDetailsByBookingId(bookingId);
    }

    @Override
    public Optional<BookingDetail> getBookingDetailById(Long id) {
        return bookingDetailService.getBookingDetailById(id);
    }

    @Override
    public void deleteBookingDetail(Long id) {
        bookingDetailService.deleteBookingDetail(id);
    }

    static BookingDetail unwrapBookingDetail(Optional<BookingDetail> entity, Long id) {
        if (entity.isPresent())
            return entity.get();
        else
            throw new EntityNotFoundException(id, BookingDetail.class);
    }
}
