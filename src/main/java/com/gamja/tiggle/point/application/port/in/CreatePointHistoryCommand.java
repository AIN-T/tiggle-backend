package com.gamja.tiggle.point.application.port.in;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreatePointHistoryCommand {
    private Long userId;
    private Integer value;
    private String description;
}
