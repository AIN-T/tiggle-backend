package com.gamja.tiggle.program.application.service;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.BaseResponseStatus;
import com.gamja.tiggle.common.annotation.UseCase;
import com.gamja.tiggle.program.adapter.out.persistence.JpaLocationRepository;
import com.gamja.tiggle.program.application.port.in.CreateLocationCommand;
import com.gamja.tiggle.program.application.port.in.CreateLocationUseCase;
import com.gamja.tiggle.program.application.port.out.CreateLocationPort;
import com.gamja.tiggle.program.domain.Location;
import lombok.RequiredArgsConstructor;


@UseCase
@RequiredArgsConstructor
public class CreateLocationService implements CreateLocationUseCase {
    private final CreateLocationPort createLocationPort;
    private final JpaLocationRepository jpaLocationRepository;

    @Override
    public void createLocation(CreateLocationCommand command) throws BaseException {
        if (jpaLocationRepository.existsByLocationName(command.getLocationName())){
            throw new BaseException(BaseResponseStatus.DUPLICATE_LOCATION);
        }
        Location location = Location.builder()
                .locationName(command.getAddressName())
                .addressName(command.getAddressName())
                .seatImg(command.getSeatImg())
                .thumbnail(command.getThumbnail())
                .build();
        try {
            createLocationPort.createLocation(location);
        } catch (BaseException e) {
            throw new BaseException(BaseResponseStatus.LOCATION_CREATION_FAILED);
        }

    }
}
