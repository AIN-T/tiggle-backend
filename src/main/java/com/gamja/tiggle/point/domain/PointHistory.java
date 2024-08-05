package com.gamja.tiggle.point.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class PointHistory {
    private Long id;
    private Long userId;
    private Integer value;
    private String description;
    private GetOrUse getOrUse;

    private Date modifiedAt;
}

