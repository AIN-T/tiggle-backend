package com.gamja.tiggle.reservation.application.service;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.annotation.UseCase;
import com.gamja.tiggle.reservation.adapter.in.web.response.VerifySeatResponse;
import com.gamja.tiggle.reservation.application.port.in.VerifySeatCommand;
import com.gamja.tiggle.reservation.application.port.in.VerifySeatUseCase;
import com.gamja.tiggle.reservation.application.port.out.SaveReservationPort;
import com.gamja.tiggle.reservation.application.port.out.VerifySeatPort;
import com.gamja.tiggle.reservation.domain.Reservation;
import com.gamja.tiggle.reservation.domain.Seat;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@UseCase
@RequiredArgsConstructor
public class VerifySeatService implements VerifySeatUseCase {

    private final VerifySeatPort verifySeatPort;
    private final SaveReservationPort saveReservationPort;

    @Override
    public VerifySeatResponse verifySeat(VerifySeatCommand command) throws BaseException {

        //좌석 검증
        Seat seat = Seat.builder().id(command.getSeatId()).build();
        verifySeatPort.verifySeat(seat);
        // TODO 유저 추가

        //검증했으면 예약 임시 저장
        String ticketNumber = getTicketNumber();
        Reservation reservation = from(command, ticketNumber);
        Long id = saveReservationPort.save(reservation);

        return VerifySeatResponse.builder().reservationId(id).build();
    }

    private static String getTicketNumber() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String nowDate = LocalDateTime.now().format(formatter);
        String randomNumber = UUID.randomUUID().toString().substring(0, 4);
        return nowDate + "-" + randomNumber;
    }

    private static Reservation from(VerifySeatCommand command, String ticketNumber) {
        return Reservation.builder()
                .userId(command.getUserId())
                .seatId(command.getSeatId())
                .timesId(command.getTimesId())
                .programId(command.getProgramId())
                .ticketNumber(ticketNumber)
                .build();
    }
}
