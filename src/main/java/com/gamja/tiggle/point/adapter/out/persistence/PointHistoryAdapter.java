package com.gamja.tiggle.point.adapter.out.persistence;

import com.gamja.tiggle.common.BaseException;
import com.gamja.tiggle.point.application.port.out.PointHistoryPort;
import com.gamja.tiggle.point.domain.GetOrUse;
import com.gamja.tiggle.point.domain.PointHistory;
import com.gamja.tiggle.user.adapter.out.persistence.JpaUserRepository;
import com.gamja.tiggle.user.adapter.out.persistence.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PointHistoryAdapter implements PointHistoryPort {
    private final JpaPointRepository jpaPointRepository;
    private final JpaUserRepository jpaUserRepository;

    @Override
    public List<PointHistory> findPointHistorybyUserId(Long userId, GetOrUse useType) {
        List<PointHistory> pointHistoryList = new ArrayList<>();

        List<PointEntity> pointEntityList = jpaPointRepository.findAllByUserEntity_Id(userId);
        if (useType.equals(GetOrUse.ALL)){
            for (PointEntity p : pointEntityList){
                PointHistory pointHistory = PointHistory.builder()
                        .id(p.getId())
                        .userId(p.getUserEntity().getId())
                        .value(p.getValue())
                        .remainPoint(p.getRemainPoint())
                        .description(p.getDescription())
                        .getOrUse(p.getGetOrUse())
                        .modifiedAt(p.getModifiedAt())
                        .build();
                pointHistoryList.add(pointHistory);
            }
            return pointHistoryList;
        }
        else {
            for (PointEntity p : pointEntityList) {
                if (p.getGetOrUse().equals(useType)) {
                    PointHistory pointHistory = PointHistory.builder()
                            .id(p.getId())
                            .userId(p.getUserEntity().getId())
                            .value(p.getValue())
                            .remainPoint(p.getRemainPoint())
                            .description(p.getDescription())
                            .getOrUse(p.getGetOrUse())
                            .modifiedAt(p.getModifiedAt())
                            .build();
                    pointHistoryList.add(pointHistory);
                }
            }
            return pointHistoryList;
        }
    }

    @Override
    public void save(PointHistory pointHistory) throws BaseException {
        Optional<UserEntity> userEntity = jpaUserRepository.findById(pointHistory.getUserId());
        PointEntity entity = PointEntity.builder()
                .id(pointHistory.getId())
                .userEntity(userEntity.get())
                .value(pointHistory.getValue())
                .remainPoint(pointHistory.getRemainPoint())
                .description(pointHistory.getDescription())
                .getOrUse(pointHistory.getGetOrUse())
                .build();

        entity.setModifiedAt();
        jpaPointRepository.save(entity);
    }
}

