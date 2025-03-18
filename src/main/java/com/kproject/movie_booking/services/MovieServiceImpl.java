package com.kproject.movie_booking.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kproject.movie_booking.exceptions.EntityNotFoundException;
import com.kproject.movie_booking.models.Movie;
import com.kproject.movie_booking.repositories.MovieRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class MovieServiceImpl implements MovieService {

    private MovieRepository movieRepository;

    @Override
    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public List<Movie> getAllMovies() {
        return (List<Movie>) movieRepository.findAll();
    }

    @Override
    public Movie getMovieById(Long id) {
        Optional<Movie> movie = movieRepository.findById(id);
        return unwrapMovie(movie, id);
    }

    @Override
    public Movie updateMovie(Long id, Movie movieDetails) {
        Optional<Movie> movie = movieRepository.findById(id);
        Movie unwrappedMovie = unwrapMovie(movie, id);
        unwrappedMovie.setDescription(movieDetails.getDescription());
        unwrappedMovie.setTitle(movieDetails.getTitle());
        unwrappedMovie.setReleaseDate(movieDetails.getReleaseDate());
        unwrappedMovie.setDuration(movieDetails.getDuration());
        unwrappedMovie.setShowtimes(movieDetails.getShowtimes());
        return movieRepository.save(unwrappedMovie);
    }

    @Override
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }

    static Movie unwrapMovie(Optional<Movie> entity, Long id) {
        if (entity.isPresent())
            return entity.get();
        else
            throw new EntityNotFoundException(id, Movie.class);
    }
}
