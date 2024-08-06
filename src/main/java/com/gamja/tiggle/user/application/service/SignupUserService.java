package com.gamja.tiggle.user.application.service;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.user.application.port.in.SignupUserCommand;
import com.gamja.tiggle.user.application.port.in.SignupUserUseCase;
import com.gamja.tiggle.user.application.port.out.EmailVerifyPort;
import com.gamja.tiggle.user.application.port.out.UserPersistencePort;
import com.gamja.tiggle.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SignupUserService implements SignupUserUseCase {
    private final UserPersistencePort userPersistencePort;
    private final EmailVerifyPort emailVerifyPort;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String signup(SignupUserCommand command) throws BaseException {
        User user = User.builder()

                .name(command.getName())
                .email(command.getEmail())
                .password(passwordEncoder.encode(command.getPassword()))
                .loginType(command.getLoginType())
                .status(command.getStatus())
                .enable(command.getEnable())
                .region_1depth_name(command.getRegion_1depth_name())
                .region_2depth_name(command.getRegion_2depth_name())
                .region_3depth_name(command.getRegion_3depth_name())
                .region_4depth_name(command.getRegion_4depth_name())
                .phoneNumber(command.getPhoneNumber())
                .point(0)
                .build();

        userPersistencePort.saveUser(user);
        String uuid = emailVerifyPort.sendEmail(user);
        emailVerifyPort.saveVerify(user.getEmail(), uuid);

        return uuid;
    }
}
