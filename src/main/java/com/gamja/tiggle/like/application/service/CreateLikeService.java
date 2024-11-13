package com.gamja.tiggle.like.application.service;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.annotation.UseCase;
import com.gamja.tiggle.like.application.port.in.CreateLikeCommand;
import com.gamja.tiggle.like.application.port.in.CreateLikeUseCase;
import com.gamja.tiggle.like.application.port.out.LikePort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class CreateLikeService implements CreateLikeUseCase {
    private final LikePort likePort;


    @Override
    @Transactional
    public boolean toggleLike(CreateLikeCommand command) throws BaseException {
        if (likePort.isLikedByUser(command)) {
            likePort.unlike(command);
            return false;
        } else {
            likePort.like(command);
            return true;
        }
    }
}
