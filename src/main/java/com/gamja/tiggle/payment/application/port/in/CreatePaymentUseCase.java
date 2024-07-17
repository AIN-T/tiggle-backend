package com.gamja.tiggle.payment.application.port.in;

import com.gamja.tiggle.common.BaseException;
import org.springframework.stereotype.Service;

@Service
public interface CreatePaymentUseCase {
    void save(CreatePaymentCommand command) throws BaseException;
}
