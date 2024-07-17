package com.gamja.tiggle.program.application.port.in;


import com.gamja.tiggle.common.BaseException;

public interface CreateProgramUseCase {
    void createProgram(CreateProgramCommand command) throws BaseException;
}
