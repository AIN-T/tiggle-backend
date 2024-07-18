package com.gamja.tiggle.payment.application.service;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.payment.application.port.in.VerifyPaymentCommand;
import com.gamja.tiggle.payment.application.port.in.VerifyPaymentUseCase;
import com.gamja.tiggle.payment.application.port.out.PaymentPersistencePort;
import com.gamja.tiggle.payment.domain.Payment;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class PGPaymentService implements VerifyPaymentUseCase {
    private final PaymentPersistencePort paymentPersistencePort;
    private final CreatePaymentService createPaymentService;

    @Value("${spring.payment.api_key}")
    public String apiSecret;

    @Override
    public String getToken(VerifyPaymentCommand command) throws BaseException {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");

        Gson gson = new Gson();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("apiSecret", apiSecret);
        String jsonStr = gson.toJson(jsonObject);


        HttpEntity<String> request = new HttpEntity<>(jsonStr, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(
                "https://api.portone.io/login/api-secret",
                HttpMethod.POST,
                request,
                String.class
        );

        Map<String, Object> result = gson.fromJson(response.getBody(), Map.class);
        String access_token = (String) result.get("accessToken");
        String refresh_token = (String) result.get("refreshToken");
        return access_token;
    }

    @Override
    public void compareDB(VerifyPaymentCommand command, String accessToken) throws BaseException {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);
        String paymentId = command.getPaymentId();
        Gson gson = new Gson();
//        JsonObject jsonObject = new JsonObject();
//        jsonObject.addProperty("paymentId", paymentId);
//        String jsonStr = gson.toJson(jsonObject);


        HttpEntity<String> request = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(
                "https://api.portone.io/payments/" + paymentId,
                HttpMethod.GET,
                request,
                String.class
        );

        Map<Object, Object> result = gson.fromJson(response.getBody(), Map.class);
        Map<Object, Object> amount = (Map<Object, Object>) result.get("amount");


        String status = (String) result.get("status");
        String payId = (String) result.get("id");
        String storeId = (String) result.get("storeId");

        Integer canceled = (Integer) amount.get("cancelled");

        String country = (String) result.get("currency");
        Integer totalPrice = (Integer) amount.get("total");

        Boolean verified = false;
        Payment payment = Payment.builder()
                .reservationId(command.getReservationId())
                        .build();
         Payment paymentSearched = paymentPersistencePort.searchPayment(payment);
         //Reservation reservationSearched = reservationPersistence.searchReservation()
         // 정상 결제 검증
        if (status.equals("PAID")&&payId.equals(command.getPaymentId())&&canceled == 0) {
            if(country.equals("KRW")&& totalPrice.equals(paymentSearched.getTicketPrice()+paymentSearched.getFee()-paymentSearched.getUsePoint())){
                verified = true;
            }
            else {
                //결제 정상 진행 되었지만 데이터 이상,
                //환불 요청

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