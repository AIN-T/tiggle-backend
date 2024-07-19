package com.gamja.tiggle.exchange.application.port.in;

import com.gamja.tiggle.common.BaseException;

public interface CreateExchangeOfferUseCase {
    void create(CreateExchangeOfferCommand command) throws BaseException;
}
