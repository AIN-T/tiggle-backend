package com.gamja.tiggle.like.application.port.in;

import com.gamja.tiggle.common.BaseException;

public interface CreateLikeUseCase {
    boolean toggleLike(CreateLikeCommand command) throws BaseException;
}
