package com.gamja.tiggle.section.domain;

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
