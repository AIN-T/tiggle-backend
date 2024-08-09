package com.gamja.tiggle.payment.adapter.in.web;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.BaseResponse;
import com.gamja.tiggle.common.BaseResponseStatus;
import com.gamja.tiggle.payment.adapter.in.web.response.GetPayInfoResponse;
import com.gamja.tiggle.payment.application.port.in.CreatePaymentUseCase;
import com.gamja.tiggle.payment.application.port.in.GetPayInfoUseCase;
import com.gamja.tiggle.payment.domain.PayInfo;
import com.gamja.tiggle.user.domain.CustomUserDetails;
import com.gamja.tiggle.user.domain.User;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class GetPayInfoController {
    private final GetPayInfoUseCase getPayInfoUseCase;

    @GetMapping("/info")
    @Operation(summary = "portone 입력 데이터 요청", description = "결제 API 이용을 위해 필요한 데이터를 전송해주는 API")
    BaseResponse<GetPayInfoResponse> create(Long reservationId, @AuthenticationPrincipal CustomUserDetails customUserDetails) throws BaseException {
        User user = null;
        PayInfo payInfo = null;
        //CustomUserDetails 를 확인하여 로그인한 사용자의 정보와 Role을 확인
        if (customUserDetails != null) {
            user = customUserDetails.getUser();
        } else {
            return new BaseResponse(BaseResponseStatus.NOT_FOUND_USER);
        }

        try {
            payInfo = getPayInfoUseCase.getReservation(reservationId);
        } catch (BaseException e) {
            return new BaseResponse(e.getStatus());
        }
        if (payInfo.getEmail4words().equals(user.getEmail())){
            String email4words = (user.getEmail()).substring(0,4);
            payInfo.setEmail4words(email4words);


            return new BaseResponse<>(getPayInfoUseCase.makeResponse(payInfo));
        }
        else {
            return new BaseResponse(BaseResponseStatus.INCORRECT_RESERVATION_DATA);
        }
    }
}
