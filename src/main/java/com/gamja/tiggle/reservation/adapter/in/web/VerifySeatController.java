package com.gamja.tiggle.reservation.adapter.in.web;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.BaseResponse;
import com.gamja.tiggle.common.BaseResponseStatus;
import com.gamja.tiggle.common.annotation.WebAdapter;
import com.gamja.tiggle.reservation.adapter.in.web.request.VerifySeatRequest;
import com.gamja.tiggle.reservation.application.port.in.VerifySeatCommand;
import com.gamja.tiggle.reservation.application.port.in.VerifySeatUseCase;
import com.gamja.tiggle.user.domain.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@WebAdapter
@RequiredArgsConstructor
@RequestMapping("/verify")
@Tag(name = "검증 컨트롤러", description = "선택한 좌석이 예약 가능한지 검증")
public class VerifySeatController {

    private final VerifySeatUseCase verifySeatUseCase;

    @PostMapping
    @Operation(summary = "좌석 예약 검증")
    public BaseResponse<Void> verifySeatController(
            @RequestBody @Valid VerifySeatRequest request,
            @AuthenticationPrincipal CustomUserDetails customUserDetails) {

        VerifySeatCommand command = from(request,customUserDetails.getUser().getId());
        try {
            verifySeatUseCase.verifySeat(command);
            return new BaseResponse<>(BaseResponseStatus.SUCCESS);
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
