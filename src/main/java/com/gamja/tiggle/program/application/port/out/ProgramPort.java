package com.gamja.tiggle.program.application.port.out;

import com.gamja.tiggle.common.BaseException;

public interface ProgramPort {
    boolean existProgram(Long id) throws BaseException;

    Long getLocationId(Long programId) throws BaseException;
}
