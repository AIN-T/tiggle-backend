package com.gamja.tiggle.exchange.application.port.in;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.exchange.adapter.in.web.response.ReadExchangeOfferListResponse;
import com.gamja.tiggle.exchange.adapter.in.web.response.ReadExchangeOfferResponse;

import java.util.List;

public interface ReadExchangeOfferUseCase {
    ReadExchangeOfferResponse read(ReadExchangeOfferCommand command) throws BaseException;

    List<ReadExchangeOfferListResponse> readAll(ReadExchangeOfferListCommand command);
}
