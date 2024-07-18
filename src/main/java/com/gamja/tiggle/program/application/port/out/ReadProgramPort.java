package com.gamja.tiggle.program.application.port.out;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.program.domain.Program;

import java.time.LocalDateTime;
import java.util.List;

public interface ReadProgramPort {
    List<Program> readProgramAll(Program program) throws BaseException;
    List<Program> readRealTimeAll(LocalDateTime currentDateTime) throws BaseException;

    Program getProgramDetail(Long id) throws BaseException;

}
