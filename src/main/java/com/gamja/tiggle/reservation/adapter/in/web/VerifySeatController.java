package com.gamja.tiggle.reservation.adapter.in.web;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.BaseResponse;
import com.gamja.tiggle.common.BaseResponseStatus;
import com.gamja.tiggle.common.annotation.WebAdapter;
import com.gamja.tiggle.reservation.adapter.in.web.request.VerifySeatRequest;
import com.gamja.tiggle.reservation.application.port.in.VerifySeatCommand;
import com.gamja.tiggle.reservation.application.port.in.VerifySeatUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@WebAdapter
@RequiredArgsConstructor
@RequestMapping("/verify")
public class VerifySeatController {

    private final VerifySeatUseCase verifySeatUseCase;

    @PostMapping
    public BaseResponse<Void> verifySeatController(
            @RequestBody VerifySeatRequest request) {

        VerifySeatCommand command = from(request);
        try {
            verifySeatUseCase.verifySeat(command);
            return new BaseResponse<>(BaseResponseStatus.SUCCESS);
        } catch (BaseException e) {
            return new BaseResponse<>(BaseResponseStatus.ALREADY_CHOSEN_SEAT);
        }
    }

    private static VerifySeatCommand from(VerifySeatRequest request) {
        return VerifySeatCommand.builder()
                .seatId(request.getSeatId())
                .programId(request.getProgramId())
                .timesId(request.getTimesId())
                .seatId(request.getSeatId())
                .build();
    }
}
