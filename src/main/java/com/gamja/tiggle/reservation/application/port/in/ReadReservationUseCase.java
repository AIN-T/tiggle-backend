package com.gamja.tiggle.reservation.application.port.in;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.reservation.domain.Reservation;

import java.util.List;

public interface ReadReservationUseCase {
    Reservation readReservation(ReadReservationCommand command) throws BaseException;
    Reservation readTemporaryReservation(ReadTemporaryReservationCommand command) throws BaseException;
    List<Reservation> myRead(ReadReservationCommand command) throws BaseException;
}
