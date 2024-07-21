package com.gamja.tiggle.reservation.adapter.in.web.request;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class VerifySeatRequest {

    private Long seatId;
    // 선택 후 예약 생성 때 쓸 필드값

    private Long programId;

    private Long timesId;


}
