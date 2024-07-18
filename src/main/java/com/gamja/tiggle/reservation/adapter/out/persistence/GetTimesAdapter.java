package com.gamja.tiggle.reservation.adapter.out.persistence;

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
    public List<Times> getTimes(Long id) {
        List<TimesEntity> TimesList = timesRepository.findAllByProgramId(id);
        return getList(TimesList);
    }

    private static List<Times> getList(List<TimesEntity> TimesList) {
        return TimesList.stream().map(timesEntity -> Times.builder()
                .id(timesEntity.getId())
                .programId(timesEntity.getProgram().getId())
                .date(timesEntity.getDate())
                .limitEndTime(timesEntity.getLimitEnterTime())
                .round(timesEntity.getRound())
                .build()
        ).toList();
    }
}
