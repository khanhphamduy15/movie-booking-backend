package com.kproject.movie_booking.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kproject.movie_booking.models.Showtime;
import com.kproject.movie_booking.repositories.ShowtimeRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ShowtimeServiceImpl implements ShowtimeService {

    private ShowtimeRepository showtimeRepository;

    @Override
    public Showtime addShowtime(Showtime showtime) {
        return showtimeRepository.save(showtime);
    }

    @Override
    public List<Showtime> getAllShowtimes() {
        return showtimeRepository.findAll();
    }

    @Override
    public List<Showtime> getShowtimesByMovie(Long movieId) {
        return (List<Showtime>) showtimeRepository.findByMovieId(movieId);
    }

    @Override
    public Optional<Showtime> getShowtimeById(Long showtimeId) {
        return showtimeRepository.findById(showtimeId);
    }

    @Override
    public void deleteShowtime(Long showtimeId) {
        showtimeRepository.deleteById(showtimeId);
    }

}
