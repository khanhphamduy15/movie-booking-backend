package com.kproject.movie_booking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kproject.movie_booking.models.Booking;

public interface BookingRepository extends JpaRepository<Booking,Long>{
    
}
