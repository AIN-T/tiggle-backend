package com.gamja.tiggle.reservation.adapter.in.web;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.BaseResponse;
import com.gamja.tiggle.common.annotation.WebAdapter;
import com.gamja.tiggle.reservation.adapter.in.web.request.VerifySeatRequest;
import com.gamja.tiggle.reservation.adapter.in.web.response.VerifySeatResponse;
import com.gamja.tiggle.reservation.application.port.in.VerifySeatCommand;
import com.gamja.tiggle.reservation.application.port.in.VerifySeatUseCase;
import com.gamja.tiggle.user.domain.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@WebAdapter
@RequiredArgsConstructor
@RequestMapping("/verify")
public class VerifySeatController {

    private final VerifySeatUseCase verifySeatUseCase;

    @PostMapping
    @Operation(summary = "좌석 예약 검증",  description = "선택한 좌석이 예약 가능한지 검증하는 API 입니다.")
    public BaseResponse<VerifySeatResponse> verifySeatController(
            @RequestBody @Valid VerifySeatRequest request,
            @AuthenticationPrincipal CustomUserDetails customUserDetails) {

        VerifySeatCommand command = from(request,customUserDetails.getUser().getId());
        try {
            VerifySeatResponse response =  verifySeatUseCase.verifySeat(command);
            return new BaseResponse<>(response);
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
    }
    private static VerifySeatCommand from(VerifySeatRequest request, Long userId) {
        return VerifySeatCommand.builder()
                .programId(request.getProgramId())
                .timesId(request.getTimesId())
                .seatId(request.getSeatId())
                .userId(userId)
                .build();
    }
}
