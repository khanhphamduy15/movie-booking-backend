package com.kproject.movie_booking.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kproject.movie_booking.models.BookingDetail;

public interface BookingDetailRepository extends JpaRepository<BookingDetail, Long> {
    List<BookingDetail> findByBookingId(Long bookingId);
}
