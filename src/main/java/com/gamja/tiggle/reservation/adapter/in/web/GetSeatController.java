package com.gamja.tiggle.reservation.adapter.in.web;


import com.gamja.tiggle.common.BaseResponse;
import com.gamja.tiggle.common.annotation.WebAdapter;
import com.gamja.tiggle.reservation.adapter.in.web.request.GetAvailableSeatRequest;
import com.gamja.tiggle.reservation.adapter.in.web.response.GetAvailableSeatResponse;
import com.gamja.tiggle.reservation.application.port.in.GetAvailableSeatCommand;
import com.gamja.tiggle.reservation.application.port.in.GetAvailableSeatUseCase;
import com.gamja.tiggle.reservation.domain.Seat;
import com.gamja.tiggle.user.domain.CustomUserDetails;
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
    public BaseResponse<List<GetAvailableSeatResponse>> getAvailableSeat(
            @RequestBody GetAvailableSeatRequest request,
            @AuthenticationPrincipal CustomUserDetails customUserDetails) {

        GetAvailableSeatCommand command = toCommand(request);
        List<GetAvailableSeatResponse> list
                = getAvailableSeatUseCase.getAvailableSeat(command).stream().map(seat -> getResponse(seat)
        ).toList();

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
