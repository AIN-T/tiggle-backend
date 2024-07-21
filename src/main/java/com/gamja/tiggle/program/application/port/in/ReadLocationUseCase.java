package com.gamja.tiggle.program.application.port.in;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.program.domain.Location;

import java.util.List;

public interface ReadLocationUseCase {
    List<Location> readProgramAll(ReadLocationCommand command) throws BaseException;
}
