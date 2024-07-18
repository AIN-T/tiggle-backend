package com.gamja.tiggle.program.adapter.in.web.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PriceResponse {
    private String gradeName;
    private int price;
}
