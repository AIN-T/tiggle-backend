package com.gamja.tiggle.user.adapter.in.web;


import com.gamja.tiggle.user.adapter.in.web.request.SignupUserRequest;
import com.gamja.tiggle.user.application.port.in.SignupUserCommand;
import com.gamja.tiggle.user.application.port.in.SignupUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class SignupUserController {
    private final SignupUserUseCase signupUserUseCase;

    @RequestMapping(method = RequestMethod.POST, value = "/signup")
    void signup(@RequestPart SignupUserRequest request)  {

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

        signupUserUseCase.signup(command);
    }
}
