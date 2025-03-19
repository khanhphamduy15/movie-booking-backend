package com.kproject.movie_booking.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kproject.movie_booking.models.BookingDetail;

public interface BookingDetailRepository extends JpaRepository<BookingDetail, Long> {
    Optional<List<BookingDetail>> findByBookingId(Long bookingId);
}
