package com.gamja.tiggle.reservation.domain;

import com.gamja.tiggle.program.domain.Location;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Seat {

    private Long id;
    private Location location;
    private Grade grade;
    private Long sectionId;
    private int seatNumber;
}
