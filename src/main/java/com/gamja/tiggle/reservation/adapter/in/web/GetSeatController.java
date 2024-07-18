package com.gamja.tiggle.reservation.adapter.in.web;


import com.gamja.tiggle.common.BaseResponse;
import com.gamja.tiggle.common.annotation.WebAdapter;
import com.gamja.tiggle.reservation.adapter.in.web.request.GetRemainedSeatRequest;
import com.gamja.tiggle.reservation.adapter.in.web.response.GetRemainedSeatResponse;
import com.gamja.tiggle.reservation.application.port.in.GetRemainedSeatCommand;
import com.gamja.tiggle.reservation.application.port.in.GetRemainedSeatUseCase;
import com.gamja.tiggle.reservation.domain.Seat;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@WebAdapter
@RequiredArgsConstructor
@RequestMapping("/seat")
public class GetSeatController {

    private final GetRemainedSeatUseCase getRemainedSeatUseCase;


    //@AuthenticationPrincipal CustomUserDetails customUserDetails
    @PostMapping
    public BaseResponse<List<GetRemainedSeatResponse>> getRemainedSeat(
            @RequestBody GetRemainedSeatRequest request) {

        GetRemainedSeatCommand command = toCommand(request);

        List<GetRemainedSeatResponse> list
                = getRemainedSeatUseCase.getRemainedSeat(command).stream().map(seat ->
                getResponse(seat)
        ).toList();

        return new BaseResponse<>(list);
    }

    private static GetRemainedSeatResponse getResponse(Seat seat) {
        return GetRemainedSeatResponse.builder()
                .seatId(seat.getId())
                .seatNumber(seat.getSeatNumber())
                .sectionId(seat.getSectionId())
                .build();
    }


    private static GetRemainedSeatCommand toCommand(GetRemainedSeatRequest request) {
        return GetRemainedSeatCommand
                .builder()
                .programId(request.getProgramId())
                .timesId(request.getTimesId())
                .sectionId(request.getSectionId())
                .build();
    }
}
