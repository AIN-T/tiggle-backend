package com.gamja.tiggle.reservation.application.port.out;

import com.gamja.tiggle.reservation.domain.Times;

import java.util.List;

public interface GetTimesPort {

    List<Times> getTimes(Long id);
}
