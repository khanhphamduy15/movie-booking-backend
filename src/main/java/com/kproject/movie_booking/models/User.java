package com.kproject.movie_booking.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false)
    @NonNull
    @NotBlank(message = "Email cannot be blank")
    private String email;

    @Column(name = "password", nullable = false)
    @NonNull
    @NotBlank(message = "Password cannot be blank")
    private String password;

    @Column(name = "fullname", nullable = false)
    @NonNull
    @NotBlank(message = "Fullname cannot be blank")
    private String fullname;

    @Column(name = "role")
    private String role;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Booking> booking;

}
