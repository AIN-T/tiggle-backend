package com.gamja.tiggle.payment.adapter.in.web.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreatePaymentRequest {
    private Long reservationId;
    private String payType;
    private Integer ticketPrice;
    private Integer usePoint;
    private Integer fee;
    private Boolean agree;

}
