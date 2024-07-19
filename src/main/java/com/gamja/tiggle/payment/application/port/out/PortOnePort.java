package com.gamja.tiggle.payment.application.port.out;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.payment.adapter.out.portOne.VerifyData;

public interface PortOnePort {
    String getToken() throws BaseException;

    VerifyData findByPaymentId(String token, String id) throws BaseException;

    String cancel(String token, String id, String msg) throws BaseException;
}
