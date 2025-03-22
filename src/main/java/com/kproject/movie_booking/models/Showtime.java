package com.kproject.movie_booking.models;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
@Table(name = "showtime")
public class Showtime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "show_time", nullable = false)
    @NonNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate showtime;

    @JsonIgnore
    @OneToMany(mappedBy = "showtime", cascade = CascadeType.ALL)
    private List<Booking> bookings;

    @ManyToOne(optional = false)
    @JoinColumn(name = "movie_id", referencedColumnName = "id", nullable = false)
    private Movie movie;
}
