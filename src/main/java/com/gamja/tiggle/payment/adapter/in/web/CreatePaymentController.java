package com.gamja.tiggle.payment.adapter.in.web;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.payment.application.port.in.CreatePaymentUseCase;
import lombok.RequiredArgsConstructor;
import com.gamja.tiggle.common.BaseResponse;
import com.gamja.tiggle.common.BaseResponseStatus;
import com.gamja.tiggle.payment.adapter.in.web.request.CreatePaymentRequest;
import com.gamja.tiggle.payment.application.port.in.CreatePaymentCommand;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class CreatePaymentController {
    private final CreatePaymentUseCase createPaymentUseCase;

    @PostMapping
    BaseResponse create(@RequestBody CreatePaymentRequest request) { //, @AuthenticationPrincipal CustomUserDetails customUserDetails)
        Boolean requestChk = true;
        //CustomUserDetails를 확인하여 로그인한 사용자의 정보와 Role을 확인
//        if (customUserDetails != null) {
//            user = customUserDetails.getUser();
//            if (request.getAgree) {
//                    if ((request.getReservationId() != null)||(user.getRole().equals("ROLE_USER"))) {
//                        if((request.getTicketPrice()<=0)||(request.getFee()<0)||(request.getUsePoint()<0)) {
//                            return new BaseException(BaseResponseStatus.잘못된 결제 정보);
//                        }
//                        else requestChk =true;
//                    }
//                    else {
//                        return new BaseException(BaseResponseStatus.비 정상적인 접근);
//                    }
//            }
//        }
//        else {
//            return new BaseException(BaseResponseStatus.수수료 동의 안함);
//        }

        if (requestChk == true) {
            CreatePaymentCommand command = CreatePaymentCommand.builder()
                    //.username(user.getName())
                    .reservationId(request.getReservationId())
                    .ticketPrice(request.getTicketPrice())
                    .usePoint(request.getUsePoint())
                    .payType(request.getPayType())
                    .fee(request.getFee())
                    .build();
            try {
                createPaymentUseCase.save(command);
            } catch (BaseException e) {
                return new BaseResponse(e.getStatus());
            }
        }
        return new BaseResponse(BaseResponseStatus.SUCCESS);
    }


}
