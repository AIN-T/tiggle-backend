package com.gamja.tiggle.reservation.application.service;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.BaseResponseStatus;
import com.gamja.tiggle.common.annotation.UseCase;
import com.gamja.tiggle.reservation.application.port.in.ReadReservationCommand;
import com.gamja.tiggle.reservation.application.port.in.ReadReservationUseCase;
import com.gamja.tiggle.reservation.application.port.out.ReadReservationPort;
import com.gamja.tiggle.reservation.domain.Reservation;
import com.gamja.tiggle.user.domain.User;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class ReadReservationService implements ReadReservationUseCase {
    private final ReadReservationPort readReservationPort;

    @Override
    public Reservation readReservation(ReadReservationCommand command) throws BaseException {
        Reservation reservation = Reservation.builder()
                .id(command.getReservationId())
                .build();
        return readReservationPort.readReservation(reservation);
    }

    @Override
    public List<Reservation> myRead(ReadReservationCommand command) throws BaseException {
        User user = command.getUser();
        if (user == null) {
            throw new BaseException(BaseResponseStatus.FAIL);
        }
        return readReservationPort.myRead(command);
    }
}
