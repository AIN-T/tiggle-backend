package com.gamja.tiggle.grade.domain;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Grade {

    @Id
    private Long gradeId;
    private String gradeName;
}
