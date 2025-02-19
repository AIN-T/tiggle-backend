package com.gamja.tiggle.reservation.adapter.in.web;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.BaseResponse;
import com.gamja.tiggle.common.annotation.WebAdapter;
import com.gamja.tiggle.reservation.adapter.in.web.response.GetTimesResponse;
import com.gamja.tiggle.reservation.application.port.in.GetTimesUseCase;
import com.gamja.tiggle.user.domain.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@WebAdapter
@RequiredArgsConstructor
@RequestMapping("/times")

public class GetTimesController {

    private final GetTimesUseCase getTimesUseCase;

    @GetMapping("/{id}/seq")
    @Operation(summary = "날짜 및 회차 조회", description = "ProgramId를 입력하여 해당 공연의 날짜 및 회차를 조회하는 API 입니다.")
    public BaseResponse<List<GetTimesResponse>> getTimes(@PathVariable Long id)  {
        List<GetTimesResponse> list;
        try {
            list = getList(id);
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
        return new BaseResponse<>(list);
    }

    private List<GetTimesResponse> getList(Long id) throws BaseException {
        return getTimesUseCase.getTimes(id).stream().map(times ->
                GetTimesResponse.builder()
                        .id(times.getId())
                        .date(times.getDate())
                        .round(times.getRound())
                        .date(times.getDate())
                        .limitEndTime(times.getLimitEndTime())
                        .build()).toList();
    }

}
