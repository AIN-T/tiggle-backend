package com.gamja.tiggle.exchange.adapter.in.web;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.BaseResponse;
import com.gamja.tiggle.common.BaseResponseStatus;
import com.gamja.tiggle.common.annotation.WebAdapter;
import com.gamja.tiggle.exchange.application.port.in.CreateExchangeOfferCommand;
import com.gamja.tiggle.exchange.application.port.in.CreateExchangeOfferUseCase;
import com.gamja.tiggle.user.domain.CustomUserDetails;
import com.gamja.tiggle.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@WebAdapter
@RequiredArgsConstructor
@RequestMapping("/exchange")
public class CreateExchangeOfferController {
    private final CreateExchangeOfferUseCase useCase;

    @GetMapping("/offer")
    public BaseResponse<BaseResponseStatus> createOffer(@AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestParam Long id1, @RequestParam Long id2) {
        User user = customUserDetails.getUser();

        CreateExchangeOfferCommand command = CreateExchangeOfferCommand.builder()
                .user(user)
                .reservationId1(id1)
                .reservationId2(id2)
                .build();

        try {
            useCase.create(command);
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }

        return new BaseResponse<>(BaseResponseStatus.EXCHANGE_OFFER_SUCCESS);
    }
}
