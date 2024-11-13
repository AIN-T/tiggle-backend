package com.gamja.tiggle.program.application.service;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.annotation.UseCase;
import com.gamja.tiggle.program.application.port.in.ReadProgramCommand;
import com.gamja.tiggle.program.application.port.in.ReadProgramUseCase;
import com.gamja.tiggle.program.application.port.out.ReadProgramPort;
import com.gamja.tiggle.program.domain.Program;
import lombok.RequiredArgsConstructor;

import java.util.List;


@UseCase
@RequiredArgsConstructor
public class ReadProgramService implements ReadProgramUseCase {
    private final ReadProgramPort readProgramPort;

    @Override
    public List<Program> readProgramAll(ReadProgramCommand command) throws BaseException {
        Program program = Program.builder()
                .categoryId(command.getCategoryId())
                .build();
        return readProgramPort.readProgramAll(program, command);
    }

    @Override
    public List<Program> readRealTimeAll(ReadProgramCommand command) throws BaseException {
        Program program = Program.builder()
                .currentDateTime(command.getCurrentDateTime())
                .build();
        return readProgramPort.readRealTimeAll(program.getCurrentDateTime());
    }

    @Override
    public List<Program> readRealTimeAllPaged(ReadProgramCommand command) throws BaseException {
        return readProgramPort.readRealTimeAllPaged(command);
    }

    @Override
    public Program GetProgramDetail(Long id) throws BaseException {
        return readProgramPort.getProgramDetail(id);
    }
}
