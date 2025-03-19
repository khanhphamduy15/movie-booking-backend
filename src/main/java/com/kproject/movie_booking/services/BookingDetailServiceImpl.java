package com.kproject.movie_booking.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kproject.movie_booking.exceptions.EntityNotFoundException;
import com.kproject.movie_booking.models.BookingDetail;
import com.kproject.movie_booking.repositories.BookingDetailRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BookingDetailServiceImpl implements BookingDetailService {
    private BookingDetailRepository bookingDetailRepository;

    @Override
    public BookingDetail addBookingDetail(BookingDetail bookingDetail) {
        return bookingDetailRepository.save(bookingDetail);
    }

    @Override
    public Optional<List<BookingDetail>> getBookingDetailsByBookingId(Long bookingId) {
        return bookingDetailRepository.findByBookingId(bookingId);
    }

    @Override
    public Optional<BookingDetail> getBookingDetailById(Long id) {
        return bookingDetailRepository.findById(id);
    }

    @Override
    public void deleteBookingDetail(Long id) {
        bookingDetailRepository.deleteById(id);
    }

    static BookingDetail unwrapBookingDetail(Optional<BookingDetail> entity, Long id) {
        if (entity.isPresent())
            return entity.get();
        else
            throw new EntityNotFoundException(id, BookingDetail.class);
    }
}
