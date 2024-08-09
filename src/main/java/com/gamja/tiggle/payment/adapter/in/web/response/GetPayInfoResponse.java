package com.gamja.tiggle.payment.adapter.in.web.response;

import com.gamja.tiggle.payment.domain.PayType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetPayInfoResponse {
    private String PAYMENTINFO;
    private String ORDERNAME;
    private Integer PRICE;
    private PayType PAYMETHOD;
}
