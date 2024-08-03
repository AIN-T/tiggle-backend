package com.gamja.tiggle.reservation.domain;

import com.gamja.tiggle.program.domain.Location;
import com.gamja.tiggle.reservation.domain.type.ReservationType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Seat {

    private Long id;
    private Location location;
    private Grade grade;
    private Long sectionId;

    private String row;
    private int seatNumber;
    private Boolean active;

    private ReservationType status;
    private Boolean available;
}
