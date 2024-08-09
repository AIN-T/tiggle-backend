package com.gamja.tiggle.payment.application.service;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.BaseResponseStatus;
import com.gamja.tiggle.payment.adapter.in.web.response.GetPayInfoResponse;
import com.gamja.tiggle.payment.application.port.in.GetPayInfoUseCase;
import com.gamja.tiggle.payment.application.port.in.VerifyPaymentCommand;
import com.gamja.tiggle.payment.application.port.in.VerifyPaymentUseCase;
import com.gamja.tiggle.payment.application.port.out.PaymentPersistencePort;
import com.gamja.tiggle.payment.application.port.out.PortOnePort;
import com.gamja.tiggle.payment.adapter.out.portOne.VerifyData;
import com.gamja.tiggle.payment.domain.PayInfo;
import com.gamja.tiggle.payment.domain.PayType;
import com.gamja.tiggle.payment.domain.Payment;
import com.gamja.tiggle.reservation.adapter.out.persistence.Entity.ReservationEntity;
import com.gamja.tiggle.reservation.application.port.out.ReadReservationPort;
import com.gamja.tiggle.reservation.domain.type.ReservationType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class PGPaymentService implements VerifyPaymentUseCase, GetPayInfoUseCase {
    private final PaymentPersistencePort paymentPersistencePort;
    private final PortOnePort portOnePort;
    private final ReadReservationPort readReservationPort;

    @Override
    public String getToken() throws BaseException {
        String access_token = portOnePort.getToken();
        return access_token;
    }

    @Override
    public void compareDB(VerifyPaymentCommand command, String accessToken) throws BaseException {
        String paymentId = command.getPaymentId();
        VerifyData data = portOnePort.findByPaymentId(accessToken, paymentId);
        if (data == null) {
            throw new BaseException(BaseResponseStatus.WRONG_PAYMENT_ID);
        }

        Boolean verified = false;
        Payment paymentSearched = paymentPersistencePort.searchPayment(command.getReservationId());

        // 정상 결제 검증
        if (data.getStatus().equals("PAID") && data.getPayId().equals(command.getPaymentId()) && data.getCanceled() == 0) {
            if (data.getCountry().equals("KRW") && data.getTotalPrice().equals(paymentSearched.getTicketPrice() + paymentSearched.getFee() - paymentSearched.getUsePoint())) {
                verified = true;
            } else {
                //결제 정상 진행 되었지만 데이터 이상,
                //환불 요청
                String PortOneError = portOnePort.cancel(accessToken, paymentId, "결제 데이터 이상 확인");
                System.out.println("PortOneError = " + PortOneError);
                VerifyData cancelChk = portOnePort.findByPaymentId(accessToken, paymentId);
                if (cancelChk.getStatus().equals("CANCELED")) {
                    //정상적으로 결제 취소
                } else {
                    throw new BaseException(BaseResponseStatus.FAIL_CANCELED_PAYMENT);
                }
                throw new BaseException(BaseResponseStatus.INCORRECT_DATA_CANCEL_SUCCESS);
            }
        } else {
            throw new BaseException(BaseResponseStatus.INCORRECT_PAYMENT_INFO);
        }

        if (verified) {
            paymentPersistencePort.verify(paymentSearched);
        }
    }

    @Override
    public PayInfo getReservation(Long reservationId) throws BaseException {
        ReservationEntity reservationEntity = readReservationPort.read(reservationId);
        String email = reservationEntity.getUser().getEmail();
        String ticketNumber = reservationEntity.getTicketNumber();
        Integer ticketPrice = reservationEntity.getTotalPrice();
        PayType payMethod = reservationEntity.getPayType();
        ReservationType status = reservationEntity.getStatus();

        return PayInfo.builder()
                .Email4words(email)
                .ticketNumber(ticketNumber)
                .ticketPrice(ticketPrice)
                .payMethod(payMethod)
                .status(status).build();

    }

    @Override
    public GetPayInfoResponse makeResponse(PayInfo payInfo) throws BaseException {
        if (payInfo.getStatus().equals(ReservationType.IN_PROGRESS)){
            return GetPayInfoResponse.builder()
                    .PAYMENTINFO(payInfo.getTicketNumber()+"_"+payInfo.getEmail4words())
                    .ORDERNAME("ticket")
                    .PRICE(payInfo.getTicketPrice())
                    .PAYMETHOD(payInfo.getPayMethod())
                    .build();
        }
        return null;
    }
}