package com.gamja.tiggle.like.application.port.out;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.like.application.port.in.CreateLikeCommand;
import com.gamja.tiggle.program.domain.Program;
import com.gamja.tiggle.user.domain.User;

import java.util.List;


public interface LikePort {
    boolean isLikedByUser(CreateLikeCommand command);
    void like(CreateLikeCommand command) throws BaseException;
    void unlike(CreateLikeCommand command);
    List<Program> readMyLikes(User user);
}
