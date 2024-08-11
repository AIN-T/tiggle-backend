package com.gamja.tiggle.reservation.application.port.in;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.reservation.domain.Seat;

import java.util.List;

public interface GetSeatUseCase {
    List<Seat> getAvailableSeat(GetAvailableSeatCommand command) throws BaseException;

    List<List<Seat>> getAllSeat(GetAllSeatCommand command) throws BaseException;

    List<List<Seat>> getAllSeatWithEnable(GetAllSeatCommand command) throws BaseException;

    List<List<Seat>> getAllSeatWithEnableExchange(GetAllSeatCommand command) throws BaseException;

    List<List<Seat>> getAvailableExchangeSeat(GetAllSeatCommand command) throws BaseException;
}
