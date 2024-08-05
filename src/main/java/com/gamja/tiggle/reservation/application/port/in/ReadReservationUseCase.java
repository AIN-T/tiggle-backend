package com.gamja.tiggle.reservation.application.port.in;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.reservation.domain.Reservation;

public interface ReadReservationUseCase {
    Reservation readReservation(ReadReservationCommand command) throws BaseException;
}
