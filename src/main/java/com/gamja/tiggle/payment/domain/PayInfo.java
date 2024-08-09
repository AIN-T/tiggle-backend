package com.gamja.tiggle.payment.domain;

import com.gamja.tiggle.reservation.domain.type.ReservationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PayInfo {
    private String Email4words;
    private Integer ticketPrice;
    private String ticketNumber;
    private PayType payMethod;
    private ReservationType status;

    public void setEmail4words(String email4words) {
        Email4words = email4words;
    }
}

