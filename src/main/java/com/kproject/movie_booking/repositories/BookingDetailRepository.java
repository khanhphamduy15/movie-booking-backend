package com.kproject.movie_booking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kproject.movie_booking.models.BookingDetail;

public interface BookingDetailRepository extends JpaRepository<BookingDetail, Long> {

}
