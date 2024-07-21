package com.gamja.tiggle.program.application.port.in;


import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.program.domain.Program;

import java.util.List;

public interface ReadProgramUseCase {

    List<Program> readProgramAll(ReadProgramCommand command) throws BaseException;
    List<Program> readRealTimeAll(ReadProgramCommand command) throws BaseException;
    List<Program> readRealTimeAllPaged(ReadProgramCommand command) throws BaseException;
    Program GetProgramDetail(Long id) throws BaseException;
}

