package com.gamja.tiggle.payment.adapter.in.web;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.BaseResponse;
import com.gamja.tiggle.common.BaseResponseStatus;
import com.gamja.tiggle.payment.adapter.in.web.request.VerifyPaymentRequest;
import com.gamja.tiggle.payment.application.port.in.VerifyPaymentCommand;
import com.gamja.tiggle.payment.application.port.in.VerifyPaymentUseCase;
import com.gamja.tiggle.payment.application.service.PGPaymentService;
import com.gamja.tiggle.user.domain.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class VerifyPaymentController {
    private final VerifyPaymentUseCase verifyPaymentUseCase;
    private final PGPaymentService pgPaymentService;

    @PostMapping("/verify")
    BaseResponse verify(@RequestBody VerifyPaymentRequest request, @AuthenticationPrincipal CustomUserDetails customUserDetails){
        VerifyPaymentCommand command = VerifyPaymentCommand.builder()
                .paymentId(request.getPaymentId())
                .reservationId(request.getReservationId())
                .username(customUserDetails.getUser().getName())
                .build();

        try {
            String accessToken = verifyPaymentUseCase.getToken();
            verifyPaymentUseCase.compareDB(command, accessToken);
        } catch (BaseException e) {
            return new BaseResponse(e.getStatus());
        }
        return new BaseResponse(BaseResponseStatus.SUCCESS);
    }
}
