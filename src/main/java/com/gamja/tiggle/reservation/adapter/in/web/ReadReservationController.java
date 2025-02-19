package com.gamja.tiggle.reservation.adapter.in.web;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.BaseResponse;
import com.gamja.tiggle.common.BaseResponseStatus;
import com.gamja.tiggle.common.annotation.WebAdapter;
import com.gamja.tiggle.reservation.adapter.in.web.response.ReadMyReservationResponse;
import com.gamja.tiggle.reservation.adapter.in.web.response.ReadReservationResponse;
import com.gamja.tiggle.reservation.adapter.in.web.response.ReadTemporaryReservationResponse;
import com.gamja.tiggle.reservation.application.port.in.ReadReservationCommand;
import com.gamja.tiggle.reservation.application.port.in.ReadReservationUseCase;
import com.gamja.tiggle.reservation.application.port.in.ReadTemporaryReservationCommand;
import com.gamja.tiggle.reservation.domain.Reservation;
import com.gamja.tiggle.user.domain.CustomUserDetails;
import com.gamja.tiggle.user.domain.User;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@WebAdapter
@RequestMapping("/reservation")
@RequiredArgsConstructor
public class ReadReservationController {
    private final ReadReservationUseCase readReservationUseCase;

    // 예매 상세 정보
    @GetMapping("/read")
    @Operation(summary = "예매 내역 조회", description = "ReservationId를 입력하여 예매 내역을 조회하는 API 입니다.")
    public BaseResponse<ReadReservationResponse> readReservation(@RequestParam Long reservationId) {
        try {
            // 커맨드 객체 생성
            ReadReservationCommand command = ReadReservationCommand.builder()
                    .reservationId(reservationId)
                    .build();
            // 예약 정보 조회
            Reservation reservation = readReservationUseCase.readReservation(command);
            // 응답 객체 생성
            ReadReservationResponse response = new ReadReservationResponse(
                    reservation.getTicketNumber(),
                    reservation.getCreatedAt(),
                    reservation.getDate(),
                    reservation.getLocationName(),
                    reservation.getName(),
                    reservation.getSeatInfo(),
                    reservation.getTotalPrice(),
                    reservation.getGradeName(),
                    reservation.getProgramName(),
                    reservation.getProgramInfo(),
                    reservation.getImageFiles(),
                    reservation.getPayType(),
                    reservation.getTicketPrice(),
                    reservation.getUsePoint(),
                    reservation.getStatus()
            );

            // 성공 응답 반환
            return new BaseResponse<>(BaseResponseStatus.SUCCESS, response);
        } catch (BaseException e) {
            // 실패 응답 반환
            return new BaseResponse<>(BaseResponseStatus.FAIL, null);
        }
    }


    @GetMapping("/temporary")
    @Operation(summary = "임시 예매 내역 조회", description = "ReservationId를 입력하여 진행중인 예매 내역을 조회하는 API 입니다.")
    public BaseResponse<ReadTemporaryReservationResponse> readTemporary(@RequestParam String ticketNumber) {

        try {

            ReadTemporaryReservationCommand command = ReadTemporaryReservationCommand.builder()
                    .ticketNumber(ticketNumber)
                    .build();

            Reservation reservation = readReservationUseCase.readTemporaryReservation(command);

            ReadTemporaryReservationResponse response = ReadTemporaryReservationResponse.builder()
                    .ticketNumber(reservation.getTicketNumber())
                    .date(reservation.getDate())
                    .locationName(reservation.getLocationName())
                    .seatInfo(reservation.getSeatInfo())
                    .ticketPrice(reservation.getTotalPrice())
                    .gradeName(reservation.getGradeName())
                    .programName(reservation.getProgramName())
                    .programInfo(reservation.getProgramInfo())
                    .myPoint(reservation.getMyPoint())
                    .build();

            return new BaseResponse<>(BaseResponseStatus.SUCCESS, response);
        } catch (BaseException e) {

            return new BaseResponse<>(BaseResponseStatus.FAIL, null);
        }
    }


    @GetMapping("/myRead")
    @Operation(summary = "예매내역 리스트 조회", description = "특정 사용자의 예매 내역들을 조회하는 API 입니다.")
    public BaseResponse<List<ReadMyReservationResponse>> myRead(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                                                @RequestParam Integer page,
                                                                @RequestParam Integer size) {
        User user = customUserDetails.getUser();
        ReadReservationCommand command = ReadReservationCommand.builder()
                .user(user)
                .page(page)
                .size(size)
                .build();
        try {
            List<Reservation> reservation = readReservationUseCase.myRead(command);
            List<ReadMyReservationResponse> responses = reservation.stream()
                    .map(r -> ReadMyReservationResponse.builder()
                            .programId(r.getProgramId())
                            .timesId(r.getTimesId())
                            .sectionId(r.getSectionId())
                            .reservationId(r.getId())
                            .locationId(r.getLocationId())
                            .ticketNumber(r.getTicketNumber())
                            .createdAt(r.getCreatedAt())
                            .date(r.getDate())
                            .locationName(r.getLocationName())
                            .programName(r.getProgramName())
                            .programStartDate(r.getProgramStartDate())
                            .programEndDate(r.getProgramEndDate())
                            .status(r.getStatus())
                            .requestLimit(r.getRequestLimit())
                            .imageFiles(r.getImageFiles())
                            .build())
                    .collect(Collectors.toUnmodifiableList());
            return new BaseResponse<>(BaseResponseStatus.SUCCESS, responses);

        } catch (BaseException e) {
            return new BaseResponse<>(BaseResponseStatus.FAIL, null);
        }
    }
}
