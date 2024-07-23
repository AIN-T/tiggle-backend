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
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "교환 요청 단일 조회", description = "교환 요청에 대한 상세 정보를 조회하는 API 입니다.")
    public BaseResponse<ReadExchangeOfferResponse> read(@AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestParam Long id) {
        User user = customUserDetails.getUser();

        ReadExchangeOfferCommand command = ReadExchangeOfferCommand.builder()
                .id(id)
                .user(user)
                .build();

        ReadExchangeOfferResponse response = null;

        try {
            response = useCase.read(command);
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }

        return new BaseResponse<>(BaseResponseStatus.READ_EXCHANGE_OFFER_SUCCESS, response);
    }

    @GetMapping("/list")
    @Operation(summary = "교환 요청 리스트 조회", description = "사용자가 받은 교환 요청들을 page별로 조회하는 API 입니다.")
    public BaseResponse<List<ReadExchangeOfferListResponse>> readAll(@AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestParam Integer page, @RequestParam Integer size) {
        User user = customUserDetails.getUser();

        ReadExchangeOfferListCommand command = ReadExchangeOfferListCommand.builder()
                .user(user)
                .page(page)
                .size(size)
                .build();
        List<ReadExchangeOfferListResponse> result = null;
        try {
            result = useCase.readAll(command);

            if (result == null) {
                throw new BaseException(BaseResponseStatus.NOT_EXCHANGE_OFFER);
            }
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }

        return new BaseResponse<>(result);
    }
}
