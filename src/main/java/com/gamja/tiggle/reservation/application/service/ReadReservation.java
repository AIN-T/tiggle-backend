package com.gamja.tiggle.reservation.application.service;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.annotation.UseCase;
import com.gamja.tiggle.reservation.application.port.in.ReadReservationCommand;
import com.gamja.tiggle.reservation.application.port.in.ReadReservationUseCase;
import com.gamja.tiggle.reservation.application.port.out.ReadReservationPort;
import com.gamja.tiggle.reservation.domain.Reservation;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class ReadReservation implements ReadReservationUseCase {
    private final ReadReservationPort readReservationPort;

    @Override
    public Reservation readReservation(ReadReservationCommand command) throws BaseException {
        Reservation reservation = Reservation.builder()
                .id(command.getReservationId())
                .build();
        return readReservationPort.readReservation(reservation);
    }
}
