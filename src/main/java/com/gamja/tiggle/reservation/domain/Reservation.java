package com.gamja.tiggle.reservation.domain;

import com.gamja.tiggle.reservation.domain.type.ReservationType;
import com.gamja.tiggle.user.domain.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class Reservation {

    private Long id;

    private Long programId;
    private Long userId;
    private Long seatId;
    private Long timesId;

    private String ticketNumber;
    private String payMethod;
    private Integer totalPrice;

    @Enumerated(EnumType.STRING)
    private ReservationType status;

    private Integer requestLimit;
    private Integer refund;

    // 예매 받아올 값
    private LocalDateTime createdAt; // 예약 날짜
    private LocalDateTime programStartDate; // 공연 날짜
    private String locationName; // 공연장
    private String name; // 예매자
    private String seatInfo; // 좌석 정보

}
