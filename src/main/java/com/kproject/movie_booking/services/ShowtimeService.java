package com.kproject.movie_booking.services;

import java.util.List;
import java.util.Optional;

import com.kproject.movie_booking.models.Showtime;

public interface ShowtimeService {
    Showtime addShowtime(Showtime showtime); // Thêm suất chiếu

    List<Showtime> getAllShowtimes(); // Lấy danh sách suất chiếu

    List<Showtime> getShowtimesByMovie(Long movieId); // Lấy suất chiếu theo phim

    Optional<Showtime> getShowtimeById(Long showtimeId); // Lấy chi tiết suất chiếu

    void deleteShowtime(Long showtimeId); // Xóa suất chiếu 
}
