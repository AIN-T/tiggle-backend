package com.gamja.tiggle.payment.application.port.in;

import com.gamja.tiggle.common.BaseException;
import org.springframework.stereotype.Service;

@Service
public interface VerifyPaymentUseCase {
    String getToken(VerifyPaymentCommand command) throws BaseException;

    void compareDB(VerifyPaymentCommand command, String accessToken) throws BaseException;
}
