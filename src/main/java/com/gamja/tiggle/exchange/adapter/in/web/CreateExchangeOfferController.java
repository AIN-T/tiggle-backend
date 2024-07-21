package com.gamja.tiggle.exchange.adapter.in.web;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.BaseResponse;
import com.gamja.tiggle.common.BaseResponseStatus;
import com.gamja.tiggle.common.annotation.WebAdapter;
import com.gamja.tiggle.exchange.adapter.in.web.request.CreateExchangeOfferRequest;
import com.gamja.tiggle.exchange.application.port.in.CreateExchangeOfferCommand;
import com.gamja.tiggle.exchange.application.port.in.CreateExchangeOfferUseCase;
import com.gamja.tiggle.user.domain.CustomUserDetails;
import com.gamja.tiggle.user.domain.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RequiredArgsConstructor
@RequestMapping("/exchange")
@Tag(name = "교환 요청")
public class CreateExchangeOfferController {
    private final CreateExchangeOfferUseCase useCase;

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
            useCase.create(command);
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }

        return new BaseResponse<>(BaseResponseStatus.EXCHANGE_OFFER_SUCCESS);
    }
}
