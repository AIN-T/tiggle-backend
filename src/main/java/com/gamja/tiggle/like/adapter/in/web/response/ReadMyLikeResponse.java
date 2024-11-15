package com.gamja.tiggle.like.adapter.in.web.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ReadMyLikeResponse {
    private Long id;
    private String imageUrl;
    private String title;
    private String location;
    private String date;
}
