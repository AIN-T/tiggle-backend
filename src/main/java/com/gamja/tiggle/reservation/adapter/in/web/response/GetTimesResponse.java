package com.gamja.tiggle.reservation.adapter.in.web.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetTimesResponse {
    private Long id;
    private LocalDateTime date;
    private int round;
    private LocalDateTime limitEndTime;
}
