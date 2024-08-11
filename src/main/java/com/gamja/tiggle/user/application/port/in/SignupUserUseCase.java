package com.gamja.tiggle.user.application.port.in;

import com.gamja.tiggle.common.BaseException;

public interface SignupUserUseCase {
    String signup(SignupUserCommand command) throws BaseException;

    Boolean duplicatedEmail(DuplicatedEmailCommand command);
}
