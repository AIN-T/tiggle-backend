package com.gamja.tiggle.reservation.application.port.in;

import com.gamja.tiggle.reservation.domain.Seat;

import java.util.List;

public interface GetRemainedSeatUseCase {
    List<Seat> getRemainedSeat(GetRemainedSeatCommand command);
}
