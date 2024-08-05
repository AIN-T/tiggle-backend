package com.gamja.tiggle.point.adapter.out.verify;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.point.adapter.out.persistence.JpaPointRepository;
import com.gamja.tiggle.point.adapter.out.persistence.PointEntity;
import com.gamja.tiggle.point.application.port.out.PointHistoryPort;
import com.gamja.tiggle.point.domain.PointHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PointHistoryAdapter implements PointHistoryPort {
    private final JpaPointRepository jpaPointRepository;

    @Override
    public void save(PointHistory pointHistory) throws BaseException {
        PointEntity entity = PointEntity.builder()
                .id(pointHistory.getId())
                .userId(pointHistory.getUserId())
                .value(pointHistory.getValue())
                .description(pointHistory.getDescription())
                .getOrUse(pointHistory.getGetOrUse())
                .build();

        entity.setModifiedAt();
        jpaPointRepository.save(entity);
    }
}

