package com.gamja.tiggle.section.adapter.in.web.response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

@Builder
@Getter
public class GetSectionListResponse {

    private Long id;
    private String sectionName;
    private Long gradeId;

}
