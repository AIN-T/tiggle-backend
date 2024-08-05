package com.gamja.tiggle.point.application.port.in;

import com.gamja.tiggle.point.domain.GetOrUse;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetPointHistoryCommand {
    private Long userId;
    private GetOrUse getOrUse;
}
