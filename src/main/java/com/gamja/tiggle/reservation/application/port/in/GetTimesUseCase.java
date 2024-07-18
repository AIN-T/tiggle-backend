package com.gamja.tiggle.reservation.application.port.in;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.reservation.domain.Times;

import java.util.List;

public interface GetTimesUseCase {
    List<Times> getTimes(Long id) throws BaseException;
}
