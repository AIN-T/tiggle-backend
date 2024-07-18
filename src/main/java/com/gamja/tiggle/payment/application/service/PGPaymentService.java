package com.gamja.tiggle.payment.application.service;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.payment.application.port.in.VerifyPaymentCommand;
import com.gamja.tiggle.payment.application.port.in.VerifyPaymentUseCase;
import com.gamja.tiggle.payment.application.port.out.PaymentPersistencePort;
import com.gamja.tiggle.payment.application.port.out.PortOnePort;
import com.gamja.tiggle.payment.adapter.out.portOne.VerifyData;
import com.gamja.tiggle.payment.domain.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class PGPaymentService implements VerifyPaymentUseCase {
    private final PaymentPersistencePort paymentPersistencePort;
    private final PortOnePort portOnePort;

    @Override
    public String getToken() throws BaseException {
        String access_token = portOnePort.getToken();
        return access_token;
    }

    @Override
    public void compareDB(VerifyPaymentCommand command, String accessToken) throws BaseException {
        String paymentId = command.getPaymentId();
        VerifyData data = portOnePort.findByPaymentId(accessToken, paymentId);

        Boolean verified = false;
        Payment payment = Payment.builder()
                .reservationId(command.getReservationId())
                        .build();
         Payment paymentSearched = paymentPersistencePort.searchPayment(payment);
         //Reservation reservationSearched = reservationPersistence.searchReservation()

        // 정상 결제 검증
        if (data.getStatus().equals("PAID")&&data.getPayId().equals(command.getPaymentId())&&data.getCanceled() == 0) {
            if(data.getCountry().equals("KRW")&& data.getTotalPrice().equals(paymentSearched.getTicketPrice()+paymentSearched.getFee()-paymentSearched.getUsePoint())){
                verified = true;
            }
            else {
                //결제 정상 진행 되었지만 데이터 이상,
                //환불 요청
                portOnePort.cancel(accessToken ,paymentId, "결제 데이터 이상 확인");

                VerifyData cancelChk = portOnePort.findByPaymentId(accessToken, paymentId);
                if(cancelChk.getStatus().equals("CANCELED")){
                    //정상적으로 결제 취소
                }
                else {
                    //throw BaseException(BaseResponseStatus.결제 취소 실패);
                }

                //throw BaseException(BaseResponseStatus.잘못된 결제 데이터로 결제 진행);
            }
        }

        else {
            //throw BaseException(BaseResponseStatus.잘못된 결제 접근);
        }

        if (verified) {
            paymentPersistencePort.verify(paymentSearched);
        }



    }

}