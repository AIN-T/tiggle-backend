package com.gamja.tiggle.reservation.application.port.out;

import com.gamja.tiggle.reservation.domain.Seat;

import java.util.List;

public interface GetRemainedSeatPort {
    List<Seat> getRemainedSeat(Long programId, Long timesId, Long sectionId);
}
