package com.gamja.tiggle.payment.adapter.out.portOne;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.payment.adapter.out.persistence.JpaPaymentRepository;
import com.gamja.tiggle.payment.adapter.out.persistence.PaymentEntity;
import com.gamja.tiggle.payment.application.port.out.PaymentPersistencePort;
import com.gamja.tiggle.payment.application.port.out.PortOnePort;
import com.gamja.tiggle.payment.application.port.out.VerifyData;
import com.gamja.tiggle.payment.domain.Payment;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class PortOneAdapter implements PortOnePort {
    @Value("${spring.payment.api_key}")
    public String apiSecret;

    @Override
    public String getToken() throws BaseException {
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
    public VerifyData findByPaymentId(String token, String id) throws BaseException {
        String accessToken = token;
        String paymentId = id;
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);

        Gson gson = new Gson();

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

        Integer canceled = (Integer) amount.get("cancelled");

        String country = (String) result.get("currency");
        Integer totalPrice = (Integer) amount.get("total");

        return VerifyData.builder()
                .status(status)
                .payId(payId)
                .canceled(canceled)
                .country(country)
                .totalPrice(totalPrice)
                .build();
    }

    @Override
    public void cancel(String token, String id, String message) throws BaseException {
        String accessToken = token;
        String paymentId = id;
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);

        Gson gson = new Gson();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("reason", message);
        String jsonStr = gson.toJson(jsonObject);

        HttpEntity<String> request = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(
                "https://api.portone.io/payments/" + paymentId + "/cancel",
                HttpMethod.GET,
                request,
                String.class
        );
    }
}

