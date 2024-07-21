package com.gamja.tiggle.reservation.application.port.in;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.reservation.domain.Seat;

import java.util.List;

public interface GetAvailableSeatUseCase {
    List<Seat> getAvailableSeat(GetAvailableSeatCommand command) throws BaseException;
}
