package com.gamja.tiggle.exchange.adapter.in.web;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.BaseResponse;
import com.gamja.tiggle.common.BaseResponseStatus;
import com.gamja.tiggle.common.annotation.WebAdapter;
import com.gamja.tiggle.exchange.adapter.in.web.response.ReadExchangeOfferListResponse;
import com.gamja.tiggle.exchange.adapter.in.web.response.ReadExchangeOfferResponse;
import com.gamja.tiggle.exchange.application.port.in.ReadExchangeOfferCommand;
import com.gamja.tiggle.exchange.application.port.in.ReadExchangeOfferListCommand;
import com.gamja.tiggle.exchange.application.port.in.ReadExchangeOfferUseCase;
import com.gamja.tiggle.user.domain.CustomUserDetails;
import com.gamja.tiggle.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@WebAdapter
@RequiredArgsConstructor
@RequestMapping("/exchange")
public class ReadExchangeOfferController {
    private final ReadExchangeOfferUseCase useCase;

    @GetMapping("")
    public BaseResponse<ReadExchangeOfferResponse> read(@AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestParam Long id) {
        User user = customUserDetails.getUser();

        ReadExchangeOfferCommand command = ReadExchangeOfferCommand.builder()
                .id(id)
                .user(user)
                .build();

        ReadExchangeOfferResponse response = null;

        try {
            response = useCase.read(command);
        }catch(BaseException e){
            return new BaseResponse<>(e.getStatus());
        }

        return new BaseResponse<>(BaseResponseStatus.READ_EXCHANGE_OFFER_SUCCESS, response);
    }

    @GetMapping("/list")
    public BaseResponse<List<ReadExchangeOfferListResponse>> readAll(@AuthenticationPrincipal CustomUserDetails customUserDetails){
        User user = customUserDetails.getUser();

        ReadExchangeOfferListCommand command = ReadExchangeOfferListCommand.builder()
                .user(user)
                .build();

        List<ReadExchangeOfferListResponse> result = useCase.readAll(command);

        return new BaseResponse<>(result);
    }
}
