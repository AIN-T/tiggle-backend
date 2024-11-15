package com.gamja.tiggle.like.application.port.in;


import com.gamja.tiggle.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ReadLikeCommand {
    private User user;
    private int size;
    private int page;
}
