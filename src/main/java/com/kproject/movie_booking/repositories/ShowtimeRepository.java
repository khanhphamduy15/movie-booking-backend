package com.kproject.movie_booking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kproject.movie_booking.models.Showtime;

public interface ShowtimeRepository extends JpaRepository<Showtime,Long>{
    
}
