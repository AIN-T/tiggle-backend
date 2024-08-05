package com.gamja.tiggle.point.adapter.out.verify;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.point.adapter.out.persistence.JpaPointRepository;
import com.gamja.tiggle.point.adapter.out.persistence.PointEntity;
import com.gamja.tiggle.point.application.port.out.PointHistoryPort;
import com.gamja.tiggle.point.domain.GetOrUse;
import com.gamja.tiggle.point.domain.PointHistory;
import com.gamja.tiggle.program.domain.ProgramGrade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PointHistoryAdapter implements PointHistoryPort {
    private final JpaPointRepository jpaPointRepository;

    @Override
    public List<PointHistory> findPointHistorybyUserId(Long userId, GetOrUse method) {
        List<PointHistory> pointHistoryList = new ArrayList<>();

        List<PointEntity> pointEntityList = jpaPointRepository.findAllByUserId(userId);
        for (PointEntity p : pointEntityList) {
            if (p.getGetOrUse().equals(method)) {
                PointHistory pointHistory = PointHistory.builder()
                        .id(p.getId())
                        .userId(p.getUserId())
                        .value(p.getValue())
                        .description(p.getDescription())
                        .getOrUse(p.getGetOrUse())
                        .modifiedAt(p.getModifiedAt())
                        .build();
                pointHistoryList.add(pointHistory);
            }
        }
        return pointHistoryList;
    }

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

