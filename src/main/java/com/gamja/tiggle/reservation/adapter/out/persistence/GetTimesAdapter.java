package com.gamja.tiggle.reservation.adapter.out.persistence;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.common.BaseResponseStatus;
import com.gamja.tiggle.common.annotation.PersistenceAdapter;
import com.gamja.tiggle.reservation.adapter.out.persistence.Entity.TimesEntity;
import com.gamja.tiggle.reservation.adapter.out.persistence.repositroy.TimesRepository;
import com.gamja.tiggle.reservation.application.port.out.GetTimesPort;
import com.gamja.tiggle.reservation.domain.Times;
import lombok.RequiredArgsConstructor;

import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class GetTimesAdapter implements GetTimesPort {

    private final TimesRepository timesRepository;

    @Override
    public List<Times> getTimes(Long id) throws BaseException {
        List<TimesEntity> TimesList = timesRepository.findAllByProgramEntityId(id);
        if (TimesList.isEmpty()) {
            throw new BaseException(BaseResponseStatus.NOT_FOUND_TIMES);
        }
        return getList(TimesList);
    }

    @Override
    public void verifyTimes(Long timesId, Long programId) throws BaseException {

        if (!timesRepository.existsByProgramEntityIdAndId(programId, timesId)) {
            throw new BaseException(BaseResponseStatus.NOT_EXIST_TIMES);
        }

    }

    private static List<Times> getList(List<TimesEntity> TimesList) {
        return TimesList.stream().map(timesEntity -> Times.builder()
                .id(timesEntity.getId())
                .programId(timesEntity.getProgramEntity().getId())
                .date(timesEntity.getDate())
                .limitEndTime(timesEntity.getLimitEnterTime())
                .round(timesEntity.getRound())
                .build()
        ).toList();
    }
}
