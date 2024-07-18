package com.gamja.tiggle.exchange.domain;

import com.gamja.tiggle.reservation.domain.Reservation;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class Exchange {
    private Long id;
    private Reservation reservationId1;
    private Reservation reservationId2;
    private Boolean isSuccess;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Boolean isWatch;
}
