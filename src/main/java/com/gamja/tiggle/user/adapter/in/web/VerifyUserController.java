package com.gamja.tiggle.user.adapter.in.web;


import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.BaseResponse;
import com.gamja.tiggle.common.BaseResponseStatus;
import com.gamja.tiggle.user.application.port.in.VerifyUserCommand;
import com.gamja.tiggle.user.application.port.in.VerifyUserUseCase;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class VerifyUserController {
    private final VerifyUserUseCase verifyUserUseCase;

    @GetMapping("/verify")
    @Operation(summary = "이메일 검증", description = "회원가입 후 본인 인증을 위하여 이메일을 인증을 요청하는 API 입니다.")
    BaseResponse signup(String email, String uuid) {
        VerifyUserCommand command = VerifyUserCommand.builder()
                .email(email)
                .uuid(uuid)
                .build();
        try {
            verifyUserUseCase.verify(command);

        } catch (BaseException e) {
            return new BaseResponse(e.getStatus());
        }
        return new BaseResponse(BaseResponseStatus.SUCCESS);
    }



}
