package com.gamja.tiggle.like.application.port.out;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.like.application.port.in.CreateLikeCommand;
import com.gamja.tiggle.like.application.port.in.ReadLikeCommand;
import com.gamja.tiggle.program.domain.Program;

import java.util.List;


public interface LikePort {
    boolean isLikedByUser(CreateLikeCommand command);
    void like(CreateLikeCommand command) throws BaseException;
    void unlike(CreateLikeCommand command);
    List<Program> readMyLikes(ReadLikeCommand command);
}
