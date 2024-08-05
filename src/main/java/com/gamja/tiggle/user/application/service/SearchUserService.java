package com.gamja.tiggle.user.application.service;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.user.application.port.in.SearchUserUseCase;
import com.gamja.tiggle.user.application.port.in.SignupUserCommand;
import com.gamja.tiggle.user.application.port.in.SignupUserUseCase;
import com.gamja.tiggle.user.application.port.out.EmailVerifyPort;
import com.gamja.tiggle.user.application.port.out.UserPersistencePort;
import com.gamja.tiggle.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SearchUserService implements SearchUserUseCase {
    private final UserPersistencePort userPersistencePort;

    @Override
    public User findById(Long id) throws BaseException {
        User user = userPersistencePort.searchUser(id);
        return user;
    }
}
