package com.gamja.tiggle.program.application.port.in;


import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.program.domain.Program;
import com.gamja.tiggle.user.domain.User;

import java.util.List;

public interface ReadProgramUseCase {

    List<Program> readProgramAll(ReadProgramCommand command) throws BaseException;
    List<Program> readRealTimeAll(ReadProgramCommand command) throws BaseException;
    List<Program> readRealTimeAllPaged(ReadProgramCommand command) throws BaseException;
    List<Program> searchPrograms(ReadProgramCommand command) throws BaseException;
    Program GetProgramDetail(Long id, User user) throws BaseException;
}

