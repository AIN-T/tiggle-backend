package com.gamja.tiggle.user.adapter.in.web;


import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.BaseResponse;
import com.gamja.tiggle.common.BaseResponseStatus;
import com.gamja.tiggle.user.adapter.in.web.request.SignupUserRequest;
import com.gamja.tiggle.user.application.port.in.SignupUserCommand;
import com.gamja.tiggle.user.application.port.in.SignupUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.gamja.tiggle.common.BaseResponseStatus.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class SignupUserController {
    private final SignupUserUseCase signupUserUseCase;

    @PostMapping("/signup")
    BaseResponse signup(@RequestBody SignupUserRequest request) {
        if (request.getEmail() == null) {
            return new BaseResponse<>(USER_EMPTY_EMAIL);
        }
        if (request.getName() == null) {
           return new BaseResponse<>(USER_EMPTY_NAME);
       }
        if(request.getPassword() == null) {
            return new BaseResponse<>(USER_EMPTY_PASSWORD);
        }

        SignupUserCommand command = SignupUserCommand.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .loginType(request.getLoginType())
                .status(true)
                .enable(false)
                .region_1depth_name(request.getRegion_1depth_name())
                .region_2depth_name(request.getRegion_2depth_name())
                .region_3depth_name(request.getRegion_3depth_name())
                .region_4depth_name(request.getRegion_4depth_name())
                .phoneNumber(request.getPhoneNumber())
                .build();

        try {
            String uuid = signupUserUseCase.signup(command);

        } catch (BaseException e) {
            return new BaseResponse(e.getStatus());
        }
        return new BaseResponse(BaseResponseStatus.SUCCESS);
    }

}
