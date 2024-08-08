package com.gamja.tiggle.mypage.adapter.in.web;


import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.BaseResponse;
import com.gamja.tiggle.common.BaseResponseStatus;
import com.gamja.tiggle.mypage.application.port.in.GetMyHeaderUseCase;
import com.gamja.tiggle.mypage.domain.MyHeaderInfo;
import com.gamja.tiggle.user.domain.CustomUserDetails;
import com.gamja.tiggle.user.domain.User;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/my-page")
public class GetMyHeaderController {
    private final GetMyHeaderUseCase getMyHeaderUseCase;

    @PostMapping("/header")
    @Operation(summary = "Mypage 헤더 구현 api", description = "로그인한 사용자의 예매 정보, 포인트 정보, 교환 정보 간략 조회")
    BaseResponse getMyHeaderInfo(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        User user = null;
        MyHeaderInfo myHeaderInfo = null;
        //CustomUserDetails 를 확인하여 로그인한 사용자의 정보와 Role을 확인
        if (customUserDetails != null) {
            user = customUserDetails.getUser();
        }
        else {
            return new BaseResponse(BaseResponseStatus.NOT_FOUND_USER);
        }

        try {
                myHeaderInfo = getMyHeaderUseCase.getByUserId(user.getId());
            {

            }

        } catch (BaseException e) {
            return new BaseResponse(e.getStatus());
        }
        return new BaseResponse<>(myHeaderInfo);
    }
}
