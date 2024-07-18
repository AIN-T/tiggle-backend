package com.gamja.tiggle.seat.domain;

import com.gamja.tiggle.grade.domain.Grade;
import com.gamja.tiggle.location.domain.Location;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Seat {

    private Long id;
    private Location location;
    private Grade grade;
    private String section;
    private int seatNumber;
}
