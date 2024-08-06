package com.gamja.tiggle.program.adapter.in.web.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetPriceResponse {

    private Long programGradeId;
    private Long gradeId;
    private int price;
    private String gradeName;
//    TODO 좌석 별 예약 가능석 갯수 구하려면 locationId 추가
}
