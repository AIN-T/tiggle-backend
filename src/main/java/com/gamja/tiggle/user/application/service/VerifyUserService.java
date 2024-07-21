package com.gamja.tiggle.user.application.service;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.user.adapter.out.persistence.EmailVerify;
import com.gamja.tiggle.user.application.port.in.VerifyUserCommand;
import com.gamja.tiggle.user.application.port.in.VerifyUserUseCase;
import com.gamja.tiggle.user.application.port.out.EmailVerifyPort;
import com.gamja.tiggle.user.application.port.out.UserPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VerifyUserService implements VerifyUserUseCase {
    private final EmailVerifyPort emailVerifyPort;
    private final UserPersistencePort userPersistencePort;

    @Override
    public void verify(VerifyUserCommand command) throws BaseException {
        EmailVerify emailVerify = emailVerifyPort.findByEmail(command.getEmail());
        if (emailVerify.getUuid().equals(command.getUuid())) {
            userPersistencePort.verifyUser(command.getEmail());
        }
    }
}

