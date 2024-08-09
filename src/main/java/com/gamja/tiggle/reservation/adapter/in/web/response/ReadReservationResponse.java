package com.gamja.tiggle.reservation.adapter.in.web.response;

import com.gamja.tiggle.payment.domain.PayType;
import com.gamja.tiggle.reservation.domain.type.ReservationType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReadReservationResponse {

    private String ticketNumber; // 예약 번호
    private LocalDateTime createdAt; // 예약 날짜
    private LocalDateTime date; // 공연 날짜
    private String locationName; // 공연장
    private String name; // 예매자
    private String seatInfo; // 좌석 정보 : A구역 1층 3열 N번
    private Integer totalPrice; // 가격 정보
    private String gradeName; // 좌석 등급

    private String programName; // 공연 이름
    private String programInfo; // 공연 정보
    private List<String> imageFiles; // 이미지
    private PayType payType; // 결제 방식
    private Integer ticketPrice; // 티켓 가격
    private Integer usePoint; // 포인트 사용

    @Enumerated(EnumType.STRING)
    private ReservationType status; // 예매 상태

}
