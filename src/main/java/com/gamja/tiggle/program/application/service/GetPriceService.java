package com.gamja.tiggle.program.application.service;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.annotation.UseCase;
import com.gamja.tiggle.program.application.port.in.GetPriceUseCase;
import com.gamja.tiggle.program.application.port.out.GetPricePort;
import com.gamja.tiggle.program.domain.ProgramGrade;
import lombok.RequiredArgsConstructor;

import java.util.List;


@UseCase
@RequiredArgsConstructor
public class GetPriceService implements GetPriceUseCase {

    private final GetPricePort getPricePort;

    @Override
    public List<ProgramGrade> getPrice(Long id) throws BaseException {

        return getPricePort.getPrice(id);
    }
}
