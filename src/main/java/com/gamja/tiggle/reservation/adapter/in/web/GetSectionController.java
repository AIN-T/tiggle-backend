package com.gamja.tiggle.reservation.adapter.in.web;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.BaseResponse;
import com.gamja.tiggle.common.annotation.WebAdapter;
import com.gamja.tiggle.reservation.adapter.in.web.response.GetSectionListResponse;
import com.gamja.tiggle.reservation.application.in.GetSectionUseCase;
import lombok.RequiredArgsConstructor;
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
    public BaseResponse<List<GetSectionListResponse>> getSection(@PathVariable Long locationId) throws BaseException {
        return new BaseResponse<>(getList(locationId));
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
