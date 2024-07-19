package com.gamja.tiggle.exchange.application.port.out;


import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.exchange.adapter.out.persistence.ExchangeEntity;
import com.gamja.tiggle.exchange.domain.Exchange;

public interface ExchangePort {
    ExchangeEntity read(Exchange exchange) throws BaseException;
    void save(Exchange exchange);
}
