package com.gamja.tiggle.exchange.application.port.in;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.exchange.adapter.in.web.response.ReadExchangeOfferResponse;

public interface ReadExchangeOfferUseCase {
    ReadExchangeOfferResponse read(ReadExchangeOfferCommand command) throws BaseException;
}
