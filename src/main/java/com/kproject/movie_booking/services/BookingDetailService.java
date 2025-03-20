package com.kproject.movie_booking.services;

import java.util.List;

import com.kproject.movie_booking.models.BookingDetail;

public interface BookingDetailService {
    BookingDetail addBookingDetail(BookingDetail bookingDetail, Long bookingId); // Thêm chi tiết đặt vé

    List<BookingDetail> getBookingDetailsByBookingId(Long bookingId); // Lấy danh sách vé theo Booking

    BookingDetail getBookingDetailById(Long id); // Lấy thông tin chi tiết vé

    void deleteBookingDetail(Long id); // Xóa chi tiết đặt vé
}
