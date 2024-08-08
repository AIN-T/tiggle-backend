package com.gamja.tiggle.reservation.adapter.in.web.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReadTemporaryReservationResponse {
    private String ticketNumber; // 예약 번호
    private LocalDateTime date; // 공연 날짜
    private String locationName; // 공연장
    private String seatInfo; // 좌석 정보 : A구역 1층 3열 N번
    private Integer ticketPrice; // 가격 정보
    private String gradeName; // 좌석 등급

    private String programName; // 공연 이름
    private String programInfo; // 공연 정보
    private Integer myPoint; // 포인트 사용
}
