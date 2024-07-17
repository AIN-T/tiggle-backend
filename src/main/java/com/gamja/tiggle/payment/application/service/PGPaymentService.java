package com.gamja.tiggle.payment.application.service;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.payment.application.port.in.VerifyPaymentCommand;
import com.gamja.tiggle.payment.application.port.in.VerifyPaymentUseCase;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;


@Service
public class PGPaymentService implements VerifyPaymentUseCase {
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
}


