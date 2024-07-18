package com.gamja.tiggle.payment.application.port.out;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.payment.domain.Payment;

public interface PaymentPersistencePort {
    void savePayment(Payment payment) throws BaseException;

    Payment searchPayment(Payment payment) throws BaseException;

    void verify(Payment payment) throws BaseException;
}
