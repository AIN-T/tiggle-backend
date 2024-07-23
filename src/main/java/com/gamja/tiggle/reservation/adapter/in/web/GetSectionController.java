package com.gamja.tiggle.reservation.adapter.in.web;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.BaseResponse;
import com.gamja.tiggle.common.annotation.WebAdapter;
import com.gamja.tiggle.reservation.adapter.in.web.response.GetSectionListResponse;
import com.gamja.tiggle.reservation.application.port.in.GetSectionUseCase;
import com.gamja.tiggle.user.domain.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@WebAdapter
@RequestMapping("/section")
@RequiredArgsConstructor

public class GetSectionController {

    private final GetSectionUseCase getSectionUseCase;

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
}
