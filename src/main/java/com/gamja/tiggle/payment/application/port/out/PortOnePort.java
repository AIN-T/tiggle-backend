package com.gamja.tiggle.payment.application.port.out;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.payment.domain.Payment;

public interface PortOnePort {
    String getToken() throws BaseException;

    VerifyData findByPaymentId(String token, String id) throws BaseException;

    void cancel(String token, String id, String msg) throws BaseException;
}
