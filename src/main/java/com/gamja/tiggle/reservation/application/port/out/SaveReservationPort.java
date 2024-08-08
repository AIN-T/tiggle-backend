package com.gamja.tiggle.reservation.application.port.out;

import com.gamja.tiggle.reservation.domain.Reservation;

public interface SaveReservationPort {

    Long save(Reservation reservation);
    void update(Reservation reservation);

}
