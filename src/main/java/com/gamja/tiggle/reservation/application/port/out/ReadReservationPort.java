package com.gamja.tiggle.reservation.application.port.out;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.reservation.adapter.out.persistence.Entity.ReservationEntity;
import com.gamja.tiggle.reservation.domain.Reservation;


public interface ReadReservationPort {
    ReservationEntity read(Long reservationId) throws BaseException;
    Reservation readReservation(Reservation reservation) throws  BaseException;
}
