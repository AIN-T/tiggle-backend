package com.gamja.tiggle.point.application.port.in;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.user.domain.User;

public interface CreatePointHistoryUseCase {
    void create(CreatePointHistoryCommand command) throws BaseException;

    User findByUserId(Long id) throws BaseException;
}
