package com.kproject.movie_booking.services;

import java.util.List;

import com.kproject.movie_booking.models.Movie;

public interface MovieService {
    Movie saveMovie(Movie movie); // Thêm phim mới

    List<Movie> getAllMovies(); // Lấy danh sách tất cả phim

    Movie getMovieById(Long id); // Tìm phim theo ID

    Movie updateMovie(Long id, Movie movieDetails); // Cập nhật phim

    void deleteMovie(Long id); // Xóa phim
}
