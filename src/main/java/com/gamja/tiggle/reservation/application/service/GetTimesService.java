package com.gamja.tiggle.reservation.application.service;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.BaseResponseStatus;
import com.gamja.tiggle.common.annotation.UseCase;
import com.gamja.tiggle.program.application.port.out.ProgramPort;
import com.gamja.tiggle.reservation.application.port.in.GetTimesUseCase;
import com.gamja.tiggle.reservation.application.port.out.GetTimesPort;
import com.gamja.tiggle.reservation.domain.Times;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class GetTimesService implements GetTimesUseCase {

    private final GetTimesPort getTimesPort;
    private final ProgramPort programPort;

    @Override
    public List<Times> getTimes(Long id) throws BaseException {
        if (programPort.existProgram(id)) {
            List<Times> times = getTimesPort.getTimes(id);
            return getList(times);
        } else {
            throw new BaseException(BaseResponseStatus.NOT_FOUND_PROGRAM);
        }


    }

    private static List<Times> getList(List<Times> times) {
        return times.stream().map(t ->
                Times.builder()
                        .id(t.getId())
                        .date(t.getDate())
                        .limitEndTime(t.getLimitEndTime())
                        .round(t.getRound())
                        .build()
        ).toList();
    }
}
