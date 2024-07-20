package com.gamja.tiggle.exchange.application.service;


import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.annotation.UseCase;
import com.gamja.tiggle.exchange.adapter.in.web.response.ReadExchangeOfferListResponse;
import com.gamja.tiggle.exchange.adapter.in.web.response.ReadExchangeOfferResponse;
import com.gamja.tiggle.exchange.adapter.out.persistence.ExchangeEntity;
import com.gamja.tiggle.exchange.application.port.in.ReadExchangeOfferCommand;
import com.gamja.tiggle.exchange.application.port.in.ReadExchangeOfferListCommand;
import com.gamja.tiggle.exchange.application.port.in.ReadExchangeOfferUseCase;
import com.gamja.tiggle.exchange.application.port.out.ExchangePort;
import com.gamja.tiggle.exchange.domain.Exchange;
import com.gamja.tiggle.reservation.adapter.out.persistence.Entity.ReservationEntity;
import com.gamja.tiggle.reservation.application.port.out.ReadReservationPort;
import com.gamja.tiggle.user.domain.User;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@UseCase
@RequiredArgsConstructor
public class ReadExchangeOfferService implements ReadExchangeOfferUseCase {
    private final ExchangePort exchangePort;
    private final ReadReservationPort readReservationPort;



    @Override
    public ReadExchangeOfferResponse read(ReadExchangeOfferCommand command) throws BaseException {
        Exchange exchange = Exchange.builder()
                .id(command.getId())
                .build();

        ExchangeEntity exchangeEntity = exchangePort.read(exchange);

        ReservationEntity reservation1 = exchangeEntity.getReservation1();
        ReservationEntity reservation2 = exchangeEntity.getReservation2();

        exchangePort.update(exchangeEntity.watched());

        return ReadExchangeOfferResponse.builder()
                .reservationId(reservation2.getId())
                .programName(reservation2.getProgramEntity().getProgramName())
                .ticketNumber(reservation2.getTicketNumber())
                .location(reservation2.getSeatEntity().getSectionEntity().getLocationEntity().getLocationName())
                .myGrade(reservation2.getSeatEntity().getSectionEntity().getGradeEntity().getGradeName())
                .mySectionName(reservation2.getSeatEntity().getSectionEntity().getSectionName())
                .mySeatNumber(reservation2.getSeatEntity().getSeatNumber())
                .myPrice(reservation2.getTotalPrice())
                .otherGrade(reservation1.getSeatEntity().getSectionEntity().getGradeEntity().getGradeName())
                .otherSectionName(reservation1.getSeatEntity().getSectionEntity().getSectionName())
                .otherSeatNumber(reservation1.getSeatEntity().getSeatNumber())
                .otherPrice(reservation1.getTotalPrice())
                .diffPrice(reservation2.getTotalPrice() - reservation1.getTotalPrice())
                .point(reservation1.getTotalPrice() * 0.1)
                .build();
    }

    @Override
    public List<ReadExchangeOfferListResponse> readAll(ReadExchangeOfferListCommand command) {
        List<ReservationEntity> reservations = readReservationPort.readExchangeOfferForMe(User.builder().id(command.getUserId()).build());

        List<ReadExchangeOfferListResponse> result = new ArrayList<>();

        reservations.forEach(reservation -> {
            reservation.getExchangeEntity2List().forEach(exchange -> {
                result.add(ReadExchangeOfferListResponse.builder()
                        .exchangeId(exchange.getId())
                        .otherEmail(reservation.getUser().getEmail())
                        .programName(reservation.getProgramEntity().getProgramName())
                        .location(reservation.getProgramEntity().getLocationEntity().getLocationName())
                        .otherGrade(reservation.getSeatEntity().getSectionEntity().getGradeEntity().getGradeName())
                        .otherPrice(reservation.getTotalPrice())
                        .otherSectionName(reservation.getSeatEntity().getSectionEntity().getSectionName())
                        .otherSeatNumber(reservation.getSeatEntity().getSeatNumber())
                        .isWatch(false)
                        .build());
            });
        });

        return result;
    }
}
