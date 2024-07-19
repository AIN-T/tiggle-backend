package com.gamja.tiggle.reservation.application.service;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.annotation.UseCase;
import com.gamja.tiggle.reservation.application.port.in.VerifySeatCommand;
import com.gamja.tiggle.reservation.application.port.in.VerifySeatUseCase;
import com.gamja.tiggle.reservation.application.port.out.SaveReservationPort;
import com.gamja.tiggle.reservation.application.port.out.VerifySeatPort;
import com.gamja.tiggle.reservation.domain.Reservation;
import com.gamja.tiggle.reservation.domain.Seat;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class VerifySeatService implements VerifySeatUseCase {

    private final VerifySeatPort verifySeatPort;
    private final SaveReservationPort saveReservationPort;

    @Override
    public void verifySeat(VerifySeatCommand command) throws BaseException {

        //좌석 검증
        Seat seat = Seat.builder().id(command.getSeatId()).build();
        verifySeatPort.verifySeat(seat);
        //검증했으면 예약 임시 저장
        // TODO 유저 추가하는데 USER 도메인 없어서 주석

        //

        Reservation reservation = from(command);
        saveReservationPort.save(reservation);

    }

    private static Reservation from(VerifySeatCommand command) {
        return Reservation.builder()
                .seatId(command.getSeatId())
                .timesId(command.getTimesId())
                .programId(command.getProgramId())
                .build();
    }
}
