package com.kproject.movie_booking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kproject.movie_booking.models.User;

public interface UserRepository  extends JpaRepository<User,Long>{
    
}
