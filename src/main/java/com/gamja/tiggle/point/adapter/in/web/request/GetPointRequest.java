package com.gamja.tiggle.point.adapter.in.web.request;

import com.gamja.tiggle.point.domain.GetOrUse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetPointRequest {
    private Long userId;
    private GetOrUse getOrUse;
}
