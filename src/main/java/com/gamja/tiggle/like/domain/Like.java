package com.gamja.tiggle.like.domain;


import com.gamja.tiggle.program.domain.Program;
import com.gamja.tiggle.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Like {
    private Long id;
    private Program program;
    private User user;
}
