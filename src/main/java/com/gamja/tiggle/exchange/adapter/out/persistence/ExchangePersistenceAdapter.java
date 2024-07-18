package com.gamja.tiggle.exchange.adapter.out.persistence;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.BaseResponseStatus;
import com.gamja.tiggle.common.annotation.PersistenceAdapter;
import com.gamja.tiggle.exchange.application.port.out.ExchangePort;
import com.gamja.tiggle.exchange.domain.Exchange;
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
}
