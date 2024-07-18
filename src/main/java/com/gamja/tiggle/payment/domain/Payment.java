package com.gamja.tiggle.payment.domain;

import lombok.AllArgsConstructor;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {
    private Long reservationId;
    private String username;
    private Integer ticketPrice;
    private Integer usePoint;
    private Integer fee;
    private String payType;
    private Boolean verify;
    private Date createdAt;
    private Date verifiedAt;
}

