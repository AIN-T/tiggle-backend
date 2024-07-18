package com.gamja.tiggle.exchange.application.service;


import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.annotation.UseCase;
import com.gamja.tiggle.exchange.adapter.in.web.response.ReadExchangeOfferResponse;
import com.gamja.tiggle.exchange.adapter.out.persistence.ExchangeEntity;
import com.gamja.tiggle.exchange.application.port.in.ReadExchangeOfferCommand;
import com.gamja.tiggle.exchange.application.port.in.ReadExchangeOfferUseCase;
import com.gamja.tiggle.exchange.application.port.out.ExchangePort;
import com.gamja.tiggle.exchange.domain.Exchange;
import com.gamja.tiggle.reservation.adapter.out.persistence.Entity.ReservationEntity;
import lombok.RequiredArgsConstructor;


@UseCase
@RequiredArgsConstructor
public class ReadExchangeOfferService implements ReadExchangeOfferUseCase {
    private final ExchangePort exchangePort;

    @Override
    public ReadExchangeOfferResponse read(ReadExchangeOfferCommand command) throws BaseException {
        Exchange exchange = Exchange.builder()
                .id(command.getId())
                .build();

        ExchangeEntity exchangeEntity = exchangePort.read(exchange);

        ReservationEntity reservation1 = exchangeEntity.getReservation1();
        ReservationEntity reservation2 = exchangeEntity.getReservation2();

        return ReadExchangeOfferResponse.builder()
                .reservationId(reservation2.getId())
                .programName(reservation2.getProgramEntity().getProgramName())
                .ticketNumber(reservation2.getTicketNumber())
                .location(reservation2.getSeatEntity().getSection().getLocation().getLocationName())
                .myGrade(reservation2.getSeatEntity().getSection().getGrade().getGradeName())
                .mySectionName(reservation2.getSeatEntity().getSection().getSectionName())
                .mySeatNumber(reservation2.getSeatEntity().getSeatNumber())
                .myPrice(reservation2.getTotalPrice())
                .otherGrade(reservation1.getSeatEntity().getSection().getGrade().getGradeName())
                .otherSectionName(reservation1.getSeatEntity().getSection().getSectionName())
                .otherSeatNumber(reservation1.getSeatEntity().getSeatNumber())
                .otherPrice(reservation1.getTotalPrice())
                .diffPrice(reservation2.getTotalPrice() - reservation1.getTotalPrice())
                .point(reservation1.getTotalPrice() * 0.1)
                .build();
    }
}
