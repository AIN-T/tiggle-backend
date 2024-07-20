package com.gamja.tiggle.exchange.application.port.in;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.exchange.adapter.out.persistence.ExchangeEntity;

public interface CreateExchangeApprovalUseCase {
    ExchangeEntity findExchangeEntity(Long id) throws BaseException;
    void create(ExchangeEntity exchangeEntity, CreateExchangeApprovalCommand command) throws BaseException;
    void reject(ExchangeEntity exchange);
}
