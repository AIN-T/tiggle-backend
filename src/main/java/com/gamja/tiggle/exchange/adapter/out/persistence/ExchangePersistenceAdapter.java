package com.gamja.tiggle.exchange.adapter.out.persistence;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.BaseResponseStatus;
import com.gamja.tiggle.common.annotation.PersistenceAdapter;
import com.gamja.tiggle.exchange.application.port.out.ExchangePort;
import com.gamja.tiggle.exchange.domain.Exchange;
import com.gamja.tiggle.reservation.adapter.out.persistence.Entity.ReservationEntity;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class ExchangePersistenceAdapter implements ExchangePort {
    private final JpaExchangeRepository exchangeRepository;

    @Override
    public ExchangeEntity read(Exchange exchange) throws BaseException {

        ExchangeEntity result = exchangeRepository.findById(exchange.getId()).orElseThrow(
                () -> new BaseException(BaseResponseStatus.NOT_FOUND_EXCHANGE_OFFER)
        );

        return result;
    }

    @Override
    public void save(Exchange exchange) {
        ReservationEntity reservation1 = ReservationEntity.builder()
                .id(exchange.getReservationId1().getId())
                .build();

        ReservationEntity reservation2 = ReservationEntity.builder()
                .id(exchange.getReservationId2().getId())
                .build();

        ExchangeEntity exchangeEntity = ExchangeEntity.builder()
                .reservation1(reservation1)
                .reservation2(reservation2)
                .isSuccess(exchange.getIsSuccess())
                .isWatch(exchange.getIsWatch())
                .build();

        exchangeRepository.save(exchangeEntity);
    }
}
