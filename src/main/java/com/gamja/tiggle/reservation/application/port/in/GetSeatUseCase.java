package com.gamja.tiggle.reservation.application.port.in;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.reservation.domain.Seat;

import java.util.List;

public interface GetSeatUseCase {
    List<Seat> getAvailableSeat(GetAvailableSeatCommand command) throws BaseException;

    List<Seat> getAllSeat(GetAllSeatCommand command) throws  BaseException;
}
