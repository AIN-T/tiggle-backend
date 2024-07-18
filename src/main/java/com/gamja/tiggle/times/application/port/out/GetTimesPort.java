package com.gamja.tiggle.times.application.port.out;

import com.gamja.tiggle.times.domain.Times;

import java.util.List;

public interface GetTimesPort {

    List<Times> getTimes(Long id);
}
