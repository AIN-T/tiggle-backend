package com.gamja.tiggle.reservation.adapter.in.web;


import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.BaseResponse;
import com.gamja.tiggle.common.annotation.WebAdapter;
import com.gamja.tiggle.reservation.adapter.in.web.request.GetAllSeatRequest;
import com.gamja.tiggle.reservation.adapter.in.web.request.GetAvailableSeatRequest;
import com.gamja.tiggle.reservation.adapter.in.web.response.GetAllSeatResponse;
import com.gamja.tiggle.reservation.adapter.in.web.response.GetAvailableSeatResponse;
import com.gamja.tiggle.reservation.application.port.in.GetAllSeatCommand;
import com.gamja.tiggle.reservation.application.port.in.GetAvailableSeatCommand;
import com.gamja.tiggle.reservation.application.port.in.GetSeatUseCase;
import com.gamja.tiggle.reservation.domain.Seat;
import com.gamja.tiggle.user.domain.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@WebAdapter
@RequiredArgsConstructor
@RequestMapping("/seat")
public class GetSeatController {

    private final GetSeatUseCase getSeatUseCase;


    @PostMapping("/all")
    public BaseResponse<List<List<GetAllSeatResponse>>> getAllSeat(
            @RequestBody GetAllSeatRequest request,
            @AuthenticationPrincipal CustomUserDetails customUserDetails) throws BaseException {


        List<List<GetAllSeatResponse>> AllSeatResponse;
        try {
            List<List<Seat>> allSeat = getSeatUseCase.getAllSeat(toAllSeatCommand(request));
            AllSeatResponse = allSeat.stream()
                    .map(row -> row.stream()
                            .map(this::toGetAllSeatResponse)
                            .collect(Collectors.toList()))
                    .collect(Collectors.toList());
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
        return new BaseResponse<>(AllSeatResponse);

    }

    public GetAllSeatResponse toGetAllSeatResponse(Seat seat) {
        return GetAllSeatResponse.builder()
                .seatId(seat.getId())
                .seatNumber(seat.getSeatNumber())
                .active(seat.getActive())
                .row(seat.getRow())
                .build();
    }

    @PostMapping
    @Operation(summary = "예약 가능 좌석 조회", description = "특정 공연의 예약 가능 좌석을 조회하는 API 입니다.")
    public BaseResponse<List<GetAvailableSeatResponse>> getAvailableSeat(
            @RequestBody GetAvailableSeatRequest request,
            @AuthenticationPrincipal CustomUserDetails customUserDetails) {

        GetAvailableSeatCommand command = toCommand(request);
        List<GetAvailableSeatResponse> list;
        try {
            list = getSeatUseCase.getAvailableSeat(command).stream().map(seat -> getResponse(seat)
            ).toList();
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }

        return new BaseResponse<>(list);
    }

    /**
     * dto 변환
     */

    private GetAvailableSeatResponse getResponse(Seat seat) {
        return GetAvailableSeatResponse.builder()
                .seatId(seat.getId())
                .seatNumber(seat.getSeatNumber())
                .row(seat.getRow())
                .build();
    }


    private GetAvailableSeatCommand toCommand(GetAvailableSeatRequest request) {
        return GetAvailableSeatCommand
                .builder()
                .programId(request.getProgramId())
                .timesId(request.getTimesId())
                .sectionId(request.getSectionId())
                .build();
    }

    private GetAllSeatResponse getAllSeatResponse(Seat seat) {
        return GetAllSeatResponse
                .builder()
                .seatId(seat.getId())
                .seatNumber(seat.getSeatNumber())
                .active(seat.getActive())
                .row(seat.getRow())
                .build();
    }

    private GetAllSeatCommand toAllSeatCommand(GetAllSeatRequest request) {
        return GetAllSeatCommand
                .builder()
                .programId(request.getProgramId())
                .timesId(request.getTimesId())
                .sectionId(request.getSectionId())
                .build();
    }


}
