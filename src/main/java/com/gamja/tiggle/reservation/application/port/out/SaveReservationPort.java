package com.gamja.tiggle.reservation.application.port.out;

import com.gamja.tiggle.reservation.domain.Reservation;

public interface SaveReservationPort {

    void save(Reservation reservation);
}
