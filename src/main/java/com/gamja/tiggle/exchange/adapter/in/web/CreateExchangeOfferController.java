package com.gamja.tiggle.exchange.adapter.in.web;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.BaseResponse;
import com.gamja.tiggle.common.BaseResponseStatus;
import com.gamja.tiggle.common.annotation.WebAdapter;
import com.gamja.tiggle.exchange.application.port.in.CreateExchangeOfferCommand;
import com.gamja.tiggle.exchange.application.port.in.CreateExchangeOfferUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@WebAdapter
@RequiredArgsConstructor
@RequestMapping("/exchange")
public class CreateExchangeOfferController {
    private final CreateExchangeOfferUseCase useCase;

    @GetMapping("/offer")
    public BaseResponse createOffer(@RequestParam Long id1 , @RequestParam Long id2) throws BaseException {
        CreateExchangeOfferCommand command = CreateExchangeOfferCommand.builder()
                .reservationId1(id1)
                .reservationId2(id2)
                .build();

        try {
            useCase.create(command);
        } catch (BaseException e){
            return new BaseResponse<>(e.getStatus());
        }

        return new BaseResponse(BaseResponseStatus.EXCHANGE_OFFER_SUCCESS);
    }
}
