package com.gamja.tiggle.program.application.port.in;

import com.gamja.tiggle.common.BaseException;

public interface CreateLocationUseCase {
    void createLocation(CreateLocationCommand command) throws BaseException;
}
