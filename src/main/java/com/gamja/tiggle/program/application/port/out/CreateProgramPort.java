package com.gamja.tiggle.program.application.port.out;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.program.domain.Program;

public interface CreateProgramPort {
    void createProgram(Program program) throws BaseException;
}
