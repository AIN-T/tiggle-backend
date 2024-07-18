package com.gamja.tiggle.program.application.port.in;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.program.domain.ProgramGrade;

import java.util.List;

public interface GetPriceUseCase {
    List<ProgramGrade> getPrice(Long id) throws BaseException;
}
