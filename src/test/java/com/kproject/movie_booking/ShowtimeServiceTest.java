package com.kproject.movie_booking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
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
import com.kproject.movie_booking.models.Showtime;
import com.kproject.movie_booking.repositories.ShowtimeRepository;
import com.kproject.movie_booking.services.ShowtimeServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ShowtimeServiceTest {
    @Mock
    private ShowtimeRepository showtimeRepository;

    @InjectMocks
    private ShowtimeServiceImpl showtimeServiceImpl;

    @Test
    public void getAllShowtimes() {
        Movie movie = new Movie();
        movie.setId(1L);
        movie.setTitle("Test");
        Showtime showtime = new Showtime();
        showtime.setMovie(movie);
        showtime.setShowtime(LocalDate.of(2025, 5, 10));
        Showtime showtime2 = new Showtime();
        showtime2.setMovie(movie);
        showtime2.setShowtime(LocalDate.of(2025, 6, 10));
        List<Showtime> showtimes = Arrays.asList(showtime, showtime2);
        when(showtimeRepository.findAll()).thenReturn(showtimes);
        List<Showtime> result = showtimeServiceImpl.getAllShowtimes();

        assertEquals(2, result.size());
        verify(showtimeRepository, times(1)).findAll();
    }

    @Test
    public void getShowtimeById() {
        Movie movie = new Movie();
        movie.setId(1L);
        movie.setTitle("Test");
        Showtime showtime = new Showtime();
        showtime.setMovie(movie);
        showtime.setShowtime(LocalDate.of(2025, 5, 10));
        when(showtimeRepository.findById(1L)).thenReturn(Optional.of(showtime));

        Showtime result = showtimeServiceImpl.getShowtimeById(1L);
        assertEquals(showtime, result);
        verify(showtimeRepository, times(1)).findById(1L);
    }

    @Test(expected = EntityNotFoundException.class)
    public void getShowtimeById_NotFound() {
        when(showtimeRepository.findById(1L)).thenReturn(Optional.empty());
        showtimeServiceImpl.getShowtimeById(1L);
    }

    @Test
    public void deleteShowtime_Success() {
        Movie movie = new Movie();
        movie.setId(1L);
        movie.setTitle("Test");
        Showtime showtime = new Showtime();
        showtime.setMovie(movie);
        showtime.setShowtime(LocalDate.of(2025, 5, 10));

        when(showtimeRepository.findById(1L)).thenReturn(Optional.of(showtime));
        doNothing().when(showtimeRepository).delete(showtime);

        showtimeServiceImpl.deleteShowtime(1L);

        verify(showtimeRepository, times(1)).delete(showtime);
    }

    @Test(expected = EntityNotFoundException.class)
    public void deleteShowtime_NotFound() {
        when(showtimeRepository.findById(1L)).thenReturn(Optional.empty());
        showtimeServiceImpl.deleteShowtime(1L);
    }
}
