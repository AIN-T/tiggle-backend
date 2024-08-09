package com.gamja.tiggle.payment.application.port.in;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.payment.adapter.in.web.response.GetPayInfoResponse;
import com.gamja.tiggle.payment.domain.PayInfo;
import com.gamja.tiggle.reservation.domain.Reservation;
import org.springframework.stereotype.Service;

@Service
public interface GetPayInfoUseCase {
    PayInfo getReservation(Long id) throws BaseException;

    GetPayInfoResponse makeResponse(PayInfo payInfo) throws BaseException;
}
