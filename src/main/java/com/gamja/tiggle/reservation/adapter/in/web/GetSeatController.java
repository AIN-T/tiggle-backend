package com.gamja.tiggle.reservation.adapter.in.web;


import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.BaseResponse;
import com.gamja.tiggle.common.annotation.WebAdapter;
import com.gamja.tiggle.reservation.adapter.in.web.request.GetAllSeatRequest;
import com.gamja.tiggle.reservation.adapter.in.web.request.GetAvailableSeatRequest;
import com.gamja.tiggle.reservation.adapter.in.web.response.GetAllSeatResponse;
import com.gamja.tiggle.reservation.adapter.in.web.response.GetAvailableExchangeSeatResponse;
import com.gamja.tiggle.reservation.adapter.in.web.response.GetAvailableSeatResponse;
import com.gamja.tiggle.reservation.application.port.in.GetAllSeatCommand;
import com.gamja.tiggle.reservation.application.port.in.GetAvailableSeatCommand;
import com.gamja.tiggle.reservation.application.port.in.GetSeatUseCase;
import com.gamja.tiggle.reservation.domain.Seat;
import com.gamja.tiggle.user.domain.CustomUserDetails;
import com.gamja.tiggle.user.domain.User;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@WebAdapter
@RequiredArgsConstructor
@RequestMapping("/seat")
public class GetSeatController {

    private final GetSeatUseCase getSeatUseCase;

    //좌석 조회 시 예약 여부가 필요없다면 getSeatUseCase.getAllSeat으로
    @PostMapping("/all")
    @Operation(summary = "전체 좌석 조회", description = "특정 공연의 전체 좌석을 조회하는 API 입니다.")
    public BaseResponse<List<List<GetAllSeatResponse>>> getAllSeat(
            @RequestBody GetAllSeatRequest request,
            @AuthenticationPrincipal CustomUserDetails customUserDetails) throws BaseException {

        List<List<GetAllSeatResponse>> AllSeatResponse;
        try {
            List<List<Seat>> allSeat = getSeatUseCase.getAllSeatWithEnable(toAllSeatCommand(request,customUserDetails.getUser()));
            AllSeatResponse = getAllSeatResponse(allSeat);
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
        return new BaseResponse<>(AllSeatResponse);

    }

    @PostMapping("/exchange")
    @Operation(summary = "교환 가능 좌석 조회", description = "교환 가능 좌석을 조회하는 API 입니다.")
    public BaseResponse<List<List<GetAvailableExchangeSeatResponse>>> getAvailableExchange(
        @RequestBody GetAllSeatRequest request,
        @AuthenticationPrincipal CustomUserDetails customUserDetails) throws BaseException {

            List<List<GetAvailableExchangeSeatResponse>> AvailableExchangeSeatResponse;
            try {
                List<List<Seat>> allSeat = getSeatUseCase.getAvailableExchangeSeat(toAllSeatCommand(request,customUserDetails.getUser()));
                AvailableExchangeSeatResponse = getExchangeSeatResponse(allSeat);
            } catch (BaseException e) {
                return new BaseResponse<>(e.getStatus());
            }
            return new BaseResponse<>(AvailableExchangeSeatResponse);
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

    private GetAllSeatCommand toAllSeatCommand(GetAllSeatRequest request, User user) {
        return GetAllSeatCommand
                .builder()
                .userId(user.getId())
                .programId(request.getProgramId())
                .timesId(request.getTimesId())
                .sectionId(request.getSectionId())
                .build();
    }

    public GetAllSeatResponse toGetAllSeatResponse(Seat seat) {
        return GetAllSeatResponse.builder()
                .seatId(seat.getId())
                .seatNumber(seat.getSeatNumber())
                .row(seat.getRow())
                .active(seat.getActive())
                .enable(seat.getEnable())
                .build();
    }
    @NotNull
    private List<List<GetAllSeatResponse>> getAllSeatResponse(List<List<Seat>> allSeat) {
        return allSeat.stream()
                .map(row -> row.stream()
                        .filter(Objects::nonNull)
                        .map(this::toGetAllSeatResponse)
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    public GetAvailableExchangeSeatResponse toGetAvailableExcnahgeSeatResponse(Seat seat) {
        return GetAvailableExchangeSeatResponse.builder()
                .seatId(seat.getId())
                .seatNumber(seat.getSeatNumber())
                .row(seat.getRow())
                .active(seat.getActive())
                .enable(seat.getEnable())
                .reservationId(seat.getReservationId())
                .price(seat.getPirce())
                .build();
    }

    @NotNull
    private List<List<GetAvailableExchangeSeatResponse>> getExchangeSeatResponse(List<List<Seat>> allSeat) {
        return allSeat.stream()
                .map(row -> row.stream()
                        .filter(Objects::nonNull)
                        .map(this::toGetAvailableExcnahgeSeatResponse)
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

}
