package com.gamja.tiggle.times.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class Times {

    private Long id;
    private Long programId;
    private LocalDateTime date;
    private int round;
    private LocalDateTime limitEndTime;

}
