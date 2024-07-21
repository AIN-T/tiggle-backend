package com.gamja.tiggle.program.application.service;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.annotation.UseCase;
import com.gamja.tiggle.program.application.port.in.ReadLocationCommand;
import com.gamja.tiggle.program.application.port.in.ReadLocationUseCase;
import com.gamja.tiggle.program.application.port.out.ReadLocationPort;
import com.gamja.tiggle.program.domain.Location;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class ReadLocationService implements ReadLocationUseCase {
    private final ReadLocationPort readLocationPort;

    @Override
    public List<Location> readProgramAll(ReadLocationCommand command) throws BaseException {
        Location location = Location.builder()
                .id(command.getLocationId())
                .build();
        return readLocationPort.readProgramAll(location);
    }

}
