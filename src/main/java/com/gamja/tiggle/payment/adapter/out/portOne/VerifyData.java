package com.gamja.tiggle.payment.adapter.out.portOne;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class VerifyData {
    private String status;
    private String payId;

    private Integer canceled;

    private String country;

    private Integer totalPrice;
}
