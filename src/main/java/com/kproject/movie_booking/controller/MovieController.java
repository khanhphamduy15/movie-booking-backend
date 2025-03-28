package com.kproject.movie_booking.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kproject.movie_booking.models.Movie;
import com.kproject.movie_booking.services.MovieService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@AllArgsConstructor
@RequestMapping("/movie")
public class MovieController {
    private MovieService movieService;

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovie(@PathVariable Long id) {
        return new ResponseEntity<>(movieService.getMovieById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Movie>> getCourses() {
        return new ResponseEntity<List<Movie>>(movieService.getAllMovies(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Movie> saveMovieEntity(@Valid @RequestBody Movie movie) {
        return new ResponseEntity<>(movieService.saveMovie(movie),HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@Valid @RequestBody Movie movie, @PathVariable Long id) {
        return new ResponseEntity<>(movieService.updateMovie(id, movie),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteMovie(@PathVariable Long id){
        movieService.deleteMovie(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
