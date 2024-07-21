package com.gamja.tiggle.program.adapter.in.web;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.BaseResponse;
import com.gamja.tiggle.common.annotation.WebAdapter;
import com.gamja.tiggle.program.adapter.in.web.response.GetPriceResponse;
import com.gamja.tiggle.program.application.port.in.GetPriceUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@WebAdapter
@RequiredArgsConstructor
@RequestMapping("/price")
@Tag(name = "공연 가격 정보 컨트롤러", description = "programId를 입력하면 해당 공연의 등급 별 가격 정보 리스트를 응답")
public class GetPriceController {

    private final GetPriceUseCase getPriceUseCase;

    @GetMapping
    public BaseResponse<List<GetPriceResponse>> getPrice(@RequestParam Long programId) {

        List<GetPriceResponse> list;
        try {
            list = getPriceUseCase.getPrice(programId).stream().map(programGrade ->
                    GetPriceResponse.builder()
                            .gradeName(programGrade.getGradeName())
                            .price(programGrade.getPrice())
                            .programGradeId(programGrade.getId())
                            .build()
            ).toList();
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
        return new BaseResponse<>(list);
    }
}
