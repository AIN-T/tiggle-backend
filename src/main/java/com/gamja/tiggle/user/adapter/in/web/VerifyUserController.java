package com.gamja.tiggle.user.adapter.in.web;


import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.BaseResponse;
import com.gamja.tiggle.common.BaseResponseStatus;
import com.gamja.tiggle.user.adapter.in.web.request.SignupUserRequest;
import com.gamja.tiggle.user.application.port.in.SignupUserCommand;
import com.gamja.tiggle.user.application.port.in.SignupUserUseCase;
import com.gamja.tiggle.user.application.port.in.VerifyUserCommand;
import com.gamja.tiggle.user.application.port.in.VerifyUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class VerifyUserController {
    private final VerifyUserUseCase verifyUserUseCase;

    @GetMapping("/verify")
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
