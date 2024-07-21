package com.gamja.tiggle.exchange.application.service;


import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.BaseResponseStatus;
import com.gamja.tiggle.common.annotation.UseCase;
import com.gamja.tiggle.exchange.adapter.in.web.response.ReadExchangeOfferListResponse;
import com.gamja.tiggle.exchange.adapter.in.web.response.ReadExchangeOfferResponse;
import com.gamja.tiggle.exchange.adapter.in.web.response.ReadSummaryExchange;
import com.gamja.tiggle.exchange.adapter.out.persistence.ExchangeEntity;
import com.gamja.tiggle.exchange.application.port.in.ReadExchangeOfferCommand;
import com.gamja.tiggle.exchange.application.port.in.ReadExchangeOfferListCommand;
import com.gamja.tiggle.exchange.application.port.in.ReadExchangeOfferUseCase;
import com.gamja.tiggle.exchange.application.port.out.ExchangePort;
import com.gamja.tiggle.exchange.domain.Exchange;
import com.gamja.tiggle.reservation.adapter.out.persistence.Entity.ReservationEntity;
import com.gamja.tiggle.reservation.application.port.out.ReadReservationPort;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


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

        if (!Objects.equals(command.getUser().getId(), reservation2.getUser().getId()))
            throw new BaseException(BaseResponseStatus.NOT_FOUND_USER);

        exchangePort.update(exchangeEntity.watched());

        return ReadExchangeOfferResponse.builder()
                .reservationId(reservation2.getId())
                .programName(reservation2.getProgramEntity().getProgramName())
                .ticketNumber(reservation2.getTicketNumber())
                .location(reservation2.getSeatEntity().getSectionEntity().getLocationEntity().getLocationName())
                .myTicketInfo(createSummaryExchange(reservation2))
                .otherTicketInfo(createSummaryExchange(reservation1))
                .diffPrice(reservation2.getTotalPrice() - reservation1.getTotalPrice())
                .build();
    }

    @Override
    public List<ReadExchangeOfferListResponse> readAll(ReadExchangeOfferListCommand command) {
        List<ReservationEntity> reservations = readReservationPort.readExchangeOfferForMe(command.getUser());

        List<ReadExchangeOfferListResponse> result = new ArrayList<>();

        reservations.forEach(reservation -> {
            reservation.getExchangeEntity2List().forEach(exchange -> {
                result.add(ReadExchangeOfferListResponse.builder()
                        .exchangeId(exchange.getId())
                        .otherEmail(reservation.getUser().getEmail())
                        .programName(reservation.getProgramEntity().getProgramName())
                        .location(reservation.getProgramEntity().getLocationEntity().getLocationName())
                        .otherTicketInfo(createSummaryExchange(reservation))
                        .isWatch(false)
                        .build());
            });
        });

        return result;
    }

    private ReadSummaryExchange createSummaryExchange(ReservationEntity reservation) {
        return ReadSummaryExchange.builder()
                .grade(reservation.getSeatEntity().getSectionEntity().getGradeEntity().getGradeName())
                .sectionName(reservation.getSeatEntity().getSectionEntity().getSectionName())
                .seatNumber(reservation.getSeatEntity().getSeatNumber())
                .price(reservation.getTotalPrice())
                .build();
    }
}
