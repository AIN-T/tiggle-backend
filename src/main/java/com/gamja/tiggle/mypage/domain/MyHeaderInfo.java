package com.gamja.tiggle.mypage.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class MyHeaderInfo {
    private String username;
    private Integer notWatchedYet;
    private Integer reservationCnt;
    private Integer point;
}

