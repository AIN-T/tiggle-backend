package com.gamja.tiggle.payment.adapter.in.web.request;

import com.gamja.tiggle.payment.domain.PayType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    @Enumerated(EnumType.STRING)
    private PayType payType;
    private Integer ticketPrice;
    private Integer usePoint;
    private Integer fee;
    private Boolean agree;

}
