package com.gamja.tiggle.reservation.adapter.out.persistence.Response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GetSectionResponse {

    private Long sectionId;
    private String sectionName;
    private Long gradeId;
    private String gradeName;
    private int price;

    public GetSectionResponse(Long sectionId, String sectionName, Long gradeId, String gradeName, int price) {
        this.sectionId = sectionId;
        this.sectionName = sectionName;
        this.gradeId = gradeId;
        this.gradeName = gradeName;
        this.price = price;
    }
}
