package com.kproject.movie_booking;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.kproject.movie_booking.exceptions.EntityNotFoundException;
import com.kproject.movie_booking.models.Movie;
import com.kproject.movie_booking.repositories.MovieRepository;
import com.kproject.movie_booking.services.MovieServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class MovieServiceTest {
    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieServiceImpl movieService;

    @Test
    public void getMovieFromRepoTest() {
        when(movieRepository.findAll()).thenReturn(Arrays.asList(
                new Movie("Inception", "Testing")));
        List<Movie> result = movieService.getAllMovies();
        assertEquals("Inception", result.get(0).getTitle());
        assertEquals("Testing", result.get(0).getDescription());
    }

    @Test
    public void getMovieById_Found() {
        Movie movie = new Movie("Inception", "Testing");
        movie.setId(2L);
        when(movieRepository.findAll()).thenReturn(Arrays.asList(movie));
        when(movieRepository.findById(2L)).thenReturn(Optional.of(movie));
        Long id = movie.getId();
        Movie result = movieService.getMovieById(id);
        assertEquals(movie, result);
    }

    @Test(expected = EntityNotFoundException.class)
    public void getShowtimeById_NotFound() {
        when(movieRepository.findById(1L)).thenReturn(Optional.empty());
        movieService.getMovieById(1L);
    }
    
    @Test
    public void addMovieTest() {
        Movie movie = new Movie("Inception", "Testing");
        movie.setId(2L);
        when(movieRepository.findAll()).thenReturn(Arrays.asList(movie));
        when(movieRepository.findById(2L)).thenReturn(Optional.of(movie));
        movieService.saveMovie(movie);
        verify(movieRepository, times(1)).save(movie);
    }

    @Test
    public void deleteMovieTest() {
        Movie movie = new Movie("Inception", "Testing");
        movie.setId(2L);
        when(movieRepository.findAll()).thenReturn(Arrays.asList(movie));
        when(movieRepository.findById(2L)).thenReturn(Optional.of(movie));
        movieService.deleteMovie(2L);
        verify(movieRepository, times(1)).delete(movie);
    }

    @Test(expected = EntityNotFoundException.class)
    public void deleteMovie_NotFound() {
        when(movieRepository.findById(1L)).thenReturn(Optional.empty());

        movieService.deleteMovie(1L);
    }
}
