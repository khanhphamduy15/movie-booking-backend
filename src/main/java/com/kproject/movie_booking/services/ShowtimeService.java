package com.kproject.movie_booking.services;

import java.util.List;

import com.kproject.movie_booking.models.Showtime;

public interface ShowtimeService {
    Showtime addShowtime(Showtime showtime, Long movieId); // Thêm suất chiếu

    List<Showtime> getAllShowtimes(); // Lấy danh sách suất chiếu

    List<Showtime> getShowtimesByMovie(Long movieId); // Lấy suất chiếu theo phim

    Showtime getShowtimeById(Long showtimeId); // Lấy chi tiết suất chiếu

    void deleteShowtime(Long showtimeId); // Xóa suất chiếu 
}
