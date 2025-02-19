package com.gamja.tiggle.exchange.application.service;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.BaseResponseStatus;
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
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
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
        ReservationEntity reservation1 = readReservationPort.read(command.getReservationId1());
        ReservationEntity reservation2 = readReservationPort.read(command.getReservationId2());

        if (!Objects.equals(command.getUser().getId(), reservation1.getUser().getId()))
            throw new BaseException(BaseResponseStatus.WRONG_EXCHANGE_OFFER);

        if (exchangePort.find(command))
            throw new BaseException(BaseResponseStatus.EXIST_EXCHANGE_OFFER);

        if (reservation1.getRequestLimit() < 1)
            throw new BaseException(BaseResponseStatus.NOT_REMAIN_EXCHANGE_OFFER);

        if (Objects.equals(reservation1.getUser().getId(), reservation2.getUser().getId()))
            throw new BaseException(BaseResponseStatus.WRONG_EXCHANGE_OFFER);

        if (!Objects.equals(reservation1.getProgramEntity().getId(), reservation2.getProgramEntity().getId()) || !Objects.equals(reservation1.getTimesEntity().getId(), reservation2.getTimesEntity().getId()))
            throw new BaseException(BaseResponseStatus.WRONG_EXCHANGE_OFFER);

        saveReservationPort.save(Reservation.builder()
                .userId(reservation1.getUser().getId())
                .programId(reservation2.getProgramEntity().getId())
                .seatId(reservation2.getSeatEntity().getId())
                .timesId(reservation2.getTimesEntity().getId())
                .ticketNumber(getTicketNumber())
                .totalPrice(reservation2.getTotalPrice())
                .status(ReservationType.IN_PROGRESS)
                .requestLimit(5)
                .build());

//        교환 내역 저장
        exchangePort.save(Exchange.builder()
                .reservationId1(Reservation.builder().id(reservation1.getId()).build())
                .reservationId2(Reservation.builder().id(reservation2.getId()).build())
                .isWatch(false)
                .build());

//      유저 티켓의 횟수 -- update
        saveReservationPort.update(Reservation.builder()
                .id(reservation1.getId())
                .userId(command.getUser().getId())
                .seatId(reservation1.getSeatEntity().getId())
                .programId(reservation1.getProgramEntity().getId())
                .timesId(reservation1.getTimesEntity().getId())
                .ticketNumber(reservation1.getTicketNumber())
                .totalPrice(reservation1.getTotalPrice())
                .status(reservation1.getStatus())
                .paymentId(reservation1.getPaymentEntity().getId())
                .available(reservation1.isAvailable())
                .requestLimit(reservation1.getRequestLimit() - 1).build());
    }

    private static String getTicketNumber() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String nowDate = LocalDateTime.now().format(formatter);
        String randomNumber = UUID.randomUUID().toString().substring(0, 4);
        return nowDate + "-" + randomNumber;
    }
}
