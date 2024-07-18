package com.gamja.tiggle.user.application.port.in;

import com.gamja.tiggle.common.BaseException;

public interface VerifyUserUseCase {
    void verify(VerifyUserCommand command) throws BaseException;
}
