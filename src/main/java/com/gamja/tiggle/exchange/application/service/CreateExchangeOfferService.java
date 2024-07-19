package com.gamja.tiggle.exchange.application.service;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.annotation.UseCase;
import com.gamja.tiggle.exchange.application.port.in.CreateExchangeOfferCommand;
import com.gamja.tiggle.exchange.application.port.in.CreateExchangeOfferUseCase;
import com.gamja.tiggle.exchange.application.port.out.ExchangePort;
import com.gamja.tiggle.exchange.domain.Exchange;
import com.gamja.tiggle.reservation.adapter.out.persistence.Entity.ReservationEntity;
import com.gamja.tiggle.reservation.application.port.out.ReadReservationPort;
import com.gamja.tiggle.reservation.application.port.out.SaveReservationPort;
import com.gamja.tiggle.reservation.domain.Reservation;
import com.gamja.tiggle.reservation.domain.type.ReservationType;
import com.gamja.tiggle.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@UseCase
@RequiredArgsConstructor
public class CreateExchangeOfferService implements CreateExchangeOfferUseCase {

    private final ExchangePort exchangePort;
    private final ReadReservationPort readReservationPort;
    private final SaveReservationPort saveReservationPort;

    @Override
    @Transactional
    public void create(CreateExchangeOfferCommand command) throws BaseException {
        ReservationEntity reservation1 = readReservationPort.read(Reservation.builder().id(command.getReservationId1()).build());
        ReservationEntity reservation2 = readReservationPort.read(Reservation.builder().id(command.getReservationId2()).build());

        String ticketNumber = UUID.randomUUID().toString();

//      TODO: 요청하는 유저의 새 티켓 발행 -> 상태는 false [보류 - 활성화 전]
        saveReservationPort.save(Reservation.builder()
//                .user(User.builder().id(reservation1.getUser().getId()).build())
                .programId(reservation2.getProgramEntity().getId())
                .seatId(reservation2.getSeatEntity().getId())
                .timesId(reservation2.getId())
                .ticketNumber(ticketNumber)
                .totalPrice(reservation2.getTotalPrice())
                .status(ReservationType.EXCHANGED)
                .requestLimit(5)
                .build());

//        새로 발행한 티켓 저장
        exchangePort.save(Exchange.builder()
                .reservationId1(Reservation.builder().id(reservation1.getId()).build())
                .reservationId2(Reservation.builder().id(reservation2.getId()).build())
                .isSuccess(false)
                .isWatch(false)
                .build());

//      유저 티켓의 횟수 -- update
        saveReservationPort.update(Reservation.builder()
                .id(reservation1.getId())
                .seatId(reservation1.getSeatEntity().getId())
                .programId(reservation1.getProgramEntity().getId())
                .timesId(reservation1.getTimesEntity().getId())
//                .user(User.builder().id(reservation1.getUser().getId()).build())
                .ticketNumber(reservation1.getTicketNumber())
                .payMethod(reservation1.getPayMethod())
                .totalPrice(reservation1.getTotalPrice())
                .status(ReservationType.EXCHANGED)
                .requestLimit(reservation1.getRequestLimit() - 1).build());
    }
}
