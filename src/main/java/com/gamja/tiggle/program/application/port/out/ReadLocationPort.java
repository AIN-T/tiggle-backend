package com.gamja.tiggle.program.application.port.out;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.program.domain.Location;

import java.util.List;

public interface ReadLocationPort {
    List<Location> readProgramAll(Location location) throws BaseException;
}
