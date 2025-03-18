package com.kproject.movie_booking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.kproject.movie_booking.models.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

}
