package com.kproject.movie_booking.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kproject.movie_booking.exceptions.EntityNotFoundException;
import com.kproject.movie_booking.models.Movie;
import com.kproject.movie_booking.models.Showtime;
import com.kproject.movie_booking.repositories.MovieRepository;
import com.kproject.movie_booking.repositories.ShowtimeRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ShowtimeServiceImpl implements ShowtimeService {

    private ShowtimeRepository showtimeRepository;
    private MovieRepository movieRepository;

    @Override
    public Showtime addShowtime(Showtime showtime, Long movieId) {
        Movie movie = MovieServiceImpl.unwrapMovie(movieRepository.findById(movieId), movieId);
        showtime.setMovie(movie);
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
    public Showtime getShowtimeById(Long showtimeId) {
        Optional<Showtime> showtime = showtimeRepository.findById(showtimeId);
        return unwrapShowtime(showtime, showtimeId);
    }

    @Override
    public void deleteShowtime(Long showtimeId) {
        showtimeRepository.deleteById(showtimeId);
    }

    static Showtime unwrapShowtime(Optional<Showtime> entity, Long id) {
        if (entity.isPresent())
            return entity.get();
        else
            throw new EntityNotFoundException(id, Showtime.class);
    }

}
