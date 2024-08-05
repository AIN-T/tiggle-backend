package com.gamja.tiggle.point.application.port.in;

import com.gamja.tiggle.common.BaseException;

public interface CreatePointHistoryUseCase {
    void create(CreatePointHistoryCommand command) throws BaseException;
}
