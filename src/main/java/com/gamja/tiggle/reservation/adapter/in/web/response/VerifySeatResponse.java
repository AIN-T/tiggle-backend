package com.gamja.tiggle.reservation.adapter.in.web.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class VerifySeatResponse {

    private List<Long> seatId;
}
