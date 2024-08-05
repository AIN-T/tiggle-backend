package com.gamja.tiggle.reservation.adapter.in.web;
import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.BaseResponse;
import com.gamja.tiggle.common.BaseResponseStatus;
import com.gamja.tiggle.common.annotation.WebAdapter;
import com.gamja.tiggle.reservation.adapter.in.web.response.GetSectionListResponse;
import com.gamja.tiggle.reservation.adapter.in.web.response.ReadReservationResponse;
import com.gamja.tiggle.reservation.application.port.in.GetSectionUseCase;
import com.gamja.tiggle.reservation.application.port.in.ReadReservationCommand;
import com.gamja.tiggle.reservation.application.port.in.ReadReservationUseCase;
import com.gamja.tiggle.reservation.domain.Reservation;
import com.gamja.tiggle.user.domain.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@WebAdapter
@RequestMapping("/section")
@RequiredArgsConstructor

public class GetSectionController {

    private final GetSectionUseCase getSectionUseCase;
    private final ReadReservationUseCase readReservationUseCase;

    @GetMapping("/{locationId}")
    @Operation(summary = "공연 구역 조회", description = "locationId를 입력하여 해당 공연장의 구역리스트를 조회하는 API 입니다.")
    public BaseResponse<List<GetSectionListResponse>> getSection(@PathVariable Long locationId,
                                                                 @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        List<GetSectionListResponse> result;
        try {
            result = getList(locationId);
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
        return new BaseResponse<>(result);
    }

    private List<GetSectionListResponse> getList(Long locationId) throws BaseException {
        return getSectionUseCase.getSection(locationId).stream().map(section ->
                GetSectionListResponse.builder()
                        .id(section.getId())
                        .gradeId(section.getGradeId())
                        .sectionName(section.getSectionName())
                        .build()
        ).toList();
    }

    // 예매 상세 정보
    @GetMapping("/read")
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
                    reservation.getStatus());
            // 성공 응답 반환
            return new BaseResponse<>(BaseResponseStatus.SUCCESS, response);
        } catch (BaseException e) {
            // 실패 응답 반환
            return new BaseResponse<>(BaseResponseStatus.FAIL, null);
        }
    }
}
