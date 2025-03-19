package com.kproject.movie_booking.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kproject.movie_booking.models.Showtime;

public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {
    List<Showtime> findByMovieId(Long movieId);
}
