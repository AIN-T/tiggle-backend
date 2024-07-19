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
import com.gamja.tiggle.user.domain.User;
import lombok.RequiredArgsConstructor;
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
    public BaseResponse<ReadExchangeOfferResponse> read(@RequestParam Long id) {
//        Todo: token으로 user 받아오세요.

        ReadExchangeOfferCommand command = ReadExchangeOfferCommand.builder()
                .id(id)
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
    public BaseResponse<List<ReadExchangeOfferListResponse>> readAll(){
        User user = User.builder().id(1L).build();

        ReadExchangeOfferListCommand command = ReadExchangeOfferListCommand.builder()
                .userId(user.getId())
                .build();

        List<ReadExchangeOfferListResponse> result = useCase.readAll(command);

        return new BaseResponse<>(result);

    }
}
