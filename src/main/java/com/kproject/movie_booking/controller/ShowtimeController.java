package com.kproject.movie_booking.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.kproject.movie_booking.models.Showtime;
import com.kproject.movie_booking.services.ShowtimeService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@AllArgsConstructor
@RequestMapping("/showtime")
public class ShowtimeController {

    private ShowtimeService showtimeService;

    @GetMapping("/all")
    public ResponseEntity<List<Showtime>> getAllShowtimes() {
        return new ResponseEntity<>(showtimeService.getAllShowtimes(), HttpStatus.OK);
    }

    @GetMapping("/{showtimeId}")
    public ResponseEntity<Showtime> getShowtimeById(@PathVariable Long showtimeId) {
        return new ResponseEntity<>(showtimeService.getShowtimeById(showtimeId), HttpStatus.OK);
    }

    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<Showtime>> getShowtimesByMovieId(@PathVariable Long movieId) {
        return new ResponseEntity<>(showtimeService.getShowtimesByMovie(movieId), HttpStatus.OK);
    }

    @PostMapping("/movie/{movieId}")
    public ResponseEntity<Showtime> createShowtime(@Valid @RequestBody Showtime showtime, @PathVariable Long movieId) {
        showtimeService.addShowtime(showtime, movieId); 
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{showtimeId}")
    public ResponseEntity<Showtime> deleteShowtimeById(@PathVariable Long showtimeId) {
        showtimeService.deleteShowtime(showtimeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
