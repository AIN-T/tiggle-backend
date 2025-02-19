package com.gamja.tiggle.exchange.adapter.in.web;


import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.BaseResponse;
import com.gamja.tiggle.common.BaseResponseStatus;
import com.gamja.tiggle.common.annotation.WebAdapter;
import com.gamja.tiggle.exchange.adapter.in.web.request.CreateExchangeApprovalRequest;
import com.gamja.tiggle.exchange.adapter.in.web.request.CreateExchangeOfferRequest;
import com.gamja.tiggle.exchange.adapter.out.persistence.ExchangeEntity;
import com.gamja.tiggle.exchange.application.port.in.CreateExchangeApprovalCommand;
import com.gamja.tiggle.exchange.application.port.in.CreateExchangeApprovalUseCase;
import com.gamja.tiggle.exchange.application.port.in.CreateExchangeOfferCommand;
import com.gamja.tiggle.exchange.application.port.in.CreateExchangeOfferUseCase;
import com.gamja.tiggle.user.domain.CustomUserDetails;
import com.gamja.tiggle.user.domain.User;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;


@WebAdapter
@RequiredArgsConstructor
@RequestMapping("/exchange")
public class CreateExchangeController {
    private final CreateExchangeApprovalUseCase approvalUseCasee;
    private final CreateExchangeOfferUseCase exchangeOfferUseCase;

    @PostMapping("/approval")
    @Operation(summary = "교환 요청 응답", description = "교환 요청 동의나 거절로 응답하는 API 입니다.")
    public BaseResponse<BaseResponseStatus> createApproval(@AuthenticationPrincipal CustomUserDetails customUserDetails , @RequestBody CreateExchangeApprovalRequest request) {
        User user = customUserDetails.getUser();

        CreateExchangeApprovalCommand command = CreateExchangeApprovalCommand.builder()
                .user(user)
                .exchangeId(request.getExchangeId())
                .isAgree(request.getIsAgree())
                .build();

        try {
//            TODO: 1번 실행
            ExchangeEntity exchangeEntity = approvalUseCasee.findExchangeEntity(command.getExchangeId());

//            TODO: 2번 실행
            if (!Objects.equals(exchangeEntity.getReservation2().getUser().getId(), user.getId())) {
                throw new BaseException(BaseResponseStatus.WRONG_EXCHANGE_OFFER);
            }

            if(exchangeEntity.getIsSuccess() == null){
                command = CreateExchangeApprovalCommand.builder()
                        .user(user)
                        .exchangeId(exchangeEntity.getId())
                        .reservationId1(exchangeEntity.getReservation1().getId())
                        .reservationId2(exchangeEntity.getReservation2().getId())
                        .isAgree(request.getIsAgree())
                        .build();

                if (command.getIsAgree()) {
                    approvalUseCasee.create(exchangeEntity, command);
                } else {
                    approvalUseCasee.reject(exchangeEntity);
                    return new BaseResponse<>(BaseResponseStatus.EXCHANGE_REJECT_SUCCESS);
                }
            } else{
                if (exchangeEntity.getIsSuccess()) {
                    throw new BaseException(BaseResponseStatus.ALREADY_SUCCESS_EXCHANGE_OFFER);
                }else  {
                    throw new BaseException(BaseResponseStatus.ALREADY_FAIL_EXCHANGE_OFFER);
                }
            }

        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }

        return new BaseResponse<>(BaseResponseStatus.EXCHANGE_APPROVAL_SUCCESS);
    }

    @PostMapping("/offer")
    @Operation(summary = "교환 요청", description = "공연 티켓 교환을 요청하는 API 입니다.")
    public BaseResponse<BaseResponseStatus> createOffer(@AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestBody CreateExchangeOfferRequest request) {
        User user = customUserDetails.getUser();

        CreateExchangeOfferCommand command = CreateExchangeOfferCommand.builder()
                .user(user)
                .reservationId1(request.getId1())
                .reservationId2(request.getId2())
                .build();

        try {
            exchangeOfferUseCase.create(command);
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }

        return new BaseResponse<>(BaseResponseStatus.EXCHANGE_OFFER_SUCCESS);
    }
}
