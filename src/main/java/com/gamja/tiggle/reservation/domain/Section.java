package com.gamja.tiggle.reservation.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Section {

    private Long id;
    private Long locationId;
    private String sectionName;
    private Long gradeId;
}
