package com.gamja.tiggle.times.application.port.in;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.times.domain.Times;

import java.util.List;

public interface GetTimesUseCase {
    List<Times> getTimes(Long id) throws BaseException;
}
