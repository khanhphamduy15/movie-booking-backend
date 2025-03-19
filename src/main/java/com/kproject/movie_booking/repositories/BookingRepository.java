package com.kproject.movie_booking.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kproject.movie_booking.models.Booking;

public interface BookingRepository extends JpaRepository<Booking,Long>{
    Optional<List<Booking>> findByUserId(Long userId);
}
