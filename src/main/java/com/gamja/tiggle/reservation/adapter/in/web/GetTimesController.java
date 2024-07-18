package com.gamja.tiggle.reservation.adapter.in.web;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.BaseResponse;
import com.gamja.tiggle.common.annotation.WebAdapter;
import com.gamja.tiggle.reservation.adapter.in.web.response.GetTimesResponse;
import com.gamja.tiggle.reservation.application.port.in.GetTimesUseCase;
import lombok.RequiredArgsConstructor;
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
