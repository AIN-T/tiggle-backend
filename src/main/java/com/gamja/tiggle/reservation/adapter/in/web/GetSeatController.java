package com.gamja.tiggle.reservation.adapter.in.web;


import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.BaseResponse;
import com.gamja.tiggle.common.annotation.WebAdapter;
import com.gamja.tiggle.reservation.adapter.in.web.request.GetAvailableSeatRequest;
import com.gamja.tiggle.reservation.adapter.in.web.response.GetAvailableSeatResponse;
import com.gamja.tiggle.reservation.application.port.in.GetAvailableSeatCommand;
import com.gamja.tiggle.reservation.application.port.in.GetAvailableSeatUseCase;
import com.gamja.tiggle.reservation.domain.Seat;
import com.gamja.tiggle.user.domain.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@WebAdapter
@RequiredArgsConstructor
@RequestMapping("/seat")
public class GetSeatController {

    private final GetAvailableSeatUseCase getAvailableSeatUseCase;


    @PostMapping
    @Operation(summary = "예약 가능 좌석 조회", description = "특정 공연의 예약 가능 좌석을 조회하는 API 입니다.")
    public BaseResponse<List<GetAvailableSeatResponse>> getAvailableSeat(
            @RequestBody GetAvailableSeatRequest request,
            @AuthenticationPrincipal CustomUserDetails customUserDetails) {

        GetAvailableSeatCommand command = toCommand(request);
        List<GetAvailableSeatResponse> list;
        try {
            list = getAvailableSeatUseCase.getAvailableSeat(command).stream().map(seat -> getResponse(seat)
            ).toList();
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }

        return new BaseResponse<>(list);
    }

    private static GetAvailableSeatResponse getResponse(Seat seat) {
        return GetAvailableSeatResponse.builder()
                .seatId(seat.getId())
                .seatNumber(seat.getSeatNumber())
                .sectionId(seat.getSectionId())
                .build();
    }


    private static GetAvailableSeatCommand toCommand(GetAvailableSeatRequest request) {
        return GetAvailableSeatCommand
                .builder()
                .programId(request.getProgramId())
                .timesId(request.getTimesId())
                .sectionId(request.getSectionId())
                .build();
    }
}
