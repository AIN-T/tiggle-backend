package com.gamja.tiggle.point.application.port.in;

import com.gamja.tiggle.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreatePointHistoryCommand {
    private Long userId;
    private Integer value;
    private Integer hasPoint;
    private String description;
}
