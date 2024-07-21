package com.gamja.tiggle.program.application.port.out;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.program.domain.Location;

public interface CreateLocationPort {
    void createLocation(Location location) throws BaseException;
}
