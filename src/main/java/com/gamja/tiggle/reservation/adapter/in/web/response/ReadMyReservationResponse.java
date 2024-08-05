package com.gamja.tiggle.reservation.adapter.in.web.response;

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
public class ReadMyReservationResponse {
    private Long reservationId; // 예매 id
    private String ticketNumber; // 예약 번호
    private LocalDateTime createdAt; // 예약 날짜
    private LocalDateTime date; // 관람 날짜
    private String locationName; // 공연장
    private String programName; // 공연 이름
    private LocalDateTime programStartDate; // 공연 날짜
    private LocalDateTime programEndDate;
    private Integer requestLimit; // 교환 요청 횟수
    @Enumerated(EnumType.STRING)
    private ReservationType status; // 예매 상태
    private List<String> imageFiles; // 이미지
}
