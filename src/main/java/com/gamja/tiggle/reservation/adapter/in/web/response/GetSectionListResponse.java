package com.gamja.tiggle.reservation.adapter.in.web.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GetSectionListResponse {

    private Long id;
    private String sectionName;
    private Long gradeId;
    private String gradeName;
    private int price;
    private Long remainingCount;


}
