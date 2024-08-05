package com.gamja.tiggle.program.adapter.out.persistence;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.BaseResponseStatus;
import com.gamja.tiggle.common.annotation.PersistenceAdapter;
import com.gamja.tiggle.program.adapter.out.persistence.Entity.LocationEntity;
import com.gamja.tiggle.program.adapter.out.persistence.Entity.ProgramEntity;
import com.gamja.tiggle.program.application.port.out.CreateLocationPort;
import com.gamja.tiggle.program.application.port.out.ReadLocationPort;
import com.gamja.tiggle.program.domain.Location;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class LocationPersistenceAdapter implements CreateLocationPort, ReadLocationPort {
    private final JpaLocationRepository jpaLocationRepository;

    @Override
    public void createLocation(Location location) throws BaseException {
        // 에러 1. 값이 들어오지 않거나
        if (location == null) {
            throw new BaseException(BaseResponseStatus.INVALID_LOCATION_DATA);
        }
        // 에러 2. location 에 특수문자가 들어왔거나
        if (location.getLocationName().matches(INVALID_CHAR_PATTERN)) {
            throw new BaseException(BaseResponseStatus.INVALID_INPUT_CHARACTER);
        }
        LocationEntity entity = LocationEntity.builder()
                .locationName(location.getLocationName())
                .addressName(location.getAddressName())
                .thumbnail(location.getThumbnail())
                .seatImg(location.getSeatImg())
                .build();
        jpaLocationRepository.save(entity);
    }

    @Override
    public List<Location> readProgramAll(Location location) throws BaseException {
        List<LocationEntity> result = jpaLocationRepository.findAllById(location.getId());
        if (result.size() == 0) {
            throw new BaseException(BaseResponseStatus.NOT_FOUND_LOCATION_ID);
        }
        List<Location> locations = result.stream().map(
                        (l) -> Location.builder()
                                .id(l.getId())
                                .locationName(l.getLocationName())
                                .thumbnail(l.getThumbnail())
                                .addressName(l.getAddressName())
                                .seatImg(l.getSeatImg())
                                .programNames(l.getProgramEntities().stream()
                                        .map(ProgramEntity::getProgramName) // ProgramEntity 의 이름을 추출
                                        .collect(Collectors.toList()))
                                .build())
                .collect(Collectors.toList());

        return locations;
    }

    @Override
    public Location readLocation(Long locationId) throws BaseException {
        LocationEntity locationEntity = jpaLocationRepository.findById(locationId).orElseThrow(() ->
                new BaseException(BaseResponseStatus.NOT_FOUND_LOCATION));

        return Location
                .builder()
                .locationName(locationEntity.getLocationName())
                .addressName(locationEntity.getAddressName())
                .build();
    }


    // 특수 문자 허용하지 않는
    private static final String INVALID_CHAR_PATTERN = "[^a-zA-Z0-9 ]";
}
