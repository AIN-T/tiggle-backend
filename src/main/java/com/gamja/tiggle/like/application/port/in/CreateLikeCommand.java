package com.gamja.tiggle.like.application.port.in;

import com.gamja.tiggle.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreateLikeCommand {
    private User user;
    private Long programId;
}
